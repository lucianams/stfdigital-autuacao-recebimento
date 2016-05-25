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

export class GerarTextoPeticaoCommand {
	constructor(peticaoId: number, public modeloId: number, public substituicoes) {}
}

export class DevolucaoService {

    private static apiRemessa: string = '/recebimento/api/devolucao';
    private static apiModelos: string = '/documents/api/modelos';
    private static apiDocumentos: string = '/documents/api/documentos';

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
    
    public gerarTextoComTags(command: GerarTextoPeticaoCommand) {
    	
    }
}

devolucao.service('app.novo-processo.devolucao.DevolucaoService', DevolucaoService);
export default devolucao;