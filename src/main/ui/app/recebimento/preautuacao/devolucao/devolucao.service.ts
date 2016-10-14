import IPromise = angular.IPromise;
import IHttpService = angular.IHttpService;

import cmd = app.support.command;
import Properties = app.support.constants.Properties;

import devolucao from "./devolucao.module";

export class DevolverRemessaCommand implements cmd.Command {
    public protocoloId: number;
    public motivo: string;

    public constructor() {};
}

class ValidadorDevolucao implements cmd.CommandValidator {

    public constructor() {}

    public isValid(command: DevolverRemessaCommand): boolean {
        if (command.motivo && command.motivo.length > 0) {
            return true;
        }
        return false;
    }

}

export class DevolucaoService {

    private static urlServicoPreautuacao: string = "/recebimento/api/remessas";

    /** @ngInject **/
    public constructor(private $http: IHttpService, private properties: Properties,
            commandService: cmd.CommandService) {
        commandService.addValidator("devolver-remessa", new ValidadorDevolucao());
    }

    public devolver(cmdDevolver: DevolverRemessaCommand): IPromise<{}> {
        return this.$http.post(this.properties.apiUrl + DevolucaoService.urlServicoPreautuacao +
                "/" + cmdDevolver.protocoloId + "/devolucao", cmdDevolver);
    }
}

devolucao.service("app.recebimento.preautuacao-devolucao.DevolucaoService", DevolucaoService);

export default devolucao;