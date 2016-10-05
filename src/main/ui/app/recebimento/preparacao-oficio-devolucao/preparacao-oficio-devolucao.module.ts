import {Remessa} from "./../services/model";
import {PreparacaoOficioDevolucaoService} from "./preparacao-oficio-devolucao.service";

/** @ngInject **/
function config($stateProvider: ng.ui.IStateProvider, properties: app.support.constants.Properties) {
    $stateProvider.state("app.tarefas.recebimento-preparacao-oficio-devolucao", {
        url: "/preparacao-oficio-devolucao/:informationId",
        views: {
            "content@app.autenticado" : {
                templateUrl: "./preparacao-oficio-devolucao.tpl.html",
                controller: "app.recebimento.preparacao-oficio-devolucao.PreparacaoOficioDevolucaoController",
                controllerAs: "vm"
            }
        },
        resolve: {
            motivosDevolucao: ["app.recebimento.preparacao-oficio-devolucao.PreparacaoOficioDevolucaoService",
                    (preparacaoOficioDevolucaoService: PreparacaoOficioDevolucaoService) => {
                return preparacaoOficioDevolucaoService.listarMotivosDevolucao();
            }],
            protocolo: /** @ngInject */ ($stateParams: ng.ui.IStateParamsService, $q: ng.IQService) =>
                    $q.when($stateParams["informationId"])
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ng.translate.ITranslatePartialLoaderService,
        properties: app.support.constants.Properties) {
    $translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preparacao-oficio-devolucao");
}

let preparacaoOficioDevolucao: ng.IModule = angular.module("app.recebimento.preparacao-oficio-devolucao",
        ["app.recebimento.services", "app.novo-processo", "app.support", "app.support"]);
preparacaoOficioDevolucao.config(config).run(run);
export default preparacaoOficioDevolucao;