import dashlets from "../dashlets.module";

import {RemessasDashletService} from "../remessas-dashlet.service";
import {Remessa} from "../../services/model";

let controllerName: string = "app.recebimento.dashlets.MinhasRemessasDashletController";

export class MinhasRemessasDashletController {
	
	public remessas: Remessa[];
	
    public static $inject = ['app.recebimento.dashlets.RemessasDashletService'];

	constructor(remessasDashletsService: RemessasDashletService) {
		remessasDashletsService.listarRemessas().then((remessas: Remessa[]) => {
			this.remessas = remessas;
		});
	}
	
}

dashlets.controller(controllerName, MinhasRemessasDashletController);

dashlets.run(/** @ngInject **/ (dashletRegistry) => {
	dashletRegistry.registerDashlet('minhas-remessas', {
		templateUrl: './minhas-remessas-dashlet.tpl.html',
		controller: controllerName,
		controllerAs: 'vm'
	});
});

export default dashlets;