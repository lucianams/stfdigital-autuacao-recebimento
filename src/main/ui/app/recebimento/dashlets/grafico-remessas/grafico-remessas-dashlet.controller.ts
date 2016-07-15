import dashlets from "../dashlets.module";

let controllerName: string = "app.recebimento.dashlets.GraficoRemessasDashletController";

export class GraficoRemessasDashletController {
	
	public nvd3: any = {};
	public options = {};
	public data = [];
	
	constructor() {
		this.options = {
            chart : {
                type : 'pieChart',
                noData: "",
                height : 300,
                margin : {top : 20, right : 20, bottom : 20, left : 55},
                x : function(d) {
                    return d.label;
                },
                y : function(d) {
                    return d.value;
                },
                showValues : true
            }
        };
		
		this.data.push({"label": 'RE', "value" : 4});
		this.data.push({"label": 'ADI', "value" : 6});
		this.data.push({"label": 'ARE', "value" : 10});
		
		//this.nvd3.api.refresh(); TODO Colocar no callback da chamada de carregamento dos dados.
	}
	
}

dashlets.controller(controllerName, GraficoRemessasDashletController);

dashlets.run(/** @ngInject **/ (dashletRegistry) => {
    dashletRegistry.registerDashlet('grafico-remessas', {
        templateUrl: './grafico-remessas-dashlet.tpl.html',
        controller: controllerName,
        controllerAs: 'vm'
    });
});

export default dashlets;