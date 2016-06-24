import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IQService = angular.IQService;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucaoAssinatura from "./devolucao-assinatura.module";
import {Modelo} from "./../services/model";

export class AssinarOficioParaDevolucaoCommand {
	constructor(public protocoloId: number, public documentoTemporarioId) {}
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

	static $inject = ['$http', 'properties', '$q'];

    constructor(private $http: IHttpService, private properties: app.support.constants.Properties, private $q: IQService) { }
    
    public montarUrlConteudoTexto(textoId: number): string {
    	return this.properties.apiUrl + DevolucaoAssinaturaService.apiTexto + '/' + textoId + '/conteudo.pdf';
    }
    
    public assinarOficioDevolucao(command: AssinarOficioParaDevolucaoCommand): IPromise<{}> {
    	return this.$http.post(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + '/devolucao-assinatura', command)
    	   .then((response: IHttpPromiseCallbackArg<any>) => {
    		   return response.data
    	   });
    }

	public consultarDevolucoes(protocolos: number[]): IPromise<Devolucao[]> {
		let promises: IPromise<Devolucao>[] = [];
		for (let protocoloId of protocolos) {
			promises.push(this.$http.get(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + "/" + protocoloId + '/devolucao')
				.then((response: IHttpPromiseCallbackArg<Devolucao>) => {
					return response.data;
				}));
		}
		return this.$q.all(promises);
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