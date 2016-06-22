import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import preautuacao from "./preautuacao.module";

export class PreautuarRemessaCommand {
    constructor(public protocoloId: number, 
                public classeId: string,
                public sigilo: string,
                public preferencias: Array<number>) {}    
}

export class DevolverRemessaCommand {
	constructor(public protocoloId: number, public motivo: string) {}
}

export class PreautuacaoService {

    private static urlServicoPreautuacao: string = '/recebimento/api/remessas';
    
    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }

    /*
     * Envia os dados da préautuação para o serviço de recebimento (back-end).
     * @param protocoloId Nº do protocolo de recebimento da remessa.
     * @param classeId Id da classe processual.
     * @param sigilo Sigilo do processo.
     * @param preferencias Preferências processuais.
     */
    public preautuarProcesso(protocoloId: number, classeId: string, sigilo: string, preferencias: Array<number>): IPromise<any> {
        let cmd: PreautuarRemessaCommand = new PreautuarRemessaCommand(protocoloId, classeId, sigilo, preferencias);
        return this.$http.post(this.properties.url + ":" + this.properties.port + 
            PreautuacaoService.urlServicoPreautuacao + '/preautuacao', cmd);        
    }

	public devolver(command: DevolverRemessaCommand): IPromise<any> {
		return this.$http.post(this.properties.apiUrl + PreautuacaoService.urlServicoPreautuacao + '/devolucao', command);
	}

}

preautuacao.service("app.recebimento.preautuacao.PreautuacaoService", PreautuacaoService);
export default preautuacao;