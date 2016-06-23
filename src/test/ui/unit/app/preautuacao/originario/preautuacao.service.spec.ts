import {PreautuacaoService, PreautuarRemessaCommand, DevolverRemessaCommand} from "recebimento/preautuacao/originario/preautuacao.service";
import "recebimento/preautuacao/originario/preautuacao.service";
import 'recebimento/services/services.module';

describe("Teste do serviço de preautuação", () => {

    let $httpBackend : ng.IHttpBackendService;
    let preautuacao : PreautuacaoService;
	let properties;
	let handler;

    beforeEach(angular.mock.module('app.recebimento.services', 'app.recebimento.preautuacao-originario'));

    beforeEach(inject(['$httpBackend', 'app.recebimento.preautuacao-originario.PreautuacaoService', 'properties', (_$httpBackend_ : ng.IHttpBackendService, _preautuacaoService_ : PreautuacaoService, _properties_) => {
        $httpBackend = _$httpBackend_;
       	preautuacao =  _preautuacaoService_;
       	properties = _properties_;
    }]));
    
    beforeEach(() => {
        handler = {
            success: () => {},
            error: () => {}
		}; 
 		spyOn(handler, 'success').and.callThrough();
		spyOn(handler, 'error').and.callThrough();
    });

    it("deveria chamar o serviço rest de preautuação", () => {
        $httpBackend.expectPOST(properties.apiUrl + '/recebimento/api/remessas/preautuacao', new PreautuarRemessaCommand(123, 'HC', 'PUBLICO',[1])).respond(200,"");
		preautuacao.preautuarProcesso(123, 'HC', 'PUBLICO',[1]).then(handler.success, handler.error);
        $httpBackend.flush();
        expect(handler.success).toHaveBeenCalled();
        expect(handler.error).not.toHaveBeenCalled();
    });
    
    it("deveria chamar o serviço rest de devolução", () => {
        $httpBackend.expectPOST(properties.apiUrl + '/recebimento/api/remessas/devolucao', new DevolverRemessaCommand(123, 'Devolução por falta da classe do processo')).respond(200,"");
		preautuacao.devolver(new DevolverRemessaCommand(123, 'Devolução por falta da classe do processo'));
        $httpBackend.flush();
    });
    
});