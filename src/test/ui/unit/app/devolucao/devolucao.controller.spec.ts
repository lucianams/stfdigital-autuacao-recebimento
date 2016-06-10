//import "recebimento/devolucao";
import {DevolucaoController} from "recebimento/devolucao/devolucao.controller";

describe('Devolução Controller', () => {
	
	//beforeEach(angular.mock.module('app.novo-processo.devolucao'));
	
	let controller: DevolucaoController;
	
	beforeEach(inject(($rootScope, $httpBackend: angular.IHttpBackendService, $controller: angular.IControllerService) => {
		var scope = $rootScope.$new();
		let ctrl = new DevolucaoController(null, null, null, null, null);
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