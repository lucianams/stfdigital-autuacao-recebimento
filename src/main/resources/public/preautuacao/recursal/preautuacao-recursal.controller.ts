/**
 * Controlador responsável por mediar as interações entre o front-end e o back-end.
 * @author anderson.araujo
 * @since 23/05/2016
 */

import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia} from "../../services/model";
import preautuacaoRecursal from "./preautuacao-recursal.module";
import {PreautuacaoRecursalService} from "./preautuacao-recursal.service";
import {ClasseService} from "../../services/classe.service";

export class PreautuacaoRecursalController {
	public basicForm: Object = {};
	public protocoloId: number;
	public remessa: Remessa;
	public classe : Classe;
	public classes: Array<Classe>;
	public preferencias : Array<Preferencia>;
	public preferenciasSelecionadas : Array<number>;
	public motivo : string;
	
	static $inject = ["$state", 
		"app.novo-processo.preautuacao-recursal.PreautuacaoRecursalService", 
		"app.novo-processo.preautuacao-services.ClasseService"];
	
	/** @ngInject **/
	constructor(private $state: IStateService, private preautuacaoRecursalService: PreautuacaoRecursalService,
		private classeService: ClasseService){
				
		/* Substituir pelo nº do protocolo passado como parâmetro. */
		preautuacaoRecursalService.gerarRemessa().then((protocoloId: number) => {
			this.protocoloId = protocoloId;
			
			preautuacaoRecursalService.consultarRemessa(this.protocoloId).then((remessa: Remessa) => {
				this.remessa = remessa;
			});		
		});

		classeService.listar("RECURSAL").then((classes: Classe[]) => {
			this.classes = classes;
		});
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
		this.preautuacaoRecursalService.preautuarProcessoRecursal(this.protocoloId, this.classe.id, "PUBLICO", this.preferenciasSelecionadas)
	        .then(() => {
	            this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true	});
	    	});
	}
}

preautuacaoRecursal.controller("app.novo-processo.preautuacao-recursal.PreautuacaoRecursalController", PreautuacaoRecursalController);
export default preautuacaoRecursal;