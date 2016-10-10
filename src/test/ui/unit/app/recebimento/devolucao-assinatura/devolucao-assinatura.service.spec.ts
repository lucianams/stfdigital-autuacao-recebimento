import {DevolucaoAssinaturaService, AssinarOficioParaDevolucaoCommand, Devolucao, Documento} from
    "recebimento/devolucao-assinatura/devolucao-assinatura.service";
import {Modelo, TipoDocumento} from "recebimento/services/model";

import "recebimento/devolucao-assinatura/devolucao-assinatura.service";
import "recebimento/services/services.module";

describe("Teste do serviço devolucao-assinatura.service", () => {

    let $httpBackend: ng.IHttpBackendService;
    let devolucaoAssinaturaService: DevolucaoAssinaturaService;
    let properties: app.support.constants.Properties;

    let handler;

    beforeEach(angular.mock.module("app.recebimento.services", "app.recebimento.devolucao-assinatura"));

    beforeEach(inject(["$httpBackend", "app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService", "properties",
            (_$httpBackend_: ng.IHttpBackendService, _devolucaoAssinaturaService_: DevolucaoAssinaturaService,
            _properties_: app.support.constants.Properties) => {
        $httpBackend = _$httpBackend_;
        devolucaoAssinaturaService =  _devolucaoAssinaturaService_;
        properties = _properties_;
    }]));

    beforeEach(() => {
        handler = {
            success: () => {},
            error: () => {}
        };
        spyOn(handler, "success").and.callThrough();
        spyOn(handler, "error").and.callThrough();
    });

    it("Deveria chamar o serviço rest de assinar o ofício de devolução", () => {
        let command: AssinarOficioParaDevolucaoCommand = new AssinarOficioParaDevolucaoCommand(9001, "1234-5675");
        $httpBackend.expectPOST(properties.apiUrl + "/recebimento/api/remessas/devolucao-assinatura", command)
                .respond(200, {});

        devolucaoAssinaturaService.assinarOficioDevolucao(command).then(handler.success, handler.error);

        $httpBackend.flush();

        expect(handler.success).toHaveBeenCalledWith({});
        expect(handler.error).not.toHaveBeenCalled();
    });

    it("Deveria chamar o serviço rest de consultar devoluções", () => {
        let protocolos = [1, 2];
        let devolucao1 = {
            textoId: 1,
            remessaProtocoloId: 1,
            remessaNumero: 1,
            remessaAno: 2016,
            modeloDevolucao: {
                id: 7,
                documento: 16,
                nome: "Modelo 1",
                tipoDocumento: new TipoDocumento(3, "Ofício de devolução 1")
            }
        };
        let devolucao2 = {
            textoId: 2,
            remessaProtocoloId: 2,
            remessaNumero: 2,
            remessaAno: 2016,
            modeloDevolucao: {
                id: 8,
                documento: 17,
                nome: "Modelo 2",
                tipoDocumento: new TipoDocumento(4, "Ofício de devolução 2")
            }
        };

        $httpBackend.expectGET(properties.apiUrl + "/recebimento/api/remessas/1/devolucao").respond(200, devolucao1);
        $httpBackend.expectGET(properties.apiUrl + "/recebimento/api/remessas/2/devolucao").respond(200, devolucao2);

        devolucaoAssinaturaService.consultarDevolucoes(protocolos).then(handler.success, handler.error);

        $httpBackend.flush();

        expect(handler.success).toHaveBeenCalledWith([devolucao1, devolucao2]);
        expect(handler.error).not.toHaveBeenCalled();
    });

    it("Deveria chamar o serviço rest de consultar o documento final do texto", () => {
        let textoId: number = 3;
        let documento: Documento = {
            documentoId: 4,
            quantidadePaginas: 12,
            tamanho: 1024
        };
        $httpBackend.expectGET(properties.apiUrl + "/documents/api/textos/3/documento-final").respond(200, documento);

        devolucaoAssinaturaService.consultarDocumentoFinalDoTexto(textoId).then(handler.success, handler.error);

        $httpBackend.flush();

        expect(handler.success).toHaveBeenCalledWith(documento);
        expect(handler.error).not.toHaveBeenCalled();
    });
});