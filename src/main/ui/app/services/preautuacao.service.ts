import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import preautuacaoServices from "./services.module";
import {TipoProcesso, Classe, Remessa} from "./model";

/*
Serviço usado para retornar os tipos de processos.
@author anderson.araujo
@since 27/05/2016
*/

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

export class DevolverRemessaCommand {
	constructor(public protocoloId: number, public motivo: string) {}
}

export class PreautuacaoService {

    private static urlServicoPreautuacao: string = '/recebimento/api/remessas';
    
    /** @ngInject **/
    constructor(private $http: IHttpService, private properties) { }
    
    /* 
     * Retorna uma array de tipos de processos.
     * @return Tipos de processos.
     */
    public listarTiposProcessos() : Array<TipoProcesso> {
        let tiposProcesso = new Array<TipoProcesso>();
        tiposProcesso.push(new TipoProcesso("ORIGINARIO", "Originário"));
        tiposProcesso.push(new TipoProcesso("RECURSAL", "RECURSAL"));
        return tiposProcesso;
    }
    
    /* 
     * Retorna uma array classes conforme o tipo de remessa informado.
     * @param tipoRemessa Tipo de remessa (ORIGINARIO/RECURSAL).
     * @return Array de classes.
     */
    public listarClassesPorTipoRemessa(tipoRemessa: string) : IPromise<Classe[]> {
        return this.$http.get(this.properties.url + ":" + this.properties.port 
            + PreautuacaoService.urlServicoPreautuacao + "/classes/tipos-remessa/" + tipoRemessa)
                .then((response: IHttpPromiseCallbackArg<Classe[]>) => { 
                    return response.data; 
                });
    }
    
    /*
     * Função temporária para registrar uma remessa para ser usada na préautuação. Após a implementação do 
     * mecanismo de ações, esta função deve ser removida, juntamente com a classe RegistrarRecebimentoCommand.
     */
    public gerarRemessa(tipo: string): IPromise<number> {
        let cmd = new RegistrarRecebimentoCommand("SEDEX", 1, 1, "SE123456789BR", tipo, "PUBLICO");
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
            PreautuacaoService.urlServicoPreautuacao + '/' + protocoloId).then(
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
    public preautuarProcesso(protocoloId: number, classeId: string, sigilo: string, preferencias: Array<number>): IPromise<any> {
        let cmd: PreautuarRemessaCommand = new PreautuarRemessaCommand(protocoloId, classeId, sigilo, preferencias);
        return this.$http.post(this.properties.url + ":" + this.properties.port + 
            PreautuacaoService.urlServicoPreautuacao + '/preautuacao', cmd);        
    }
    
	public devolver(command: DevolverRemessaCommand): IPromise<any> {
		return this.$http.post(this.properties.apiUrl + PreautuacaoService.urlServicoPreautuacao + '/devolucao', command);
	}
}

preautuacaoServices.service("app.novo-processo.preautuacao-services.PreautuacaoService", PreautuacaoService);
export default preautuacaoServices;