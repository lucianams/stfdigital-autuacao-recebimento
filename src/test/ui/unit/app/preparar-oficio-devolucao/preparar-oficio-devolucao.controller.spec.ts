import {PrepararOficioDevolucaoController} from "recebimento/preparar-oficio-devolucao/preparar-oficio-devolucao.controller";
import {MotivoDevolucao, Tag, SubstituicaoTag, Texto, PrepararOficioParaDevolucaoCommand} from "recebimento/preparar-oficio-devolucao/preparar-oficio-devolucao.service";
import {Modelo, TipoDocumento} from "recebimento/services/model";
import {Documento} from "recebimento/preparar-oficio-devolucao/documento";

describe('Teste do controlador preparar-oficio-devolucao.controller', () => {

	let $rootScope: ng.IRootScopeService;
	let $q: ng.IQService;

	let controller: PrepararOficioDevolucaoController;
	let mockState;
	let mockPrepararOficioDevolucaoService;
	let mockMessagesService;

	let protocolo: number;

	beforeEach(inject((_$rootScope_: ng.IRootScopeService, _$q_: ng.IQService) => {
		$rootScope = _$rootScope_;
		$q = _$q_;

		mockState = {
			go : () => {}
		};

		mockPrepararOficioDevolucaoService = {
			consultarModelosPorMotivo: () => {},
			extrairTags: () => {},
			gerarTextoComTags: () => {},
			finalizarDevolucao: () => {}
		};

		mockMessagesService = {
			error: () => {},
			success: () => {}
		};

		protocolo = 12345;

		controller = new PrepararOficioDevolucaoController(mockState, mockPrepararOficioDevolucaoService,
			[new MotivoDevolucao(123, "AI Intempestivo", [1, 2, 3])],
			protocolo, mockMessagesService);
	}));
	
	it('Deveria carregar os modelos de acordo com o motivo de devolução', () => {
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

	it('Deveria tratar o possível erro ao carregar os modelos de acordo com o motivo de devolução', () => {
		spyOn(mockPrepararOficioDevolucaoService, 'consultarModelosPorMotivo').and.callFake(() => $q.reject());
		spyOn(mockMessagesService, 'error').and.callThrough();

		// Escolhendo o motivo de devolução
		controller.motivoDevolucao = controller.motivosDevolucao[0];

		expect(controller.modelosSendoCarregados).toEqual(false, 'Modelos não deveriam estar sendo carregados');

		controller.carregarModelos();

		expect(controller.modelosSendoCarregados).toEqual(true, 'Modelos deveriam estar sendo carregados');

		$rootScope.$apply(); // Resolvendo promises

		expect(mockPrepararOficioDevolucaoService.consultarModelosPorMotivo).toHaveBeenCalledWith(123);

		expect(mockMessagesService.error).toHaveBeenCalledWith("Erro ao carregar os modelos.");
	});

	it('Deveria extrair as tags do documento do modelo escolhido', () => {
		let tags: Tag[] = [<Tag>{nome: 'Destinatário'}, <Tag>{nome: 'Vocativo'}];
		spyOn(mockPrepararOficioDevolucaoService, 'extrairTags').and.callFake(() => $q.when(tags));

		controller.modelo = <Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")};

		expect(controller.tagsSendoCarregadas).toEqual(false, 'Tags não deveriam estar sendo carregadas');

		controller.extrairTags();

		expect(controller.tagsSendoCarregadas).toEqual(true, 'Tags deveriam estar sendo carregadas.');

		$rootScope.$apply(); // Resolvendo promises

		expect(mockPrepararOficioDevolucaoService.extrairTags).toHaveBeenCalledWith(3);

		expect(controller.substituicoesTags).toEqual([new SubstituicaoTag('Destinatário', ''), new SubstituicaoTag('Vocativo', '')]);

		expect(controller.tagsSendoCarregadas).toEqual(false, "Tags não deveriam mais estar sendo carregadas");
	});

	it('Deveria tratar o possível erro ao extrair as tags do documento do modelo escolhido', () => {
		spyOn(mockPrepararOficioDevolucaoService, 'extrairTags').and.callFake(() => $q.reject());
		spyOn(mockMessagesService, 'error').and.callThrough();

		controller.modelo = <Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")};

		expect(controller.tagsSendoCarregadas).toEqual(false, 'Tags não deveriam estar sendo carregadas');

		controller.extrairTags();

		expect(controller.tagsSendoCarregadas).toEqual(true, 'Tags deveriam estar sendo carregadas.');

		$rootScope.$apply(); // Resolvendo promises

		expect(mockPrepararOficioDevolucaoService.extrairTags).toHaveBeenCalledWith(3);

		expect(mockMessagesService.error).toHaveBeenCalledWith("Erro ao carregar as tags.");
	});

	it('Deveria gerar o texto a partir das tags preenchidas', () => {
		let texto = <Texto>{id: 13, documentoId: 234};
		spyOn(mockPrepararOficioDevolucaoService, 'gerarTextoComTags').and.callFake(() => $q.when(texto));

		controller.modelo = <Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")};
		controller.substituicoesTags = [new SubstituicaoTag('Destinatário', 'Fulano'), new SubstituicaoTag('Vocativo', 'Excelentíssimo')];

		controller.gerarTexto();

		$rootScope.$apply();
		
		expect(controller.texto).toEqual(texto);
		expect(controller.documento).toEqual(<Documento>{id: texto.documentoId, nome: 'Documento de Devolução'});
		expect(controller.showEditor).toEqual(true, "Deveria ativar a exibição do editor");
	});

	it('Deveria tratar o possível erro ao gerar o texto a partir das tags preenchidas', () => {
		spyOn(mockPrepararOficioDevolucaoService, 'gerarTextoComTags').and.callFake(() => $q.reject());
		spyOn(mockMessagesService, 'error').and.callThrough();

		controller.modelo = <Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")};
		controller.substituicoesTags = [new SubstituicaoTag('Destinatário', 'Fulano'), new SubstituicaoTag('Vocativo', 'Excelentíssimo')];

		controller.gerarTexto();

		$rootScope.$apply();
		
		expect(mockMessagesService.error).toHaveBeenCalledWith("Erro ao gerar o texto.");
	});

	it('Deveria finalizar a edição do documento de devolução quando o callback for chamado', () => {
		spyOn(mockPrepararOficioDevolucaoService, 'finalizarDevolucao').and.callFake(() => $q.when());
		spyOn(mockMessagesService, 'success').and.callThrough();
		spyOn(mockState, 'go').and.callThrough();

		// Escolhendo o motivo de devolução e as outras informações necessárias para concluir.
		controller.motivoDevolucao = controller.motivosDevolucao[0];
		controller.modelo = <Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")};
		controller.texto = <Texto>{id: 13, documentoId: 234};

		controller.concluiuEdicao();

		$rootScope.$apply();

		expect(mockPrepararOficioDevolucaoService.finalizarDevolucao).toHaveBeenCalledWith(new PrepararOficioParaDevolucaoCommand(
			protocolo, 123, 1, 13
		));

		expect(mockState.go).toHaveBeenCalledWith('app.tarefas.minhas-tarefas', {}, { reload: true });
		expect(mockMessagesService.success).toHaveBeenCalledWith("Documento de devolução elaborado com sucesso!");
	});

	it('Deveria tratar o possível erro ao finalizar a edição do documento de devolução quando o callback for chamado', () => {
		spyOn(mockPrepararOficioDevolucaoService, 'finalizarDevolucao').and.callFake(() => $q.reject());
		spyOn(mockMessagesService, 'error').and.callThrough();
		spyOn(mockState, 'go').and.callThrough();

		// Escolhendo o motivo de devolução e as outras informações necessárias para concluir.
		controller.motivoDevolucao = controller.motivosDevolucao[0];
		controller.modelo = <Modelo>{id: 1, nome: "Modelo de teste", documento: 3, tipoDocumento: new TipoDocumento(7, "Ofício de devolução")};
		controller.texto = <Texto>{id: 13, documentoId: 234};

		controller.concluiuEdicao();

		$rootScope.$apply();

		expect(mockPrepararOficioDevolucaoService.finalizarDevolucao).toHaveBeenCalledWith(new PrepararOficioParaDevolucaoCommand(
			protocolo, 123, 1, 13
		));

		expect(mockState.go).not.toHaveBeenCalled();
		expect(mockMessagesService.error).toHaveBeenCalledWith("Erro ao concluir a elaboração do texto!");
	});

});