import {PreparacaoOficioDevolucaoService, MotivoDevolucao, Tag, SubstituicaoTag, GerarTextoCommand, Texto, PrepararOficioParaDevolucaoCommand} from "./preparacao-oficio-devolucao.service";
import {Modelo} from "./../services/model";
import {Documento} from "./documento";
import preparacaoOficioDevolucao from "./preparacao-oficio-devolucao.module";

export class PreparacaoOficioDevolucaoController {
	
    static $inject = ['$state', 'app.recebimento.preparacao-oficio-devolucao.PreparacaoOficioDevolucaoService', 'motivosDevolucao', 'protocolo', 'messagesService'];
    
	public path = {id: 'tarefas.preparacao-oficio-devolucao', translation:'Elaborar Ofício de Devolução', uisref: 'app.tarefas.recebimento-preparacao-oficio-devolucao', parent: 'tarefas'};

    public modelos: Modelo[];
    
    public motivoDevolucao: MotivoDevolucao;
    public modelo: Modelo;
    
    public substituicoesTags: SubstituicaoTag[];

    public texto: Texto;
    
    public showEditor: boolean = false;
    
    public editor: any = {};
    
    public documento: Documento;
    
    public edicaoIniciada: boolean = false;
    
    public tagsSendoCarregadas: boolean = false;
    
    public modelosSendoCarregados: boolean = false;

    constructor(private $state: ng.ui.IStateService, private preparacaoOficioDevolucaoService: PreparacaoOficioDevolucaoService,
                public motivosDevolucao: MotivoDevolucao[], private protocolo: number, private messagesService: app.support.messaging.MessagesService) {
    	
    }
    
    public callbackEdicaoIniciada() {
    	this.edicaoIniciada = true;
    }
    
    public carregarModelos(): void {
    	this.modelosSendoCarregados = true;
    	this.preparacaoOficioDevolucaoService.consultarModelosPorMotivo(this.motivoDevolucao.id).then(
    		(modelos: Modelo[]) => {
    			this.modelos = modelos;
    		},
    		() => {
    			this.messagesService.error("Erro ao carregar os modelos.");
    		}
    	).finally(() => {
    		this.modelosSendoCarregados = false;
    	});
    }
	
    public extrairTags(): void {
    	this.tagsSendoCarregadas = true;
    	this.preparacaoOficioDevolucaoService.extrairTags(this.modelo.documento).then(
    		(tags: Tag[]) => {
    			this.substituicoesTags = tags.map<SubstituicaoTag>((tag: Tag) => {
    				return new SubstituicaoTag(tag.nome, "");
    			});
    		},
    		() => {
    			this.messagesService.error("Erro ao carregar as tags.");
    		}
    	).finally(() => {
    		this.tagsSendoCarregadas = false;
    	});
    }
    
    public isTextoCriado(): boolean {
    	return this.edicaoIniciada;
    }
    
    public tagsCarregadas(): boolean {
    	return this.substituicoesTags != null;
    }
    
	concluiuEdicao() {
		this.preparacaoOficioDevolucaoService.finalizarDevolucao(new PrepararOficioParaDevolucaoCommand(
			this.protocolo, this.motivoDevolucao.id, this.modelo.id, this.texto.id
		)).then(() => {
			this.$state.go('app.tarefas.minhas-tarefas');
			this.messagesService.success("Documento de devolução elaborado com sucesso!");
		}, () => {
			this.messagesService.error("Erro ao concluir a elaboração do texto!");
		});
	}
    
    public gerarTexto(): void {
    	this.preparacaoOficioDevolucaoService.gerarTextoComTags(new GerarTextoCommand(this.modelo.id, this.substituicoesTags))
    		.then((texto: Texto) => {
    			this.texto = texto;
    			this.documento = {
					id: texto.documentoId,
					nome: 'Documento de Devolução'
				};
				this.showEditor = true;
    		}, () => {
    			this.messagesService.error("Erro ao gerar o texto.");
    		});
    }
    
    public finalizarDevolucao(): void {
    	this.editor.api.salvar();
    }
    
}

preparacaoOficioDevolucao.controller('app.recebimento.preparacao-oficio-devolucao.PreparacaoOficioDevolucaoController', PreparacaoOficioDevolucaoController);
export default preparacaoOficioDevolucao;