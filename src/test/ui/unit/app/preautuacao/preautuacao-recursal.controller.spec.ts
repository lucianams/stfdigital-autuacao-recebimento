import {PreautuacaoRecursalController} from "recebimento/preautuacao/recursal/preautuacao-recursal.controller";
import {PreautuacaoService, PreautuarRecursalCommand} from "recebimento/services/preautuacao.service";
import {Classe} from 'recebimento/services/model';


describe('Teste do controlador preautuacao-recursal.controller', () => {
	
	let controller : PreautuacaoRecursalController;
	let $q : ng.IQService;
	let $rootScope : ng.IRootScopeService;
	let mockState;
	let mockPreautuacaoRecursalService;
	
	beforeEach(inject((_$q_, _$rootScope_) => {
        $q = _$q_;
        $rootScope = _$rootScope_;
	}));
	
	beforeEach(() => {
		mockState = {
			go : () => {}
		};
		mockPreautuacaoRecursalService = {
			preautuarRecursal : () =>{}
		}
	    controller = new PreautuacaoRecursalController(mockState, mockPreautuacaoRecursalService, [new Classe('HC', 'Habeas Corpus', [])]);
	});
	
	it('Deveria preautuar a remessa recursal', () => {
		let protocoloId = 123;
		let classe = 'RE';
		let sigiloProcesso = 'PUBLICO';
		let preferencias : Array<number> = [1,2,3];
		
		controller.protocoloId = protocoloId;
		controller.classe.id = classe;
		controller.preferenciasSelecionadas = preferencias;
		
		spyOn(mockPreautuacaoRecursalService, 'preautuarRecursal').and.callFake(() => $q.when());
		
		spyOn(mockState, 'go').and.callThrough();
		
		controller.preautuarProcessoRecursal();
		
		$rootScope.$apply();
		
		expect(mockPreautuacaoRecursalService.preautuarRecursal).toHaveBeenCalledWith(new PreautuarRecursalCommand(protocoloId, classe, sigiloProcesso, preferencias));
		
		expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas", {}, { reload: true	});
	});
	
});
