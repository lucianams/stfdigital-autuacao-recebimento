import {PreautuacaoController} from "recebimento/preautuacao/originario/preautuacao.controller";
import {PreautuacaoService, DevolverRemessaCommand} from "recebimento/preautuacao/originario/preautuacao.service";
import {Classe, Remessa} from 'recebimento/services/model';


describe('Teste do controlador preautuacao.controller', () => {
	
	let controller : PreautuacaoController;
	let $q : ng.IQService;
	let $rootScope : ng.IRootScopeService;
	let mockState;
	let mockPreautuacaoService;

	let protocoloId: number;
	
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
		};
		protocoloId = 123;
		let remessa: Remessa = new Remessa(protocoloId, 'HC', 4, 7, 'BALCAO', null);
	    controller = new PreautuacaoController(mockState, mockPreautuacaoService, [new Classe('HC', 'Habeas Corpus', [])], remessa);
	});
	
	it('Deveria devolver a remessa', () => {
		let motivoDevolucao = 'Motivo para devolução';
		controller.motivo = motivoDevolucao;
		
		spyOn(mockPreautuacaoService, 'devolver').and.callFake(() => $q.when());
		
		spyOn(mockState, 'go').and.callThrough();
		
		controller.devolver();
		
		$rootScope.$apply();
		
		expect(mockPreautuacaoService.devolver).toHaveBeenCalledWith(new DevolverRemessaCommand(protocoloId, motivoDevolucao));
		
		expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas", {}, { reload: true	});
	});
	
});
