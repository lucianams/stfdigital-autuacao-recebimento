import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia, Sigilo} from "../../services/model";
import preautuacaoRecursal from "./preautuacao-recursal.module";
import {PreautuacaoRecursalService, PreautuarRecursalCommand} from "./preautuacao-recursal.service";
import {DevolucaoService, DevolverRemessaCommand} from "../devolucao/devolucao.service";
import "./preautuacao-recursal.service";

/**
 * Controlador responsável por mediar as interações entre o front-end e o back-end.
 * @author anderson.araujo
 * @since 23/05/2016
 */

export class PreautuacaoRecursalController {
	public basicForm: Object = {};
	public classe : Classe;
	public preferencias : Array<Preferencia>;

	public cmdPreautuar: PreautuarRecursalCommand = new PreautuarRecursalCommand();
	public cmdDevolver: DevolverRemessaCommand = new DevolverRemessaCommand();

	public path = {id: 'tarefas.preautuacao-recursal', translation:'Preautuação Recursal', uisref: 'app.tarefas.recebimento-preautuacao-recursal', parent: 'tarefas'};
	
	static $inject = ["$state",
		"app.recebimento.preautuacao-recursal.PreautuacaoRecursalService", "app.recebimento.preautuacao-devolucao.DevolucaoService",
		"classes", "remessa", "sigilos", "messagesService"];

	constructor(private $state: IStateService,
		private preautuacaoRecursalService: PreautuacaoRecursalService, private devolucaoService: DevolucaoService,
		public classes: Classe[], public remessa: Remessa, public sigilos: Sigilo[], private messagesService: app.support.messaging.MessagesService) {
		this.cmdPreautuar.protocoloId = remessa.protocolo;
		this.cmdPreautuar.sigilo = remessa.sigilo;
		this.cmdDevolver.protocoloId = remessa.protocolo;
	}
	
	/*
	 * Carrega as preferências da classe selecionada.
	 * @return Array de objetos Preferencia.
	 */
	public carregarPreferencias(): void {
		 this.cmdPreautuar.classeId = this.classe.sigla;
		 this.preferencias = this.classe.preferencias;
	}
	
	/*
	 * Realiza a préautuação do processo recursal.
	 */
	
	public preautuarProcessoRecursal(): ng.IPromise<any> {
		return this.preautuacaoRecursalService.preautuarRecursal(this.cmdPreautuar)
	        .then(() => {
				this.messagesService.success('Remessa recursal preautuada com sucesso.');
				return this.$state.go('app.tarefas.minhas-tarefas');
	    	}, () => {
				this.messagesService.error('Erro ao preautuar remessa recursal.');
			});
	}

	public devolver(): ng.IPromise<any> {
		return this.devolucaoService.devolver(this.cmdDevolver)
			.then(() => {
				this.messagesService.success('Remessa devolvida com sucesso.');
				return this.$state.go('app.tarefas.minhas-tarefas');
			}, () => {
				this.messagesService.error('Erro ao devolver remessa.');
			});
	}	
}

preautuacaoRecursal.controller("app.recebimento.preautuacao-recursal.PreautuacaoRecursalController", PreautuacaoRecursalController);
export default preautuacaoRecursal;