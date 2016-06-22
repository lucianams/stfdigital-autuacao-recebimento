import {DevolucaoAssinaturaController} from "recebimento/devolucao-assinatura/devolucao-assinatura.controller";

describe('Devolução Assinatura Controller', () => {

    let controller: DevolucaoAssinaturaController;

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