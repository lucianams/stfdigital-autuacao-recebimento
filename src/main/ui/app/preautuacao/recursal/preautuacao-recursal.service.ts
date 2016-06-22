import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import preautuacaoRecursal from "./preautuacao-recursal.module";

export class PreautuarRecursalCommand {
    constructor(public protocoloId: number, 
                public classeId: string,
                public sigilo: string,
                public preferencias: Array<number>) {}    
}

export class PreautuacaoRecursalService {
    
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
    public preautuarRecursal(protocoloId: number, classeId: string, sigilo: string, preferencias: Array<number>): IPromise<any> {
        let cmd: PreautuarRecursalCommand = new PreautuarRecursalCommand(protocoloId, classeId, sigilo, preferencias);
        return this.$http.post(this.properties.url + ":" + this.properties.port + 
            PreautuacaoRecursalService.urlServicoPreautuacao + '/preautuacao-recursal', cmd);        
    }
}

preautuacaoRecursal.service("app.recebimento.preautuacao-recursal.PreautuacaoRecursalService", PreautuacaoRecursalService);
export default preautuacaoRecursal;