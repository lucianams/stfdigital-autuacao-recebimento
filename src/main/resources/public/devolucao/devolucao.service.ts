import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import devolucao from "./devolucao.module";

export class MotivoDevolucao {
	constructor(public id : string, public descricao: string )	{}
}

export class DevolucaoService {

    private static apiRemessa: string = '/recebimento/api/devolucao';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public listarMotivosDevolucao() : IPromise<MotivoDevolucao[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + DevolucaoService.apiRemessa + '/motivos-devolucao')
            .then((response: IHttpPromiseCallbackArg<MotivoDevolucao[]>) => { 
                return response.data; 
            });
    }
}

devolucao.service('app.novo-processo.devolucao.DevolucaoService', DevolucaoService);
export default devolucao;