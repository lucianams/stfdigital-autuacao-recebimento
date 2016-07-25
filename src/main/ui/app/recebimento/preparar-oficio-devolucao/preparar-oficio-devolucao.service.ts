import devolucao from "./preparar-oficio-devolucao.module";
import {Modelo} from "../services/model";

export class MotivoDevolucao {
	constructor(public id : number, public descricao: string, public tiposDocumento: number[]) {}
}

export interface Tag {
	nome: string;
}

export class SubstituicaoTag {
	constructor(public nome: string, public valor: string){}
}

export interface Texto {
	id: number;
	documentoId: number;
}

export class GerarTextoCommand {
	constructor(public modeloId: number, public substituicoes: SubstituicaoTag[]) {}
}

export class PrepararOficioParaDevolucaoCommand {
	constructor(public protocoloId: number, public motivo: number, public modeloId: number, public textoId: number) {}
}

export class PrepararOficioDevolucaoService {

    private static apiRemessaDevolucao: string = '/recebimento/api/devolucao';
    private static apiRemessa: string = '/recebimento/api/remessas';
    private static apiModelos: string = '/documents/api/modelos';
    private static apiDocumentos: string = '/documents/api/documentos';
    private static apiTextos: string = '/documents/api/textos';

    /** @ngInject **/
    constructor(private $http: ng.IHttpService, private properties) { }

    public listarMotivosDevolucao(): ng.IPromise<MotivoDevolucao[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + PrepararOficioDevolucaoService.apiRemessaDevolucao + '/motivos-devolucao')
            .then((response: ng.IHttpPromiseCallbackArg<MotivoDevolucao[]>) => { 
                return response.data; 
            });
    }
    
    public consultarModelosPorMotivo(idMotivo: number): ng.IPromise<Modelo[]> {
    	return this.$http.get(this.properties.apiUrl + PrepararOficioDevolucaoService.apiRemessaDevolucao + '/motivos-devolucao/' + idMotivo + '/modelos')
    		.then((response: ng.IHttpPromiseCallbackArg<Modelo[]>) => {
    			return response.data;
    		});
    }
    
    public extrairTags(idDocumento: number): ng.IPromise<Tag[]> {
    	return this.$http.get(this.properties.apiUrl + PrepararOficioDevolucaoService.apiDocumentos + "/" + idDocumento + "/tags")
    		.then((response: ng.IHttpPromiseCallbackArg<Tag[]>) => {
    			return response.data;
    		});
    }
    
    public gerarTextoComTags(command: GerarTextoCommand) {
    	return this.$http.post(this.properties.apiUrl + PrepararOficioDevolucaoService.apiTextos + "/gerar-texto", command)
    		.then((response: ng.IHttpPromiseCallbackArg<Texto>) => {
    			return response.data;
    		});
    }
    
    public finalizarDevolucao(command: PrepararOficioParaDevolucaoCommand): ng.IPromise<any> {
    	return this.$http.post(this.properties.apiUrl + PrepararOficioDevolucaoService.apiRemessa + "/devolucao-oficio", command);
    }
}

devolucao.service('app.recebimento.preparar-oficio-devolucao.PrepararOficioDevolucaoService', PrepararOficioDevolucaoService);
export default devolucao;