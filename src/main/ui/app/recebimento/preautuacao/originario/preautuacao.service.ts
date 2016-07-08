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
	
    constructor() {};
}

export class DevolverRemessaCommand implements cmd.Command {
	public protocoloId: number;
	public motivo: string;

	constructor() {};
}

export class PreautuacaoService {

    private static urlServicoPreautuacao: string = '/recebimento/api/remessas';
    
    /** @ngInject **/
    constructor(private $http: IHttpService, private properties : Properties,  commandService: cmd.CommandService) {
    	commandService.setValidator('preautuar-originario', new ValidadorPreautuacao());
    }

    /*
     * Envia os dados da préautuação para o serviço de recebimento (back-end).
     * @param protocoloId Nº do protocolo de recebimento da remessa.
     * @param classeId Id da classe processual.
     * @param sigilo Sigilo do processo.
     * @param preferencias Preferências processuais.
     */
    public preautuarProcesso(cmdPreautuar : PreautuarRemessaCommand): IPromise<any> {
        return this.$http.post(this.properties.url + ":" + this.properties.port + 
            PreautuacaoService.urlServicoPreautuacao + '/preautuacao', cmdPreautuar);        
    }

	public devolver(cmdDevolver: DevolverRemessaCommand): IPromise<any> {
		return this.$http.post(this.properties.apiUrl + PreautuacaoService.urlServicoPreautuacao + '/devolucao', cmdDevolver);
	}
}

class ValidadorPreautuacao implements cmd.CommandValidator {
	
	constructor() {}
	
	public isValid(command: PreautuarRemessaCommand): boolean {
		if (angular.isString(command.classeId)) {
			return true;
		}
		return false;
	}

}

preautuacao.service("app.recebimento.preautuacao-originario.PreautuacaoService", PreautuacaoService);
export default preautuacao;