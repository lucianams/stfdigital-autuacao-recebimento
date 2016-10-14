import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;

import cmd = app.support.command;
import Properties = app.support.constants.Properties;

import preautuacaoRecursal from "./preautuacao-recursal.module";

export class PreautuarRecursalCommand {
    public protocoloId: number;
    public classeId: string;
    public sigilo: string;
    public preferencias: Array<number> = [];
    public motivo: string;
    public numeroProcessoOrigem: string;
    public numeroUnicoProcesso: string;

    public constructor() {}
}

class ValidadorPreautuacao implements cmd.CommandValidator {

    public constructor() {}

    public isValid(command: PreautuarRecursalCommand): boolean {
        if (angular.isString(command.classeId) &&
            command.preferencias.length > 0) {
            return true;
        }
        return false;
    }

}

export class PreautuacaoRecursalService {

    private static urlServicoPreautuacao: string = "/recebimento/api/remessas";

    /** @ngInject **/
    public constructor(private $http: IHttpService, private properties: Properties,
            commandService: cmd.CommandService) {
        commandService.addValidator("preautuar-recursal", new ValidadorPreautuacao());
    }

    /*
     * Envia os dados da préautuação para o serviço de recebimento (back-end).
     */
    public preautuarRecursal(cmd: PreautuarRecursalCommand): IPromise<{}> {
        return this.$http.put(this.properties.apiUrl + PreautuacaoRecursalService.urlServicoPreautuacao +
                "/" + cmd.protocoloId  + "/preautuacao-recursal", cmd);
    }

}

preautuacaoRecursal.service("app.recebimento.preautuacao-recursal.PreautuacaoRecursalService",
        PreautuacaoRecursalService);
export default preautuacaoRecursal;