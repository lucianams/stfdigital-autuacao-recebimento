import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import preautuacaoRecursal from "./preautuacao-recursal.module";
import cmd = app.support.command;
import Properties = app.support.constants.Properties;

export class PreautuarRecursalCommand {
    public protocoloId: number; 
    public classeId: string;
    public sigilo: string
    public preferencias: Array<number> = [];
    public motivo : string;
    public numeroProcessoOrigem: string;
    public numeroUnicoProcesso: string;
    
    constructor () {};
}

export class PreautuacaoRecursalService {
    
    private static urlServicoPreautuacao: string = '/recebimento/api/remessas';
    
    /** @ngInject **/
    constructor(private $http: IHttpService, private properties : Properties, commandService: cmd.CommandService) {
    	commandService.addValidator('preautuar-recursal', new ValidadorPreautuacao());
    }
    /*
     * Envia os dados da préautuação para o serviço de recebimento (back-end).
     * @param protocoloId Nº do protocolo de recebimento da remessa.
     * @param classeId Id da classe processual.
     * @param sigilo Sigilo do processo.
     * @param preferencias Preferências processuais.
     */
    public preautuarRecursal(cmd : PreautuarRecursalCommand): IPromise<any> {
        return this.$http.post(this.properties.url + ":" + this.properties.port + 
            PreautuacaoRecursalService.urlServicoPreautuacao + '/preautuacao-recursal', cmd);        
    }
}

class ValidadorPreautuacao implements cmd.CommandValidator {
	
	constructor() {}
	
	public isValid(command: PreautuarRecursalCommand): boolean {
		if (angular.isString(command.classeId) &&
			command.preferencias.length > 0) {
			return true;
		}
		return false;
	}

}

preautuacaoRecursal.service("app.recebimento.preautuacao-recursal.PreautuacaoRecursalService", PreautuacaoRecursalService);
export default preautuacaoRecursal;