import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import {DevolucaoService, MotivoDevolucao, Modelo, Tag} from "./devolucao.service";
import devolucao from "./devolucao.module";

export class DevolucaoController {
	
    static $inject = ['$state', 'app.novo-processo.devolucao.DevolucaoService', 'motivosDevolucao'];
    
    public modelos: Modelo[];
    
    public motivoDevolucao: MotivoDevolucao;
    public modelo: Modelo;
    
    public tags: Tag[];
    
    public showEditor: boolean = false;

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
    			this.tags = tags;
    		}
    	);
    }
    
    public isTextoCriado(): boolean {
    	return false;
    }
    
    public tagsCarregadas(): boolean {
    	return this.tags != null;
    }
    
    public gerarTexto(): void {
    	
    }
    
}

devolucao.controller('app.novo-processo.devolucao.DevolucaoController', DevolucaoController);
export default devolucao;