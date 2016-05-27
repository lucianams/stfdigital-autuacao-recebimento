import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import preautuacaoServices from "../services/services.module";
import {Classe, Preferencia} from "../services/model";

export class ClasseService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public listar(tipoRemessa: string) : IPromise<Classe[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port 
            + ClasseService.apiRemessa + "/classes/tipos-remessa/" + tipoRemessa)
                .then((response: IHttpPromiseCallbackArg<Classe[]>) => { 
                    return response.data; 
                });
    }
}

preautuacaoServices.service("app.novo-processo.preautuacao-services.ClasseService", ClasseService);
export default preautuacaoServices;