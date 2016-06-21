import {PeticaoFisicaController} from "recebimento/peticoes-fisicas/peticao-fisica.controller";
import {PeticaoFisicaService, PeticaoFisicaCommand, FormaRecebimento} from "recebimento/peticoes-fisicas/peticao-fisica.service";


describe('Teste do controlador peticao-fisica.controller', () => {
	
	let controller : PeticaoFisicaController;
	let $q : ng.IQService;
	let $rootScope : ng.IRootScopeService;
	let mockState;
	let mockPeticaoFisicaService;
	
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
		}
	    controller = new PeticaoFisicaController(mockState, mockPeticaoFisicaService, [new FormaRecebimento('MALOTE', 'Malote', false)]);
	});
	
	it('Deveria registrar o recebimento da petição física', () => {
		let formaRecebimento = 'MALOTE';
		let qtdApensos = 3;
		let qtdVolumes = 2
		let numeroSedex : string;
		let tipoProcesso = 'ORIGINARIO';
		
		controller.formaRecebimento = formaRecebimento;
		controller.qtdApensos = qtdApensos;
		controller.qtdVolumes = qtdVolumes;
		controller.tipoProcesso = tipoProcesso;
		
		spyOn(mockPeticaoFisicaService, 'registrar').and.callFake(() => $q.when());
		
		spyOn(mockState, 'go').and.callThrough();
		
		controller.registrarPeticao();
		
		$rootScope.$apply();
		
		expect(mockPeticaoFisicaService.registrar).toHaveBeenCalledWith(new PeticaoFisicaCommand(formaRecebimento, qtdVolumes, qtdApensos, numeroSedex, tipoProcesso));
		
		expect(mockState.go).toHaveBeenCalledWith("app.tarefas.minhas-tarefas", {}, { reload: true	});
	});
	
});
