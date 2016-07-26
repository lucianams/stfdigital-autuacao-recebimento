import {PreautuacaoController} from "recebimento/preautuacao/originario/preautuacao.controller";
import {PreautuacaoService} from "recebimento/preautuacao/originario/preautuacao.service";
import {DevolverRemessaCommand} from "recebimento/preautuacao/devolucao/devolucao.service";
import {Classe, Remessa} from 'recebimento/services/model';


describe('Teste do controlador preautuacao.controller', () => {
	
	let controller : PreautuacaoController;
	let $q : ng.IQService;
	let $rootScope : ng.IRootScopeService;
	let mockState;
	let mockPreautuacaoService;
	let mockMessagesService;
	let mockDevolucaoService;

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
			preautuarProcesso : () =>{}
		};
		mockMessagesService = {
			success: () => {}
		};
		mockDevolucaoService = {
			devolver : () => {},
		};
		let remessa: Remessa = new Remessa(123, 'HC', 4, 7, 'BALCAO', null);
	    controller = new PreautuacaoController(mockState, mockMessagesService, mockPreautuacaoService, mockDevolucaoService, [new Classe('HC', 'Habeas Corpus', [])], remessa, [{nome: "PUBLICO", descricao: "Público"}, {nome: "SEGREDO_JUSTICA", descricao: "Segredo de Justiça"}]);
	});
	
	it('Deveria devolver a remessa', () => {
		let motivoDevolucao = 'Motivo para devolução';
		controller.cmdDevolucao.motivo = motivoDevolucao;
		
		spyOn(mockDevolucaoService, 'devolver').and.callFake(() => $q.when());
		
		spyOn(mockState, 'go').and.callThrough();
		
		controller.devolver();
		
		$rootScope.$apply();
		
		expect(mockDevolucaoService.devolver).toHaveBeenCalledWith(controller.cmdDevolucao);
		
		expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas");
	});
	
});
