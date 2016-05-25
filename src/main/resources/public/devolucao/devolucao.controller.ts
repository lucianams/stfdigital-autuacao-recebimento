import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import {DevolucaoService, MotivoDevolucao, Modelo, Tag, SubstituicaoTag, GerarTextoCommand, Texto} from "./devolucao.service";
import {Documento} from "./documento";
import devolucao from "./devolucao.module";

export class DevolucaoController {
	
    static $inject = ['$state', 'app.novo-processo.devolucao.DevolucaoService', 'motivosDevolucao'];
    
    public modelos: Modelo[];
    
    public motivoDevolucao: MotivoDevolucao;
    public modelo: Modelo;
    
    public substituicoesTags: SubstituicaoTag[];

    public texto: Texto;
    
    public showEditor: boolean = false;
    
    public editor: any = {};
    
    public documento: Documento;

    constructor(private $state: IStateService, private devolucaoService: DevolucaoService,
                public motivosDevolucao: MotivoDevolucao[]) { }
    
    public carregarModelos(): void {
    	console.log(this.motivoDevolucao);
    	this.devolucaoService.consultarModelosPorTiposDocumento(this.motivoDevolucao.tiposDocumento).then(
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
    	return false;
    }
    
    public tagsCarregadas(): boolean {
    	return this.substituicoesTags != null;
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
    
}

devolucao.controller('app.novo-processo.devolucao.DevolucaoController', DevolucaoController);
export default devolucao;