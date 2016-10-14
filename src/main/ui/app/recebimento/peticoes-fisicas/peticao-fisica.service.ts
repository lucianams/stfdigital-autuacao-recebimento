import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;

import cmd = app.support.command;
import Properties = app.support.constants.Properties;

import recebimento from "./peticao-fisica.module";

export class FormaRecebimento {

    public constructor(public sigla: string, public descricao: string, public exigeNumero: boolean ) { }

}

export class PeticaoFisicaCommand implements cmd.Command {

    public formaRecebimento: string;
    public volumes: number;
    public apensos: number;
    public numeroSedex: string;
    public tipoProcesso: string;
    public sigilo: string;

    public constructor() { }

}

class ValidadorRemessa implements cmd.CommandValidator {

    public constructor() { }

    public isValid(command: PeticaoFisicaCommand): boolean {
        if (angular.isNumber(command.apensos) &&
            angular.isNumber(command.volumes) &&
            this.validarTipoProcesso(command.tipoProcesso) &&
            this.validarFormaRecebimento(command.formaRecebimento, command.numeroSedex) &&
            angular.isDefined(command.sigilo)) {
            return true;
        }
        return false;
    }

    private validarTipoProcesso(tipoProcesso: string): boolean {
        if (tipoProcesso === "ORIGINARIO" || tipoProcesso === "RECURSAL") {
            return true;
        }
        return false;
    }

    private validarFormaRecebimento(siglaFormaRecebimento: string, numeroSedex: string): boolean {
        if (siglaFormaRecebimento &&
                ((siglaFormaRecebimento === "SEDEX" && numeroSedex) ||
                (siglaFormaRecebimento !== "SEDEX" && !numeroSedex))) {
            return true;
        }
        return false;
    }
}

export class PeticaoFisicaService {

    private static apiRemessa: string = "/recebimento/api/remessas";

    /** @ngInject **/
    public constructor(private $http: IHttpService, private properties: Properties,
            commandService: cmd.CommandService) {
        commandService.addValidator("registrar-remessa", new ValidadorRemessa());
    }

    public registrar(peticao: PeticaoFisicaCommand): IPromise<{}> {
        return this.$http.post(this.properties.apiUrl + PeticaoFisicaService.apiRemessa, peticao);
    }

    public consultarFormasRecebimento(): IPromise<FormaRecebimento[]> {
        return this.$http.get(this.properties.apiUrl + PeticaoFisicaService.apiRemessa +
                "/formas-recebimento")
            .then((response: IHttpPromiseCallbackArg<FormaRecebimento[]>) => {
                return response.data;
            });
    }
}

recebimento.service("app.recebimento.peticoes-fisicas.PeticaoFisicaService", PeticaoFisicaService);
export default recebimento;