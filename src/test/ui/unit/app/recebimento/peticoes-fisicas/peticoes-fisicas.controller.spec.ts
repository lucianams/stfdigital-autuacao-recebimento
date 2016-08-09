import {PeticaoFisicaController} from "recebimento/peticoes-fisicas/peticao-fisica.controller";
import {PeticaoFisicaService, PeticaoFisicaCommand, FormaRecebimento} from "recebimento/peticoes-fisicas/peticao-fisica.service";


describe('Teste do controlador peticao-fisica.controller', () => {
	
	let controller : PeticaoFisicaController;
	let $q : ng.IQService;
	let $rootScope : ng.IRootScopeService;
	let mockState;
	let mockPeticaoFisicaService;
	let mockMessagesService;
	
	beforeEach(inject((_$q_, _$rootScope_) => {
        $q = _$q_;
        $rootScope = _$rootScope_;
	}));
	
	beforeEach(() => {
		mockState = {
			go : () => {}
		};
		mockPeticaoFisicaService = {
			registrar : () =>{}
		};
		mockMessagesService = {
			success: () => {}
		};
	    controller = new PeticaoFisicaController(mockState, mockPeticaoFisicaService, [new FormaRecebimento('MALOTE', 'Malote', false)], mockMessagesService, [{nome: "PUBLICO", descricao: "Público"}, {nome: "SEGREDO_JUSTICA", descricao: "Segredo de Justiça"}]);
	});
	
	it('Deveria registrar o recebimento da petição física', () => {
		let formaRecebimento = 'MALOTE';
		let qtdApensos = 3;
		let qtdVolumes = 2
		let numeroSedex : string;
		let tipoProcesso = 'ORIGINARIO';
		
		controller.cmd.formaRecebimento = formaRecebimento;
		controller.cmd.apensos = qtdApensos;
		controller.cmd.volumes = qtdVolumes;
		controller.cmd.tipoProcesso = tipoProcesso;
		controller.cmd.sigilo = 'PUBLICO';
		
		spyOn(mockPeticaoFisicaService, 'registrar').and.callFake(() => $q.when());
		
		spyOn(mockState, 'go').and.callThrough();
		
		controller.registrarRemessa();
		
		$rootScope.$apply();
		
		expect(mockPeticaoFisicaService.registrar).toHaveBeenCalledWith(controller.cmd);
		
		expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas");
	});
	
});
