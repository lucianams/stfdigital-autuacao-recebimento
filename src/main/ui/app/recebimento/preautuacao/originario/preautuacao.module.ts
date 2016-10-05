import IModule = angular.IModule;
import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateParams = angular.ui.IStateParamsService;
import IStateProvider = angular.ui.IStateProvider;

import {ClasseService} from "./../../services/classe.service";
import {RemessaService} from "./../../services/remessa.service";

import Properties = app.support.constants.Properties;
import cmd = app.support.command;

/** @ngInject **/
function config($stateProvider: IStateProvider, properties: Properties) {

    $stateProvider.state("app.tarefas.recebimento-preautuacao", {
        url : "/preautuacao/originario/:informationId",
        views : {
            "content@app.autenticado" : {
                templateUrl : "./preautuacao.tpl.html",
                controller : "app.recebimento.preautuacao-originario.PreautuacaoController",
                controllerAs: "preautuacao"
            }
        },
        resolve : {
            classes : ["app.recebimento.services.ClasseService", (classeService: ClasseService) => {
                return classeService.listar("ORIGINARIO");
            }],
            remessa: ["app.recebimento.services.RemessaService", "$stateParams",
                    (remessaService: RemessaService, $stateParams: IStateParams) => {
                let protocoloId = $stateParams["informationId"];
                return remessaService.consultarRemessa(protocoloId);
            }],
            sigilos: ["app.recebimento.services.RemessaService", (remessaService: RemessaService) => {
                return remessaService.listarSigilo();
            }]
        },
        params : {
            resource: undefined
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
             properties: Properties) {
    $translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preautuacao/originario");
}

let preautuacao: IModule = angular.module("app.recebimento.preautuacao-originario", [
    "app.recebimento.services",
    "app.recebimento.preautuacao-devolucao",
    "app.novo-processo",
    "app.support"]);
preautuacao.config(config).run(run);
export default preautuacao;