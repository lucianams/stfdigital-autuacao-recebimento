import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia, Sigilo} from "../../services/model";
import preautuacaoRecursal from "./preautuacao-recursal.module";
import {PreautuacaoRecursalService, PreautuarRecursalCommand} from "./preautuacao-recursal.service";
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
	
	static $inject = ["$state", "app.recebimento.preautuacao-recursal.PreautuacaoRecursalService", "classes", "remessa", "sigilos", "messagesService"];

	constructor(private $state: IStateService, private preautuacaoRecursalService: PreautuacaoRecursalService, public classes: Classe[], public remessa: Remessa,
		public sigilos: Sigilo[], private messagesService: app.support.messaging.MessagesService){
		this.cmdPreautuar.protocoloId = remessa.protocolo;
	}
	
	/*
	 * Carrega as preferências da classe selecionada.
	 * @return Array de objetos Preferencia.
	 */
	public carregarPreferencias(): void {
		 this.cmdPreautuar.classeId = this.classe.id;
		 this.preferencias = this.classe.preferencias;
	}
	
	/*
	 * Realiza a préautuação do processo recursal.
	 */
	
	public preautuarProcessoRecursal(): void {

		this.preautuacaoRecursalService.preautuarRecursal(this.cmdPreautuar)
	        .then(() => {
	            this.$state.go('app.tarefas.minhas-tarefas');
				this.messagesService.success('Remessa recursal preautuada com sucesso.');
	    	}, () => {
				this.messagesService.error('Erro ao preautuar remessa recursal.');
			});
	}
}

preautuacaoRecursal.controller("app.recebimento.preautuacao-recursal.PreautuacaoRecursalController", PreautuacaoRecursalController);
export default preautuacaoRecursal;