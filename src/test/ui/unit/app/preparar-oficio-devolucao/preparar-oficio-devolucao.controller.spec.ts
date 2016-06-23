import {PrepararOficioDevolucaoController} from "recebimento/preparar-oficio-devolucao/preparar-oficio-devolucao.controller";
import {MotivoDevolucao} from "recebimento/preparar-oficio-devolucao/preparar-oficio-devolucao.service";
import {Modelo, TipoDocumento} from "recebimento/services/model";

describe('Teste do controlador preparar-oficio-devolucao.controller', () => {

	let $rootScope: ng.IRootScopeService;
	let $q: ng.IQService;

	let controller: PrepararOficioDevolucaoController;
	let mockState;
	let mockPrepararOficioDevolucaoService;
	let mockMessagesService;

	beforeEach(inject((_$rootScope_: ng.IRootScopeService, _$q_: ng.IQService) => {
		$rootScope = _$rootScope_;
		$q = _$q_;

		mockState = {
			go : () => {}
		};

		mockPrepararOficioDevolucaoService = {
			consultarModelosPorMotivo: () => {}
		};

		mockMessagesService = {

		};

		controller = new PrepararOficioDevolucaoController(mockState, mockPrepararOficioDevolucaoService,
			[new MotivoDevolucao(123, "AI Intempestivo", [1, 2, 3])],
			12345, mockMessagesService);
	}));
	
	it('Deveria carregar os modelos de acordo com o motivo de devolução.', () => {
		let modelos: Modelo[] = [<Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")}];
		spyOn(mockPrepararOficioDevolucaoService, 'consultarModelosPorMotivo').and.callFake(() => $q.when(modelos));
		
		// Escolhendo o motivo de devolução
		controller.motivoDevolucao = controller.motivosDevolucao[0];

		expect(controller.modelosSendoCarregados).toEqual(false, 'Modelos não deveriam estar sendo carregados');

		controller.carregarModelos();

		expect(controller.modelosSendoCarregados).toEqual(true, 'Modelos deveriam estar sendo carregados');

		$rootScope.$apply(); // Resolvendo promises

		expect(mockPrepararOficioDevolucaoService.consultarModelosPorMotivo).toHaveBeenCalledWith(123);

		expect(controller.modelos).toEqual(modelos);

		expect(controller.modelosSendoCarregados).toEqual(false, 'Modelos não deveriam mais estar sendo carregados');
	});

	it('TODO: Deveria extrair as tags do documento do modelo escolhido', () => {

	});

	it('TODO: Deveria gerar o texto a partir das tags preenchidas', () => {

	});

	it('TODO: Deveria concluir a edição do documento de devolução', () => {

	});

});