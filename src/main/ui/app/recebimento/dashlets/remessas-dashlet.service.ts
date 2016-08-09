import dashlets from "./dashlets.module";

import {Remessa} from "../services/model";

export class RemessasDashletService {
	
	public static apiRemessas: string = '/recebimento/api/remessas';
	
	/** @ngInject */
	constructor(private $http: ng.IHttpService, private properties: app.support.constants.Properties) {
		
	}
	
	public listarRemessas(): ng.IPromise<Remessa[]> {
		return this.$http.get(this.properties.apiUrl + RemessasDashletService.apiRemessas).then(
			(response: ng.IHttpPromiseCallbackArg<Remessa[]>) => {
				return response.data;
			});
	}
	
}

dashlets.service("app.recebimento.dashlets.RemessasDashletService", RemessasDashletService);