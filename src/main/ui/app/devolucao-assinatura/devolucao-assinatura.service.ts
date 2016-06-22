import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucaoAssinatura from "./devolucao-assinatura.module";
import {PreautuacaoService} from "./../services/preautuacao.service";
import {Modelo} from "./../services/model";

export class AssinarOficioParaDevolucaoCommand {
	constructor(public protocoloId: number) {}
}

export class Devolucao {
    public remessaNumero: number;
    public remessaAno: number;

    public modeloDevolucao: Modelo;

    public textoId: number;

}

export class DevolucaoAssinaturaService {

    private static apiRemessa: string = '/recebimento/api/remessas';
    private static apiTexto: string = '/documents/api/textos'

	static $inject = ['$http', 'properties', 'app.recebimento.preautuacao-services.PreautuacaoService'];

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
}

devolucaoAssinatura.service('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', DevolucaoAssinaturaService);
export default devolucaoAssinatura;