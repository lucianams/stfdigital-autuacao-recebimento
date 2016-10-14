import {PreautuacaoRecursalService, PreautuarRecursalCommand} from
        "recebimento/preautuacao/recursal/preautuacao-recursal.service";

import "recebimento/preautuacao/recursal/preautuacao-recursal.service";
import "recebimento/services/services.module";

describe("Teste do serviço de preautuação recursal", () => {
    let $httpBackend: ng.IHttpBackendService;
    let preautuacao: PreautuacaoRecursalService;
    let properties;
    let handler;

    beforeEach(angular.mock.module("app.recebimento.services", "app.recebimento.preautuacao-recursal"));

    beforeEach(inject(["$httpBackend", "app.recebimento.preautuacao-recursal.PreautuacaoRecursalService", "properties",
            (_$httpBackend_: ng.IHttpBackendService, _preautuacaoRecursalService_: PreautuacaoRecursalService,
            _properties_) => {
        $httpBackend = _$httpBackend_;
        preautuacao =  _preautuacaoRecursalService_;
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

    it("deveria chamar o serviço rest de preautuação recursal", () => {
        let cmdPreautuar: PreautuarRecursalCommand = new PreautuarRecursalCommand();
        cmdPreautuar.classeId = "HC";
        cmdPreautuar.preferencias = [1];
        cmdPreautuar.numeroProcessoOrigem = "123456";
        cmdPreautuar.numeroUnicoProcesso = "987645321";
        cmdPreautuar.sigilo = "";
        $httpBackend.expectPUT(properties.apiUrl + "/recebimento/api/remessas/" +
                cmdPreautuar.protocoloId + "/preautuacao-recursal", cmdPreautuar)
                .respond(200, "");
        preautuacao.preautuarRecursal(cmdPreautuar).then(handler.success, handler.error);
        $httpBackend.flush();
        expect(handler.success).toHaveBeenCalled();
        expect(handler.error).not.toHaveBeenCalled();
    });
});