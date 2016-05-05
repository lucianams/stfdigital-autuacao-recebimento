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
                public tipoProcesso: string) { }    
}

export class PeticaoFisicaService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public registrar(peticao: PeticaoFisicaCommand): IPromise<any> {            
        return this.$http.post(this.properties.url + ":" + this.properties.port + PeticaoFisicaService.apiRemessa, peticao);
    }
    
    public consultarFormasRecebimento() : IPromise<FormaRecebimento[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + PeticaoFisicaService.apiRemessa + '/formas-recebimento')
                .then((response: IHttpPromiseCallbackArg<FormaRecebimento[]>) => { 
                    return response.data; 
                });
    }
}

recebimento.service('app.novo-processo.peticoes-fisicas.PeticaoFisicaService', PeticaoFisicaService);
export default recebimento;