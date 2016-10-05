import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import preautuacao from "./preautuacao.module";
import cmd = app.support.command;
import Properties = app.support.constants.Properties;

export class PreautuarRemessaCommand implements cmd.Command{
    public protocoloId: number;
    public classeId: string;
    public sigilo: string;
    public preferencias: Array<number>;
    public motivo: string;

    public constructor() { }
}

class ValidadorPreautuacao implements cmd.CommandValidator {

    public constructor() { }

    public isValid(command: PreautuarRemessaCommand): boolean {
        if (angular.isString(command.classeId) &&
            angular.isDefined(command.sigilo)) {
            return true;
        }
        return false;
    }

}

export class PreautuacaoService {

    private static urlServicoPreautuacao: string = "/recebimento/api/remessas";

    /** @ngInject **/
    public constructor(private $http: IHttpService, private properties: Properties,
            commandService: cmd.CommandService) {
        commandService.addValidator("preautuar-originario", new ValidadorPreautuacao());
    }

    /*
     * Envia os dados da preautuação para o serviço de recebimento (back-end).
     */
    public preautuarProcesso(cmdPreautuar: PreautuarRemessaCommand): IPromise<{}> {
        return this.$http.post(this.properties.url + ":" + this.properties.port +
                PreautuacaoService.urlServicoPreautuacao + "/preautuacao", cmdPreautuar);
    }
}

preautuacao.service("app.recebimento.preautuacao-originario.PreautuacaoService", PreautuacaoService);
export default preautuacao;