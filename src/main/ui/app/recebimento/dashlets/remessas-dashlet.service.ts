import {Remessa} from "../services/model";
import dashlets from "./dashlets.module";

export class RemessasDashletService {

    public static apiRemessas: string = "/recebimento/api/remessas";

    /** @ngInject */
    public constructor(private $http: ng.IHttpService, private properties: app.support.constants.Properties) {

    }

    public listarRemessas(): ng.IPromise<Remessa[]> {
        return this.$http.get(this.properties.apiUrl + RemessasDashletService.apiRemessas).then(
            (response: ng.IHttpPromiseCallbackArg<Remessa[]>) => {
                return response.data;
            });
    }

}

dashlets.service("app.recebimento.dashlets.RemessasDashletService", RemessasDashletService);