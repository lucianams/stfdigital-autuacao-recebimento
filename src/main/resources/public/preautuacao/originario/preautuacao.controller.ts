/**
 * @author Viniciusk
 */
import IStateService = angular.ui.IStateService;
import {Classe, Remessa, Preferencia} from "../../services/model";
import preautuacao from "./preautuacao.module";
import {PreautuacaoService, DevolverRemessaCommand} from "../../services/preautuacao.service";

export class PreautuacaoController {
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
		preautuacaoService.gerarRemessa("ORIGINARIO").then((protocoloId: number) => {
			this.protocoloId = protocoloId;
			
			preautuacaoService.consultarRemessa(this.protocoloId).then((remessa: Remessa) => {
				this.remessa = remessa;
			});		
		});

		preautuacaoService.listarClassesPorTipoRemessa("ORIGINARIO").then((classes: Classe[]) => {
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
	
	public devolver(): void {
		this.preautuacaoService.devolver(this.commandDevolucao())
			.then(() => {
			this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
		});
	}
	
	private commandDevolucao(): DevolverRemessaCommand {
		return new DevolverRemessaCommand(2, this.motivo);
	}
	
	/*
	 * Realiza a préautuação do processo recursal.
	 */
	public preautuarProcessoOriginario(): void {
		this.preautuacaoService.preautuarProcesso(this.protocoloId, this.classe.id, "PUBLICO", this.preferenciasSelecionadas)
	        .then(() => {
	            this.$state.go("app.tarefas.minhas-tarefas", {}, { reload: true	});
		});
	}
}

preautuacao.controller("app.novo-processo.preautuacao.PreautuacaoController", PreautuacaoController);
export default preautuacao;