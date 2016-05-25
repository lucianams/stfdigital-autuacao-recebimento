import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucao from "./devolucao.module";

export class MotivoDevolucao {
	constructor(public id : string, public descricao: string, public tiposDocumento: number[]) {}
}

export class TipoDocumento {
	constructor(public id: number, public descricao: string) {}
}

export interface Modelo {
	id: number;
	tipoDocumento: TipoDocumento;
	nome: string;
	documento: number;
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

export class DevolucaoService {

    private static apiRemessa: string = '/recebimento/api/devolucao';
    private static apiModelos: string = '/documents/api/modelos';
    private static apiDocumentos: string = '/documents/api/documentos';
    private static apiTextos: string = '/documents/api/textos';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public listarMotivosDevolucao() : IPromise<MotivoDevolucao[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + DevolucaoService.apiRemessa + '/motivos-devolucao')
            .then((response: IHttpPromiseCallbackArg<MotivoDevolucao[]>) => { 
                return response.data; 
            });
    }
    
    public consultarModelosPorTiposDocumento(tiposDocumento: number[]): IPromise<Modelo[]> {
    	return this.$http.post(this.properties.apiUrl + DevolucaoService.apiModelos + '/por-tipos-documento', tiposDocumento)
    		.then((response: IHttpPromiseCallbackArg<Modelo[]>) => {
    			return response.data;
    		});
    }
    
    public extrairTags(idDocumento: number): IPromise<Tag[]> {
    	return this.$http.get(this.properties.apiUrl + DevolucaoService.apiDocumentos + "/" + idDocumento + "/tags")
    		.then((response: IHttpPromiseCallbackArg<Tag[]>) => {
    			return response.data;
    		});
    }
    
    public gerarTextoComTags(command: GerarTextoCommand) {
    	return this.$http.post(this.properties.apiUrl + DevolucaoService.apiTextos + "/gerar-texto", command)
    		.then((response: IHttpPromiseCallbackArg<Texto>) => {
    			return response.data;
    		});
    }
}

devolucao.service('app.novo-processo.devolucao.DevolucaoService', DevolucaoService);
export default devolucao;