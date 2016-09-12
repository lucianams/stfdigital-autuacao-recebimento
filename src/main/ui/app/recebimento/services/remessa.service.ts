import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import services from "./services.module";
import {TipoProcesso, Classe, Remessa, Sigilo} from "./model";

/**
 * Serviço usado para retornar os tipos de processos.
 * @author anderson.araujo
 * @since 27/05/2016
 */

export class RemessaService {

    private static urlServicoRemessa: string = '/recebimento/api/remessas';
    
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
    
    public listarSigilo() : IPromise<Sigilo[]> {
    	return this.$http.get(this.properties.url + ":" + this.properties.port 
                + RemessaService.urlServicoRemessa + "/sigilos" )
                    .then((response: IHttpPromiseCallbackArg<Sigilo[]>) => { 
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
            RemessaService.urlServicoRemessa + '/' + protocoloId).then(
                (response: IHttpPromiseCallbackArg<Remessa>) => { 
                return response.data; 
            });
    }

}

services.service("app.recebimento.services.RemessaService", RemessaService);
export default services;