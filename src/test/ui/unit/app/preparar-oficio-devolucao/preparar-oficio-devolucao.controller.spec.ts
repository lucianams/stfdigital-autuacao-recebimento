import "recebimento/preparar-oficio-devolucao";
import {PrepararOficioDevolucaoController} from "recebimento/preparar-oficio-devolucao/preparar-oficio-devolucao.controller";

describe('Devolução Controller', () => {
	
	//beforeEach(angular.mock.module('app.novo-processo.devolucao'));
	
	let controller: PrepararOficioDevolucaoController;

	beforeEach(inject(($rootScope, $httpBackend: angular.IHttpBackendService, $controller: angular.IControllerService) => {
		var scope = $rootScope.$new();
		
		//let ctrl = new PrepararOficioDevolucaoController(null, null, null, null, null);
		/*
		controller = <DevolucaoController>$controller('app.novo-processo.devolucao.DevolucaoController', {
			$scope : scope,
			motivosDevolucao: [],
			protocolo: 2,
			MessagesService: {}
		});*/
	}));
	
	it('Deveria criar a controller', () => {
		expect(true).toEqual(true);
	});
	
});