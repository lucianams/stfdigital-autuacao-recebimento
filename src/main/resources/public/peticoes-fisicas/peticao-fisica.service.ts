import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import recebimento from "./peticao-fisica.module";

export class FormaRecebimento {
    public static SEDEX = 'SEDEX';
    public static BALCAO = 'BALCAO';
    public static FAX = 'FAX';
}

export interface IPeticaoFisica {
    formaRecebimento: FormaRecebimento,
    volumes: number,
    apensos: number,
    numeroSedex: string,
    tipoProcesso: string
}

export class PeticaoFisicaService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public registrar(peticao: IPeticaoFisica): IPromise<any> {
        return this.$http.post(this.properties.url + ":" + this.properties.port + PeticaoFisicaService.apiRemessa, peticao);
    }
}

recebimento.service('app.novo-processo.recebimento.PeticaoFisicaService', PeticaoFisicaService);

export default recebimento;