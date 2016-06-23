import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucaoAssinatura from "./devolucao-assinatura.module";
import {RemessaService} from "./../services/remessa.service";
import {Modelo} from "./../services/model";

export class AssinarOficioParaDevolucaoCommand {
	constructor(public protocoloId: number) {}
}

export class Devolucao {
	remessaProtocoloId: number;

    remessaNumero: number;
    remessaAno: number;

    modeloDevolucao: Modelo;

    textoId: number;

}

export interface Documento {
	documentoId: number;
	tamanho: number;
	quantidadePaginas: number;
}

export class DevolucaoAssinaturaService {

    private static apiRemessa: string = '/recebimento/api/remessas';
    private static apiTexto: string = '/documents/api/textos';

	static $inject = ['$http', 'properties', 'app.recebimento.services.RemessaService'];

    constructor(private $http: IHttpService, private properties, private teste) { }
    
    public montarUrlConteudoTexto(textoId: number): string {
    	return this.properties.apiUrl + DevolucaoAssinaturaService.apiTexto + '/' + textoId + '/conteudo.pdf';
    }
    
    public assinarOficioDevolucao(command: AssinarOficioParaDevolucaoCommand): IPromise<{}> {
    	return this.$http.post(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + '/devolucao-assinatura', command)
    	   .then((response: IHttpPromiseCallbackArg<any>) => {
    		   return response.data
    	   });
    }

	public consultarDevolucao(protocoloId: number): IPromise<Devolucao> {
		return this.$http.get(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + "/" + protocoloId + '/devolucao')
			.then((response: IHttpPromiseCallbackArg<Devolucao>) => {
				return response.data;
			});
	}

	public consultarDocumentoFinalDoTexto(textoId: number): IPromise<Documento> {
		return this.$http.get(this.properties.apiUrl + DevolucaoAssinaturaService.apiTexto + "/" + textoId + "/documento-final")
			.then((response: IHttpPromiseCallbackArg<Documento>) => {
				return response.data;
			});
	}
}

devolucaoAssinatura.service('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', DevolucaoAssinaturaService);
export default devolucaoAssinatura;