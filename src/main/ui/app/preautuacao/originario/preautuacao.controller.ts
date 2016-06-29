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
	public cmdDevolucao : DevolverRemessaCommand = new DevolverRemessaCommand();

	static $inject = ["$state", "app.recebimento.preautuacao-originario.PreautuacaoService", "classes", "remessa"];
	
    /** @ngInject **/
	constructor(private $state: IStateService, private preautuacaoService: PreautuacaoService, public classes : Classe[], public remessa: Remessa){
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
		});
	}	
	
	/*
	 * Realiza a preautuação do processo recursal.
	 */
	public preautuarProcessoOriginario(): void {
		this.preautuacaoService.preautuarProcesso(this.cmdPreautuar)
	        .then(() => {
	            this.$state.go("app.tarefas.minhas-tarefas");
		});
	}
}

preautuacao.controller("app.recebimento.preautuacao-originario.PreautuacaoController", PreautuacaoController);
export default preautuacao;