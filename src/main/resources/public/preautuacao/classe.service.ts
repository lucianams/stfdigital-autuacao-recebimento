import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import preautuacao from "./preautuacao.module";

export class Classe {
	constructor(public id : string, public nome: string )	{}
}

export class ClasseService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public listar() : IPromise<Classe[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + ClasseService.apiRemessa + '/classe')
                .then((response: IHttpPromiseCallbackArg<Classe[]>) => { 
                    return response.data; 
                });
    }
}

preautuacao.service('app.novo-processo.preautuacao.ClasseService', ClasseService);
export default preautuacao;