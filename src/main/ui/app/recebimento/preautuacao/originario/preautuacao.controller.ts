import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia} from "../../services/model";
import preautuacao from "./preautuacao.module";
import {PreautuacaoService, DevolverRemessaCommand, PreautuarRemessaCommand} from "./preautuacao.service";

/**
 * @author Viniciusk
 */

export class PreautuacaoController {
	public basicForm: Object = {};
	public classe : Classe;
	public preferencias : Array<Preferencia> = [];

	public cmdPreautuar: PreautuarRemessaCommand = new PreautuarRemessaCommand();
	public cmdDevolucao: DevolverRemessaCommand = new DevolverRemessaCommand();

	static $inject = ["$state", "messagesService", "app.recebimento.preautuacao-originario.PreautuacaoService", "classes", "remessa"];

	constructor(private $state: IStateService, private messagesService: app.support.messaging.MessagesService, 
			private preautuacaoService: PreautuacaoService, public classes : Classe[], public remessa: Remessa){
		this.cmdPreautuar.sigilo = 'PUBLICO';
		this.cmdPreautuar.protocoloId = remessa.protocolo;
		this.cmdDevolucao.protocoloId = remessa.protocolo;
	}

    /*
	 * Carrega as preferências da classe selecionada.
	 * @return Array de objetos Preferencia.
	 */
	public carregarPreferencias(): void {
		 this.cmdPreautuar.classeId = this.classe.id;
		 this.preferencias = this.classe.preferencias;
	}
	
	public devolver(): void {
		this.preautuacaoService.devolver(this.cmdDevolucao)
		.then(() => {
            this.$state.go('app.tarefas.minhas-tarefas');
			this.messagesService.success('Remessa devolvida com sucesso.');
    	}, () => {
			this.messagesService.error('Erro ao devolver remessa.');
		});
	}	
	
	/*
	 * Realiza a preautuação do processo recursal.
	 */
	public preautuarProcessoOriginario(): void {
		this.preautuacaoService.preautuarProcesso(this.cmdPreautuar)
		.then(() => {
            this.$state.go('app.tarefas.minhas-tarefas');
			this.messagesService.success('Remessa preautuada com sucesso.');
    	}, () => {
			this.messagesService.error('Erro ao preautuar remessa.');
		});
	}
}

preautuacao.controller("app.recebimento.preautuacao-originario.PreautuacaoController", PreautuacaoController);
export default preautuacao;