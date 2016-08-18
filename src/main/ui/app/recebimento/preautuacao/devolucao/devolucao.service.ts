import devolucao from "./devolucao.module";
import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import cmd = app.support.command;
import Properties = app.support.constants.Properties;

export class DevolverRemessaCommand implements cmd.Command {
	public protocoloId: number;
	public motivo: string;

	constructor() {};
}

export class DevolucaoService {

    private static urlServicoPreautuacao: string = '/recebimento/api/remessas';
    
    /** @ngInject **/
    constructor(private $http: IHttpService, private properties : Properties,  commandService: cmd.CommandService) {
        commandService.addValidator('devolver-remessa', new ValidadorDevolucao());
    }

	public devolver(cmdDevolver: DevolverRemessaCommand): IPromise<any> {
		return this.$http.post(this.properties.apiUrl + DevolucaoService.urlServicoPreautuacao + '/devolucao', cmdDevolver);
	}
}

class ValidadorDevolucao implements cmd.CommandValidator {
	
	constructor() {}
	
	public isValid(command: DevolverRemessaCommand): boolean {
		if (command.motivo && command.motivo.length > 0) {
			return true;
		}
		return false;
	}

}

devolucao.service("app.recebimento.preautuacao-devolucao.DevolucaoService", DevolucaoService);
export default devolucao;