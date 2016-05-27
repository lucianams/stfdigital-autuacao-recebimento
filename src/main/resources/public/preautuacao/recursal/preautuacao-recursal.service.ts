/*
 * Fonrnece os serviços de préautuação de processos recursais. 
 * @author anderson.araujo
 * @since 24/05/2016
 */

import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import {Processo, Remessa, Preferencia} from "../../services/model";
import preautuacaoRecursal from "./preautuacao-recursal.module";

export class PreautuarRemessaCommand {
    constructor(public protocoloId: number, 
                public classeId: string,
                public sigilo: string,
                public preferencias: Array<number>) {}    
}

export class RegistrarRecebimentoCommand {
    public formaRecebimento: string;
    public volumes: number;
    public apensos: number;
    public numeroSedex: string;
    public tipoProcesso: string;
    public sigilo: string;
    
    constructor(formaRecebimento: string, volumes: number, apensos: number, numeroSedex: string, tipoProcesso: string, sigilo: string){
        this.formaRecebimento = formaRecebimento;
        this.volumes = volumes;
        this.apensos = apensos;
        this.numeroSedex = numeroSedex;
        this.tipoProcesso = tipoProcesso;
        this.sigilo = sigilo;
    }
}

export class PreautuacaoRecursalService {
    
    private static urlServicoRemessa: string = '/recebimento/api/remessas';
    private static urlServicoPreautuacaoRecursal: string = '/recebimento/api/remessas';

    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }
    /*
     * Função temporária para registrar uma remessa para ser usada na préautuação. Após a implementação do 
     * mecanismo de ações, esta função deve ser removida, juntamente com a classe RegistrarRecebimentoCommand.
     */
    public gerarRemessa(): IPromise<number> {
        let cmd = new RegistrarRecebimentoCommand("SEDEX", 1, 1, "SE123456789BR", "RECURSAL", "PUBLICO");
        return this.$http.post(this.properties.url + ":" + this.properties.port + "/recebimento/api/remessas/recebimento", cmd).then(
            (response: IHttpPromiseCallbackArg<number>) => { 
                return response.data; 
            });
    }
    
    /*
     * Consulta uma remessa de acordo com o nº de protocolo informado.
     * @param protocoloId Nº do protocolo de recebimento da remessa.
     * @return Dados da remessa recebida.
     */    
    public consultarRemessa(protocoloId: number): IPromise<Remessa> {
        return this.$http.get(this.properties.url + ":" + this.properties.port + 
            PreautuacaoRecursalService.urlServicoRemessa + '/' + protocoloId).then(
                (response: IHttpPromiseCallbackArg<Remessa>) => { 
                return response.data; 
            });
    }
    
    /*
     * Envia os dados da préautuação para o serviço de recebimento (back-end).
     * @param protocoloId Nº do protocolo de recebimento da remessa.
     * @param classeId Id da classe processual.
     * @param sigilo Sigilo do processo.
     * @param preferencias Preferências processuais.
     */
    
    public preautuarProcessoRecursal(protocoloId: number, classeId: string, sigilo: string, preferencias: Array<number>): IPromise<any> {
        let cmd: PreautuarRemessaCommand = new PreautuarRemessaCommand(protocoloId, classeId, sigilo, preferencias);
        return this.$http.post(this.properties.url + ":" + this.properties.port + 
            PreautuacaoRecursalService.urlServicoPreautuacaoRecursal + '/preautuacao', cmd);        
    }
}

preautuacaoRecursal.service("app.novo-processo.preautuacao-recursal.PreautuacaoRecursalService", PreautuacaoRecursalService);

export default preautuacaoRecursal;