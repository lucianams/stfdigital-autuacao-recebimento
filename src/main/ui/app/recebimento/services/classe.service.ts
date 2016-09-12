import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import services from "./services.module";
import {Classe, Preferencia} from "./model";

export class ClasseService {

    private static apiClasse: string = '/recebimento/api/classes';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public listar(tipoRemessa?: string) : IPromise<Classe[]> {
        let config: ng.IRequestShortcutConfig = { };
        if (tipoRemessa) { 
            config.params = { tipoRemessa : tipoRemessa };
        }
        config.cache = true;

        return this.$http.get(this.properties.apiUrl + ClasseService.apiClasse, config)
                .then((response: IHttpPromiseCallbackArg<Classe[]>) => {
                    return response.data;
                });
    }
}

services.service("app.recebimento.services.ClasseService", ClasseService);
export default services;