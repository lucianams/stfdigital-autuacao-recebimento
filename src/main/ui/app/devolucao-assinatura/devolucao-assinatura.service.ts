import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucaoAssinatura from "./devolucao-assinatura.module";
import {PreautuacaoService} from "./../services/preautuacao.service";

export class AssinarOficioParaDevolucaoCommand {
	constructor(public protocoloId: number) {}
}

export class DevolucaoAssinaturaService {

    private static apiRemessa: string = '/recebimento/api/remessas';

	static $inject = ['$http', 'properties', 'app.recebimento.preautuacao-services.PreautuacaoService'];

    constructor(private $http: IHttpService, private properties, private teste) { }
    
    public assinarOficioDevolucao(command: AssinarOficioParaDevolucaoCommand): IPromise<{}> {
    	return this.$http.post(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + "/devolucao-assinatura", command)
    	   .then((response: IHttpPromiseCallbackArg<any>) => {
    		   return response.data
    	   });
    }
}

devolucaoAssinatura.service('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', DevolucaoAssinaturaService);
export default devolucaoAssinatura;