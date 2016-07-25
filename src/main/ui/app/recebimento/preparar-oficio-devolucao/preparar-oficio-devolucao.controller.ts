import {PrepararOficioDevolucaoService, MotivoDevolucao, Tag, SubstituicaoTag, GerarTextoCommand, Texto, PrepararOficioParaDevolucaoCommand} from "./preparar-oficio-devolucao.service";
import {Modelo} from "./../services/model";
import {Documento} from "./documento";
import prepararOficioDevolucao from "./preparar-oficio-devolucao.module";

export class PrepararOficioDevolucaoController {
	
    static $inject = ['$state', 'app.recebimento.preparar-oficio-devolucao.PrepararOficioDevolucaoService', 'motivosDevolucao', 'protocolo', 'messagesService'];
    
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

    constructor(private $state: ng.ui.IStateService, private prepararOficioDevolucaoService: PrepararOficioDevolucaoService,
                public motivosDevolucao: MotivoDevolucao[], private protocolo: number, private messagesService: app.support.messaging.MessagesService) {
    	
    }
    
    public callbackEdicaoIniciada() {
    	this.edicaoIniciada = true;
    }
    
    public carregarModelos(): void {
    	this.modelosSendoCarregados = true;
    	this.prepararOficioDevolucaoService.consultarModelosPorMotivo(this.motivoDevolucao.id).then(
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
    	this.prepararOficioDevolucaoService.extrairTags(this.modelo.documento).then(
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
		this.prepararOficioDevolucaoService.finalizarDevolucao(new PrepararOficioParaDevolucaoCommand(
			this.protocolo, this.motivoDevolucao.id, this.modelo.id, this.texto.id
		)).then(() => {
			this.$state.go('app.tarefas.minhas-tarefas');
			this.messagesService.success("Documento de devolução elaborado com sucesso!");
		}, () => {
			this.messagesService.error("Erro ao concluir a elaboração do texto!");
		});
	}
    
    public gerarTexto(): void {
    	this.prepararOficioDevolucaoService.gerarTextoComTags(new GerarTextoCommand(this.modelo.id, this.substituicoesTags))
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

prepararOficioDevolucao.controller('app.recebimento.preparar-oficio-devolucao.PrepararOficioDevolucaoController', PrepararOficioDevolucaoController);
export default prepararOficioDevolucao;