import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import preautuacao from "./preautuacao.module";

export class PreautuarRemessaCommand {
    
    constructor(public protocoloId: number, 
                public classeId: String,
                public preferencias: Array<Object>) {}    
}

export class Remessa {
	constructor (public classe : String, public qtdVolumes : number, 
				 public qtdApensos : number, public formaRecebimento : String, 
				 public numeroSedex: String){}
}

export class Preferencia {
	constructor(public id : string, public nome: string ) {}
}

export class Processo{
	
	constructor (public processoId : number, public remessa : Object) {}
}

export class PreautuacaoService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public preautuar(peticao: PreautuarRemessaCommand): IPromise<any> {
        return this.$http.post(this.properties.url + ":" + this.properties.port + PreautuacaoService.apiRemessa + '/preautuacao', peticao);
    }
    
    public consultarRemessa(protocoloId : number) : IPromise<Remessa> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + PreautuacaoService.apiRemessa + '/' + protocoloId)
                .then((response: IHttpPromiseCallbackArg<Remessa>) => { 
                    return response.data; 
                });
    }
    
/*    public carregarPreferencias() : IPromise<Preferencia[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + PreautuacaoService.apiRemessas + '/preferencia')
                .then((response: IHttpPromiseCallbackArg<Preferencia[]>) => { 
                    return response.data; 
                });
    }
 */   
}

preautuacao.service('app.novo-processo.preautuacao.PreautuacaoService', PreautuacaoService);

export default preautuacao;