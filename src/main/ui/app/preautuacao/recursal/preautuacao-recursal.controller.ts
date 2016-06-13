import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia} from "../../services/model";
import preautuacaoRecursal from "./preautuacao-recursal.module";
import {PreautuacaoService} from "../../services/preautuacao.service";

/**
 * Controlador responsável por mediar as interações entre o front-end e o back-end.
 * @author anderson.araujo
 * @since 23/05/2016
 */

export class PreautuacaoRecursalController {
	public basicForm: Object = {};
	public protocoloId: number;
	public remessa: Remessa;
	public classe : Classe;
	public classes: Array<Classe>;
	public preferencias : Array<Preferencia>;
	public preferenciasSelecionadas : Array<number>;
	public motivo : string;
	
	static $inject = ["$state", "app.novo-processo.preautuacao-services.PreautuacaoService"];
	
	/** @ngInject **/
	constructor(private $state: IStateService, private preautuacaoService: PreautuacaoService){
				
		/* Substituir pelo nº do protocolo passado como parâmetro. */
		preautuacaoService.gerarRemessa("RECURSAL").then((protocoloId: number) => {
			this.protocoloId = protocoloId;
			
			preautuacaoService.consultarRemessa(this.protocoloId).then((remessa: Remessa) => {
				this.remessa = remessa;
			});		
		});

		preautuacaoService.listarClassesPorTipoRemessa("RECURSAL").then((classes: Classe[]) => {
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
		this.preautuacaoService.preautuarRecursal(this.protocoloId, this.classe.id, "PUBLICO", this.preferenciasSelecionadas)
	        .then(() => {
	            this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true	});
	    	});
	}
}

preautuacaoRecursal.controller("app.novo-processo.preautuacao-recursal.PreautuacaoRecursalController", PreautuacaoRecursalController);
export default preautuacaoRecursal;