import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia} from "../../services/model";
import preautuacao from "./preautuacao.module";
import {PreautuacaoService, DevolverRemessaCommand} from "./preautuacao.service";

/**
 * @author Viniciusk
 */

export class PreautuacaoController {
	public basicForm: Object = {};
	public classe : Classe;
	public preferencias : Array<Preferencia>;
	public preferenciasSelecionadas : Array<number>;
	public motivo : string;


	static $inject = ["$state", "app.recebimento.preautuacao-originario.PreautuacaoService", "classes", "remessa"];
	
    /** @ngInject **/
	constructor(private $state: IStateService, private preautuacaoService: PreautuacaoService, public classes : Classe[], public remessa: Remessa){

	}
    
    /*
	 * Carrega as preferências da classe selecionada.
	 * @return Array de objetos Preferencia.
	 */
	public carregarPreferencias(): void {
		 this.preferencias = this.classe.preferencias;
	}
	
	public devolver(): void {
		this.preautuacaoService.devolver(this.commandDevolucao())
			.then(() => {
			this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
		});
	}
	
	private commandDevolucao(): DevolverRemessaCommand {
		return new DevolverRemessaCommand(this.remessa.protocolo, this.motivo);
	}
	
	/*
	 * Realiza a préautuação do processo recursal.
	 */
	public preautuarProcessoOriginario(): void {
		this.preautuacaoService.preautuarProcesso(this.remessa.protocolo, this.classe.id, "PUBLICO", this.preferenciasSelecionadas)
	        .then(() => {
	            this.$state.go("app.tarefas.minhas-tarefas", {}, { reload: true	});
		});
	}
}

preautuacao.controller("app.recebimento.preautuacao-originario.PreautuacaoController", PreautuacaoController);
export default preautuacao;