import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import IHttpService = angular.IHttpService;
import IPromise = angular.IPromise;

import {Classe, Remessa, Sigilo, TipoProcesso} from "./model";
import services from "./services.module";

/**
 * Serviço usado para retornar os tipos de processos.
 * 
 * @author anderson.araujo
 * @since 1.0.0
 * @since 27.05.2016
 */
export class RemessaService {

    private static urlServicoRemessa: string = "/recebimento/api/remessas";

    /** @ngInject **/
    public constructor(private $http: IHttpService, private properties: app.support.constants.Properties) {}

    /* 
     * Retorna uma array de tipos de processos.
     * 
     * @return Tipos de processos.
     */
    public listarTiposProcessos(): Array<TipoProcesso> {
        let tiposProcesso = new Array<TipoProcesso>();
        tiposProcesso.push(new TipoProcesso("ORIGINARIO", "Originário"));
        tiposProcesso.push(new TipoProcesso("RECURSAL", "Recursal"));
        return tiposProcesso;
    }

    public listarSigilo(): IPromise<Sigilo[]> {
        return this.$http.get(this.properties.apiUrl + RemessaService.urlServicoRemessa + "/sigilos" )
                .then((response: IHttpPromiseCallbackArg<Sigilo[]>) => {
            return response.data;
        });
    }

    /*
     * Consulta uma remessa de acordo com o nº de protocolo informado.
     * 
     * @param protocoloId Nº do protocolo de recebimento da remessa. 
     * @return Dados da remessa recebida.
     */
    public consultarRemessa(protocoloId: number): IPromise<Remessa> {
        return this.$http.get(this.properties.apiUrl +
                RemessaService.urlServicoRemessa + "/" + protocoloId)
                .then((response: IHttpPromiseCallbackArg<Remessa>) => {
            return response.data;
        });
    }

}

services.service("app.recebimento.services.RemessaService", RemessaService);
export default services;