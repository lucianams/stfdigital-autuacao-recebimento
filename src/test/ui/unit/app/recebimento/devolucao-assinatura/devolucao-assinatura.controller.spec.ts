import {DevolucaoAssinaturaController, DevolucaoEmAssinatura} from
        "recebimento/devolucao-assinatura/devolucao-assinatura.controller";
import {AssinarOficioParaDevolucaoCommand, Devolucao, Documento} from
        "recebimento/devolucao-assinatura/devolucao-assinatura.service";
import {Modelo, TipoDocumento} from "recebimento/services/model";

describe("Teste do controlador devolucao-assinatura.controller", () => {
    let $rootScope: ng.IRootScopeService;
    let $q: ng.IQService;

    let controller: DevolucaoAssinaturaController;
    let mockState;
    let mockDevolucaoAssinaturaService;
    let mockMessagesService;
    let mockSignatureService;

    let mockSigningManager;
    let mockSigner;

    let mockDevolucoes: Devolucao[];

    beforeEach(inject((_$rootScope_: ng.IRootScopeService, _$q_: ng.IQService) => {
        $rootScope = _$rootScope_;
        $q = _$q_;

        mockState = {
            go : () => {}
        };

        mockDevolucaoAssinaturaService = {
            consultarDocumentoFinalDoTexto: () => {},
            assinarOficioDevolucao: () => {}
        };

        mockMessagesService = {
            error: () => {},
            success: () => {}
        };

        mockDevolucoes = [{
            textoId: 1,
            remessaProtocoloId: 9001,
            remessaNumero: 123,
            remessaAno: 2016,
            modeloDevolucao: {
                id: 4,
                documento: 7,
                nome: "Modelo de devolução",
                tipoDocumento: new TipoDocumento(1, "Ofício de devolução")
            }
        }, {
            textoId: 2,
            remessaProtocoloId: 9002,
            remessaNumero: 124,
            remessaAno: 2016,
            modeloDevolucao: {
                id: 5,
                documento: 8,
                nome: "Modelo de devolução 2",
                tipoDocumento: new TipoDocumento(2, "Carta de devolução")
            }
        }];

        mockSigner = {
            onSignerReady: () => {},
            onSigningCompleted: () => {},
            onErrorCallback: () => {},
            start: () => {},
            provideExistingDocument: () => {},
            saveSignedDocument: () => {}
        };

        mockSigningManager = {
            createSigner: () => {
                return mockSigner;
            }
        };

        mockSignatureService = {
            signingManager: () => {
                return mockSigningManager;
            }
        };

        controller = new DevolucaoAssinaturaController(mockState, mockDevolucaoAssinaturaService, mockDevolucoes,
                mockSignatureService, mockMessagesService);
    }));

    describe("Método assinar", () => {
        it("Deveria chamar mensagem de erro se nenhuma devolução tiver sido selecionada para assinar", () => {
            spyOn(mockMessagesService, "error").and.callThrough();

            controller.devolucoesParaAssinar = [];
            controller.assinar();

            expect(mockMessagesService.error).toHaveBeenCalledWith(
                    "É necessário selecionar pelo menos uma remessa para assinar.");
        });

        describe("Interação com o mecanismo de assinatura", () => {
            it("Deveria registrar corretamente os callbacks", () => {
                spyOn(mockSigner, "start").and.callThrough();
                spyOn(mockSigner, "onSignerReady").and.callThrough();
                spyOn(mockSigner, "onSigningCompleted").and.callThrough();
                spyOn(mockSigner, "onErrorCallback").and.callThrough();

                controller.devolucoesParaAssinar = <DevolucaoEmAssinatura[]>controller.devolucoes;

                controller.assinar();

                // Verificação do registro dos callbacks
                expect(mockSigner.start).toHaveBeenCalledTimes(2);
                expect(mockSigner.onSignerReady).toHaveBeenCalledTimes(2);
                expect(mockSigner.onSigningCompleted).toHaveBeenCalledTimes(2);
                expect(mockSigner.onErrorCallback).toHaveBeenCalledTimes(2);
            });

            it("Deveria interagir via callback onSignerReady corretamente", () => {
                let callbacks = [];
                spyOn(mockSigner, "onSignerReady").and.callFake((cb) => {
                    callbacks.push(cb);
                });
                spyOn(mockDevolucaoAssinaturaService, "consultarDocumentoFinalDoTexto").and
                        .callFake((textoId: number) => {
                    if (textoId === 1) {
                        return $q.when(<Documento>{documentoId: 7, quantidadePaginas: 2, tamanho: 1024});
                    } else if (textoId === 2) {
                        return $q.when(<Documento>{documentoId: 8, quantidadePaginas: 3, tamanho: 1025});
                    }
                });
                spyOn(mockSigner, "provideExistingDocument").and.callThrough();

                controller.devolucoesParaAssinar = <DevolucaoEmAssinatura[]>controller.devolucoes;

                controller.assinar();

                expect(mockSigner.onSignerReady).toHaveBeenCalledTimes(2);

                expect(callbacks.length).toEqual(2);

                callbacks[0]();
                expect(mockDevolucaoAssinaturaService.consultarDocumentoFinalDoTexto).toHaveBeenCalledWith(1);

                $rootScope.$apply();
                expect(mockSigner.provideExistingDocument).toHaveBeenCalledWith(7);

                callbacks[1]();
                expect(mockDevolucaoAssinaturaService.consultarDocumentoFinalDoTexto).toHaveBeenCalledWith(2);

                $rootScope.$apply();
                expect(mockSigner.provideExistingDocument).toHaveBeenCalledWith(8);
            });

            it("Deveria interagir via callback onSigningCompleted corretamente", () => {
                let callbacks = [];
                spyOn(mockSigner, "onSigningCompleted").and.callFake((cb) => {
                    callbacks.push(cb);
                });

                spyOn(mockSigner, "saveSignedDocument").and.callFake(() => {
                    return $q.when(<app.certification.SignedDocumentDto>{documentId: "1abcd-bef87"});
                });

                spyOn(mockDevolucaoAssinaturaService, "assinarOficioDevolucao").and.callFake(() => {
                    return $q.when();
                });

                spyOn(mockState, "go").and.callThrough();
                spyOn(mockMessagesService, "success").and.callThrough();

                controller.devolucoesParaAssinar = <DevolucaoEmAssinatura[]>controller.devolucoes;

                controller.assinar();

                expect(mockSigner.onSigningCompleted).toHaveBeenCalledTimes(2);

                callbacks[0]();
                expect(mockSigner.saveSignedDocument).toHaveBeenCalled();

                expect(controller.devolucoesParaAssinar[0].teminou()).toEqual(false);
                $rootScope.$apply();

                expect(mockDevolucaoAssinaturaService.assinarOficioDevolucao).toHaveBeenCalledWith(
                    new AssinarOficioParaDevolucaoCommand(9001, "1abcd-bef87")
                );
                expect(controller.devolucoesParaAssinar[0].teminou()).toEqual(true);

                callbacks[1]();
                expect(mockSigner.saveSignedDocument).toHaveBeenCalled();

                expect(controller.devolucoesParaAssinar[1].teminou()).toEqual(false);
                $rootScope.$apply();

                expect(mockDevolucaoAssinaturaService.assinarOficioDevolucao).toHaveBeenCalledWith(
                    new AssinarOficioParaDevolucaoCommand(9002, "1abcd-bef87")
                );
                expect(controller.devolucoesParaAssinar[1].teminou()).toEqual(true);
                expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas");
                expect(mockMessagesService.success)
                        .toHaveBeenCalledWith("2 documento(s) de devolução assinados com sucesso.");
            });

            it("Deveria interagir via callback onErrorCallback corretamente", () => {
                let callbacks = [];
                spyOn(mockSigner, "onErrorCallback").and.callFake((cb) => {
                    callbacks.push(cb);
                });

                spyOn(mockMessagesService, "error").and.callThrough();

                controller.devolucoesParaAssinar = <DevolucaoEmAssinatura[]>controller.devolucoes;

                controller.assinar();

                expect(mockSigner.onErrorCallback).toHaveBeenCalledTimes(2);

                callbacks[0]({error: "Erro ao assinar"});

                expect(mockMessagesService.error).toHaveBeenCalledWith(
                        "Erro ao assinar documento de devolução da Remessa 123/2016. Erro ao assinar");
            });
        });
    });
});