import {DevolucaoAssinaturaController} from "recebimento/devolucao-assinatura/devolucao-assinatura.controller";
import {Devolucao} from "recebimento/devolucao-assinatura/devolucao-assinatura.service";
import {Modelo, TipoDocumento} from "recebimento/services/model";

describe('Controlador devolucao-assinatura.controller', () => {

	let $rootScope: ng.IRootScopeService;
	let $q: ng.IQService;

	let controller: DevolucaoAssinaturaController;
	let mockState;
	let mockDevolucaoAssinaturaService;
	let mockMessagesService;
    let mockSignatureService;

    let mockSigner;

    let mockDevolucoes: Devolucao[];

	beforeEach(inject((_$rootScope_: ng.IRootScopeService, _$q_: ng.IQService) => {
		$rootScope = _$rootScope_;
		$q = _$q_;

		mockState = {
			go : () => {}
		};

		mockDevolucaoAssinaturaService = {

		};

		mockMessagesService = {
			error: () => {},
			success: () => {}
		};

        mockDevolucoes = [<Devolucao>{textoId: 1, remessaProtocoloId: 9001, remessaNumero: 123, remessaAno: 2016,
            modeloDevolucao: <Modelo>{id: 4, documento: 7, nome: "Modelo de devolução", tipoDocumento: new TipoDocumento(1, 'Ofício de devolução')}},
            <Devolucao>{textoId: 2, remessaProtocoloId: 9002, remessaNumero: 124, remessaAno: 2016,
            modeloDevolucao: <Modelo>{id: 5, documento: 8, nome: "Modelo de devolução 2", tipoDocumento: new TipoDocumento(2, 'Carta de devolução')}}];

        mockSigner = {

        };

        mockSignatureService = {
            signingManager: () => {
                return mockSigner;
            }
        };

		controller = new DevolucaoAssinaturaController(mockState, mockDevolucaoAssinaturaService, mockDevolucoes, mockSignatureService, mockMessagesService);
	}));
	
    describe('Método assinar', () => {
        it('Deveria chamar mensagem de erro se nenhuma devolução tiver sido selecionada para assinar', () => {
            spyOn(mockMessagesService, 'error').and.callThrough();

            controller.devolucoesParaAssinar = [];
            controller.assinar();

            expect(mockMessagesService.error).toHaveBeenCalledWith('É necessário selecionar pelo menos uma remessa para assinar.');
        });

        describe('Interação com o mecanismo de assinatura', () => {

            beforeEach(() => {
                controller.assinar();
            });

            it('TODO Deveria terminar assinatura com sucesso', () => {
                expect(true).toEqual(true);
            });

        });
    });
	
});