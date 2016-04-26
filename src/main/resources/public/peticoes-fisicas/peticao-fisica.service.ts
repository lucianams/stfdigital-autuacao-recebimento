import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import recebimento from "./peticao-fisica.module";

export class FormaRecebimento {
    
    constructor(public descricao: string, public exigeNumero: boolean ) {}
}

export class PeticaoFisica {
    
    constructor(private _formaRecebimento: string, 
                private _volumes: number,
                private _apensos: number,
                private _numeroSedex: string,
                private _tipoProcesso: string) { }    

	public get formaRecebimento(): string {
		return this._formaRecebimento;
	}

	public set formaRecebimento(value: string) {
		this._formaRecebimento = value;
	}

	public get volumes(): number {
		return this._volumes;
	}

	public set volumes(value: number) {
		this._volumes = value;
	}
    
	public get apensos(): number {
		return this._apensos;
	}

	public set apensos(value: number) {
		this._apensos = value;
	}

	public get numeroSedex(): string {
		return this._numeroSedex;
	}

	public set numeroSedex(value: string) {
		this._numeroSedex = value;
	}
    
	public get tipoProcesso(): string {
		return this._tipoProcesso;
	}

	public set tipoProcesso(value: string) {
		this._tipoProcesso = value;
	}     
    
}

export class PeticaoFisicaService {

    private static apiRemessa: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    public registrar(peticao: PeticaoFisica): IPromise<any> {            
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