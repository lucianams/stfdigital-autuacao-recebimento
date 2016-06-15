import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucaoAssinatura from "./devolucao-assinatura.module";

export class AssinarOficioParaDevolucaoCommand {
	constructor(public protocoloId: number) {}
}

export class DevolucaoAssinaturaService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }
    
    public assinarOficioDevolucao(command: AssinarOficioParaDevolucaoCommand): IPromise<{}> {
    	return this.$http.post(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + "/devolucao-assinatura", command)
    	   .then((response: IHttpPromiseCallbackArg<any>) => {
    		   return response.data
    	   });
    }
}

devolucaoAssinatura.service('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', DevolucaoAssinaturaService);
export default devolucaoAssinatura;