import {PreautuacaoController} from "recebimento/preautuacao/originario/preautuacao.controller";
import {PreautuacaoService, DevolverRemessaCommand} from "recebimento/services/preautuacao.service";
import {Classe} from 'recebimento/services/model';


describe('Teste do controlador preautuacao.controller', () => {
	
	let controller : PreautuacaoController;
	let $q : ng.IQService;
	let $rootScope : ng.IRootScopeService;
	let mockState;
	let mockPreautuacaoService;
	
	beforeEach(inject((_$q_, _$rootScope_) => {
        $q = _$q_;
        $rootScope = _$rootScope_;
	}));
	
	beforeEach(() => {
		mockState = {
			go : () => {}
		};
		mockPreautuacaoService = {
			devolver : () =>{},
			preautuarProcesso : () =>{}
		}
	    controller = new PreautuacaoController(mockState, mockPreautuacaoService, [new Classe('HC', 'Habeas Corpus', [])]);
	});
	
	it('Deveria devolver a remessa', () => {
		let protocoloId = 123;
		let motivoDevolucao = 'Motivo para devolução';
		controller.protocoloId = protocoloId;
		controller.motivo = motivoDevolucao;
		
		spyOn(mockPreautuacaoService, 'devolver').and.callFake(() => $q.when());
		
		spyOn(mockState, 'go').and.callThrough();
		
		controller.devolver();
		
		$rootScope.$apply();
		
		expect(mockPreautuacaoService.devolver).toHaveBeenCalledWith(new DevolverRemessaCommand(protocoloId, motivoDevolucao));
		
		expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas", {}, { reload: true	});
	});
	
});
