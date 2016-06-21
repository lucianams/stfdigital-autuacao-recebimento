import {PeticaoFisicaController} from "recebimento/peticoes-fisicas/peticao-fisica.controller";
import {PeticaoFisicaService} from "recebimento/peticoes-fisicas/peticao-fisica.service";

describe('Teste do controlador peticoes-fisicas.controller', () => {
	let controller : PeticaoFisicaController;
	var scope;
	
	beforeEach(inject(($rootScope, $httpBackend: angular.IHttpBackendService, $controller: angular.IControllerService, properties : any) => {
		$httpBackend.expectGET(properties.apiUrl + '/formas-recebimento').respond([{sigla: 'BALCAO', descricao: 'Balcão', exigeNumero : false}, {sigla: 'SEDEX', descricao: 'Sedex', exigeNumero : true}]);
		let ctrl = new PeticaoFisicaController(null, null, null);
	}));
	
	it ('Deveria carregar a lista de forma de envio do controlador', () =>{
		expect(controller.formasRecebimento.length).toEqual(2);
	});
});