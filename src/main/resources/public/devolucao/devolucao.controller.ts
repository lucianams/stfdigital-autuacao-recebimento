import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import {DevolucaoService, MotivoDevolucao, Modelo, Tag, SubstituicaoTag, GerarTextoCommand, Texto, PrepararOficioParaDevolucaoCommand} from "./devolucao.service";
import {Documento} from "./documento";
import devolucao from "./devolucao.module";

export class DevolucaoController {
	
    static $inject = ['$state', 'app.novo-processo.devolucao.DevolucaoService', 'motivosDevolucao', 'protocolo'];
    
    public modelos: Modelo[];
    
    public motivoDevolucao: MotivoDevolucao;
    public modelo: Modelo;
    
    public substituicoesTags: SubstituicaoTag[];

    public texto: Texto;
    
    public showEditor: boolean = false;
    
    public editor: any = {};
    
    public documento: Documento;
    
    public edicaoIniciada: boolean = false;

    constructor(private $state: IStateService, private devolucaoService: DevolucaoService,
                public motivosDevolucao: MotivoDevolucao[], private protocolo: number) {
    	
    }
    
    public callbackEdicaoIniciada() {
    	this.edicaoIniciada = true;
    }
    
    public carregarModelos(): void {
    	this.devolucaoService.consultarModelosPorMotivo(this.motivoDevolucao.id).then(
    		(modelos: Modelo[]) => {
    			this.modelos = modelos;
    		}
    	)
    }
	
    public extrairTags(): void {
    	this.devolucaoService.extrairTags(this.modelo.documento).then(
    		(tags: Tag[]) => {
    			this.substituicoesTags = tags.map<SubstituicaoTag>((tag: Tag) => {
    				return new SubstituicaoTag(tag.nome, "");
    			});
    		}
    	);
    }
    
    public isTextoCriado(): boolean {
    	return this.edicaoIniciada;
    }
    
    public tagsCarregadas(): boolean {
    	return this.substituicoesTags != null;
    }
    
	concluiuEdicao() {
		this.devolucaoService.finalizarDevolucao(new PrepararOficioParaDevolucaoCommand(
			this.protocolo, this.motivoDevolucao.id, this.modelo.id, this.texto.id
		)).then(() => {
			this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
		});
	}
    
    public gerarTexto(): void {
    	this.devolucaoService.gerarTextoComTags(new GerarTextoCommand(this.modelo.id, this.substituicoesTags))
    		.then((texto: Texto) => {
    			this.texto = texto;
    			this.documento = {
					id: texto.documentoId,
					nome: 'Documento de Devolução'
				};
				this.showEditor = true;
    		});
    }
    
    public finalizarDevolucao(): void {
    	this.editor.api.salvar();
    }
    
}

devolucao.controller('app.novo-processo.devolucao.DevolucaoController', DevolucaoController);
export default devolucao;