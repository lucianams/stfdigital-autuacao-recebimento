import dashlets from "../dashlets.module";

let controllerName: string = "app.recebimento.dashlets.MinhasRemessasDashletController";

interface Remessa {
	identificacao: string;
	dataCadastramento: Date;
}

export class MinhasRemessasDashletController {
	
	public remessas: Remessa[];
	
	constructor() {
		this.remessas = [<Remessa>{identificacao: "1/2016", dataCadastramento: new Date()},
		                 <Remessa>{identificacao: "2/2016", dataCadastramento: new Date()},
		                 <Remessa>{identificacao: "3/2016", dataCadastramento: new Date()},
		                 <Remessa>{identificacao: "4/2016", dataCadastramento: new Date()},
		                 <Remessa>{identificacao: "5/2016", dataCadastramento: new Date()},
		                 <Remessa>{identificacao: "6/2016", dataCadastramento: new Date()}];
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