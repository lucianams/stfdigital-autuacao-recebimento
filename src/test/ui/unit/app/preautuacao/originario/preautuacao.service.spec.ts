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
    	let cmdPreautuar : PreautuarRemessaCommand = new PreautuarRemessaCommand();
    	cmdPreautuar.classeId = 'HC';
    	cmdPreautuar.preferencias = [1];
        $httpBackend.expectPOST(properties.apiUrl + '/recebimento/api/remessas/preautuacao', cmdPreautuar).respond(200,"");
		preautuacao.preautuarProcesso(cmdPreautuar).then(handler.success, handler.error);
        $httpBackend.flush();
        expect(handler.success).toHaveBeenCalled();
        expect(handler.error).not.toHaveBeenCalled();
    });
    
    it("deveria chamar o serviço rest de devolução", () => {
    	let cmdDevolver : DevolverRemessaCommand = new DevolverRemessaCommand();
    	cmdDevolver.motivo  = 'Devolução por falta da classe do processo';
        $httpBackend.expectPOST(properties.apiUrl + '/recebimento/api/remessas/devolucao', cmdDevolver).respond(200,"");
		preautuacao.devolver(cmdDevolver);
        $httpBackend.flush();
    });
    
});