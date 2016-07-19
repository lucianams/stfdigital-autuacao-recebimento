import dashlets from "../dashlets.module";

let controllerName: string = "app.recebimento.dashlets.MinhasRemessasDashletController";

export interface Remessa {
	identificacao: string;
	dataCadastramento: Date;
}

export class MinhasRemessasDashletController {
	
	public remessas: Remessa[];
	
	constructor() {
		this.remessas = [{identificacao: "1/2016", dataCadastramento: new Date()},
		                 {identificacao: "2/2016", dataCadastramento: new Date()},
		                 {identificacao: "3/2016", dataCadastramento: new Date()},
		                 {identificacao: "4/2016", dataCadastramento: new Date()},
		                 {identificacao: "5/2016", dataCadastramento: new Date()},
		                 {identificacao: "6/2016", dataCadastramento: new Date()}];
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