import {Remessa} from "../../services/model";
import {RemessasDashletService} from "../remessas-dashlet.service";
import dashlets from "../dashlets.module";

interface Nvd3 {
    refresh: Function;
}

export class GraficoRemessasDashletController {

    public nvd3: {
        api: {
            refresh: Function
        }
    };
    public options = {};
    public data = [];
    public remessas: Remessa[];

    public static $inject = ["app.recebimento.dashlets.RemessasDashletService"];

    public constructor(remessasDashletsService: RemessasDashletService) {
        this.options = {
            chart: {
                type: "pieChart",
                noData: "",
                height: 300,
                margin: {
                    top: 20,
                    right: 20,
                    bottom: 20,
                    left : 55
                },
                x: function(d) {
                    return d.label;
                },
                y: function(d) {
                    return d.value;
                },
                showValues : true
            }
        };

        remessasDashletsService.listarRemessas().then((remessas: Remessa[]) => {
            this.remessas = remessas;

            this.data = _(this.remessas)
                    .groupBy("classe")
                    .mapValues((remessasDaClasse: Remessa[]) => remessasDaClasse.length)
                    .transform((result, value, key) => result.push({
                        label: (key ? key : "NÃO CLASSIFICADO"), value: value
                    }), []).value();

            this.nvd3.api.refresh();
        });
    }

}

let controllerName: string = "app.recebimento.dashlets.GraficoRemessasDashletController";

dashlets.controller(controllerName, GraficoRemessasDashletController);

dashlets.run(/** @ngInject **/ (dashletRegistry) => {
    dashletRegistry.registerDashlet("grafico-remessas", {
        templateUrl: "./grafico-remessas-dashlet.tpl.html",
        controller: controllerName,
        controllerAs: "vm"
    });
});

export default dashlets;