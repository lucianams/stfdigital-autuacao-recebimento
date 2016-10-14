import IModule = angular.IModule;
import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;

import cmd = app.support.command;
import Properties = app.support.constants.Properties;

import {RemessaService} from "./../services/remessa.service";
import {PeticaoFisicaService} from "./peticao-fisica.service";

/** @ngInject **/
function config($stateProvider: IStateProvider, properties: app.support.constants.Properties) {

    $stateProvider.state("app.novo-processo.recebimento-peticao-fisica", {
        url: "/",
        views: {
            "content@app.autenticado" : {
                templateUrl : "./peticao-fisica.tpl.html",
                controller : "app.recebimento.peticoes-fisicas.PeticaoFisicaController",
                controllerAs: "registro"
            }
        },
        resolve: {
            formasRecebimento: ["app.recebimento.peticoes-fisicas.PeticaoFisicaService",
                    (peticaoFisicaService: PeticaoFisicaService) => {
                return peticaoFisicaService.consultarFormasRecebimento();
            }],
            sigilos: ["app.recebimento.services.RemessaService", (remessaService: RemessaService) => {
                return remessaService.listarSigilo();
            }],
            tiposProcessos: ["app.recebimento.services.RemessaService", (remessaService: RemessaService) => {
                return remessaService.listarTiposProcessos();
            }]
        }
    });

}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: Properties) {
    $translatePartialLoader.addPart(properties.apiUrl + "/recebimento/peticoes-fisicas");
}

let recebimento: IModule = angular.module("app.recebimento.peticoes-fisicas",
        ["app.recebimento.services", "app.novo-processo", "app.support"]);
recebimento.config(config).run(run);
export default recebimento;