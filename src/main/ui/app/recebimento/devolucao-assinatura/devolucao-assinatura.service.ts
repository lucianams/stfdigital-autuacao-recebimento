import {Modelo} from "./../services/model";
import devolucaoAssinatura from "./devolucao-assinatura.module";

export class AssinarOficioParaDevolucaoCommand {

    public constructor(public protocoloId: number, public documentoTemporarioId: string) {}

}

export class Devolucao {
    public remessaProtocoloId: number;

    public remessaNumero: number;
    public remessaAno: number;

    public modeloDevolucao: Modelo;

    public textoId: number;

}

export interface Documento {
    documentoId: number;
    tamanho: number;
    quantidadePaginas: number;
}

export class DevolucaoAssinaturaService {

    private static apiRemessa: string = "/recebimento/api/remessas";
    private static apiTexto: string = "/documents/api/textos";

    public static $inject = ["$http", "properties", "$q"];

    public constructor(private $http: ng.IHttpService, private properties: app.support.constants.Properties,
            private $q: ng.IQService) { }

    public montarUrlConteudoTexto(textoId: number): string {
        return this.properties.apiUrl + DevolucaoAssinaturaService.apiTexto + "/" + textoId + "/conteudo.pdf";
    }

    public assinarOficioDevolucao(command: AssinarOficioParaDevolucaoCommand): ng.IPromise<{}> {
        return this.$http.put(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa +
                "/" + command.protocoloId +  "/assinatura-devolucao", command)
                .then((response: ng.IHttpPromiseCallbackArg<{}>) => {
            return response.data;
        });
    }

    public consultarDevolucoes(protocolos: number[]): ng.IPromise<Devolucao[]> {
        let promises: ng.IPromise<Devolucao>[] = [];
        for (let protocoloId of protocolos) {
            promises.push(this.$http.get(this.properties.apiUrl + DevolucaoAssinaturaService.apiRemessa + "/" +
                    protocoloId + "/devolucoes")
                .then((response: ng.IHttpPromiseCallbackArg<Devolucao>) => {
                    return response.data;
                }));
        }
        return this.$q.all(promises);
    }

    public consultarDocumentoFinalDoTexto(textoId: number): ng.IPromise<Documento> {
        return this.$http.get(this.properties.apiUrl + DevolucaoAssinaturaService.apiTexto + "/" + textoId +
                "/documento-final")
            .then((response: ng.IHttpPromiseCallbackArg<Documento>) => {
                return response.data;
            });
    }
}

devolucaoAssinatura.service("app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService",
        DevolucaoAssinaturaService);
export default devolucaoAssinatura;