import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import recebimento from "./peticao-fisica.module";

export class FormaRecebimento {
    
    constructor(public descricao: string, public exigeNumero: boolean ) {}
}

export class PeticaoFisicaCommand {
    
    constructor(public formaRecebimento: string, 
                public volumes: number,
                public apensos: number,
                public numeroSedex: string,
                public tipoProcesso: string,
                public sigilo: string = 'PUBLICO') { }
}

export class PeticaoFisicaService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public registrar(peticao: PeticaoFisicaCommand): IPromise<any> {
        return this.$http.post(this.properties.url + ":" + this.properties.port + PeticaoFisicaService.apiRemessa + '/recebimento', peticao);
    }
    
    public consultarFormasRecebimento() : IPromise<FormaRecebimento[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + PeticaoFisicaService.apiRemessa + '/formas-recebimento')
                .then((response: IHttpPromiseCallbackArg<FormaRecebimento[]>) => { 
                    return response.data; 
                });
    }
}

recebimento.service('app.recebimento.peticoes-fisicas.PeticaoFisicaService', PeticaoFisicaService);
export default recebimento;