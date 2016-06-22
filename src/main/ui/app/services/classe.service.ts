import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import services from "./services.module";
import {Classe, Preferencia} from "./model";

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

services.service("app.recebimento.services.ClasseService", ClasseService);
export default services;