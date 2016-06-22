import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia} from "../../services/model";
import preautuacaoRecursal from "./preautuacao-recursal.module";
import {PreautuacaoRecursalService} from "./preautuacao-recursal.service";
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
	public preferenciasSelecionadas : Array<number>;
	public motivo : string;

	static $inject = ["$state", "app.recebimento.preautuacao-recursal.PreautuacaoRecursalService", "classes", "remessa"];
	
	/** @ngInject **/
	constructor(private $state: IStateService, private preautuacaoRecursalService: PreautuacaoRecursalService, public classes: Classe[], public remessa: Remessa){

	}
	
	/*
	 * Carrega as preferências da classe selecionada.
	 * @return Array de objetos Preferencia.
	 */
	public carregarPreferencias(): void {
		 this.preferencias = this.classe.preferencias;
	}
	
	/*
	 * Realiza a préautuação do processo recursal.
	 */
	
	public preautuarProcessoRecursal(): void {

		this.preautuacaoRecursalService.preautuarRecursal(this.remessa.protocolo, this.classe.id, "PUBLICO", this.preferenciasSelecionadas)
	        .then(() => {
	            this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true	});
	    	});
	}
}

preautuacaoRecursal.controller("app.recebimento.preautuacao-recursal.PreautuacaoRecursalController", PreautuacaoRecursalController);
export default preautuacaoRecursal;