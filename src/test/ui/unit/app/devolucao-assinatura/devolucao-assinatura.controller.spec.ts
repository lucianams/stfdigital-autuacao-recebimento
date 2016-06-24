import {DevolucaoAssinaturaController} from "recebimento/devolucao-assinatura/devolucao-assinatura.controller";

describe('Teste do controlador devolucao-assinatura.controller', () => {

	let $rootScope: ng.IRootScopeService;
	let $q: ng.IQService;

	let controller: DevolucaoAssinaturaController;
	let mockState;
	let mockPrepararOficioDevolucaoService;
	let mockMessagesService;

    describe('No módulo', () => {
        inject(($rootScope, $httpBackend: angular.IHttpBackendService) => {

        });
	});

    beforeEach(inject(($rootScope, $httpBackend: angular.IHttpBackendService) => {

    }));
	
    it('Deveria criar a controller', () => {
        expect(true).toEqual(true);
    });
	
});