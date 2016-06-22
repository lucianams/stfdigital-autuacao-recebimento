import {PreautuacaoService, PreautuarRemessaCommand} from "recebimento/preautuacao/originario/preautuacao.service";
import "recebimento/preautuacao/originario/preautuacao.service";
import 'recebimento/services/services.module';

describe("Teste do serviço de preautuação", () => {

    let $httpBackend : ng.IHttpBackendService;
    let preautuacao : PreautuacaoService;
	let properties;

    beforeEach(angular.mock.module('app.recebimento.services', 'app.recebimento.preautuacao-originario'));

    beforeEach(inject(['$httpBackend', 'app.recebimento.preautuacao-originario.PreautuacaoService', 'properties', (_$httpBackend_ : ng.IHttpBackendService, _preautuacaoService_ : PreautuacaoService, _properties_) => {
        $httpBackend = _$httpBackend_;
       	preautuacao =  _preautuacaoService_;
       	properties = _properties_;
    }]));


   
    it("deveria chamar o serviço rest de preautuação", () => {
        $httpBackend.expectPOST(properties.apiUrl + '/recebimento/api/remessas/preautuacao', new PreautuarRemessaCommand(123, 'HC', 'PUBLICO',[1])).respond(200,"");
		preautuacao.preautuarProcesso(123, 'HC', 'PUBLICO',[1]);
        $httpBackend.flush();
    });
});