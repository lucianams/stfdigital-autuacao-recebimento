import {DevolverRemessaCommand, DevolucaoService} from "recebimento/preautuacao/devolucao/devolucao.service";

import "recebimento/preautuacao/devolucao/devolucao.service";

describe("Teste do serviço de devolução na preautuação", () => {
    let $httpBackend: ng.IHttpBackendService;
    let devolucaoService: DevolucaoService;
    let properties;
    let handler;

    beforeEach(angular.mock.module("app.recebimento.preautuacao-devolucao"));

    beforeEach(inject(["$httpBackend", "app.recebimento.preautuacao-devolucao.DevolucaoService", "properties",
            (_$httpBackend_: ng.IHttpBackendService, _devolucaoService_: DevolucaoService, _properties_) => {
        $httpBackend = _$httpBackend_;
        devolucaoService =  _devolucaoService_;
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

    it("deveria chamar o serviço rest de devolução", () => {
        let cmdDevolver: DevolverRemessaCommand = new DevolverRemessaCommand();
        cmdDevolver.motivo  = "Devolução por falta da classe do processo";
        $httpBackend.expectPOST(properties.apiUrl + "/recebimento/api/remessas/" +
                cmdDevolver.protocoloId + "/devolucao", cmdDevolver)
                .respond(200, "");
        devolucaoService.devolver(cmdDevolver);
        $httpBackend.flush();
    });
});