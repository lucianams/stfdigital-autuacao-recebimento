import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IStateParams = angular.ui.IStateParamsService;
import IModule = angular.IModule;
import {RemessaService} from '../../services/remessa.service';
import Properties = app.support.constants.Properties;
import cmd = app.support.command; 

/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: Properties) {

    $stateProvider.state("app.tarefas.recebimento-preautuacao-recursal", {
        url : "/preautuacao/recursal/:informationId",
        views : {
            "content@app.autenticado" : {
                templateUrl : "./preautuacao-recursal.tpl.html",
                controller : "app.recebimento.preautuacao-recursal.PreautuacaoRecursalController",
                controllerAs: "preautuacao"
            }
        },
        resolve: {
            classes: ['app.recebimento.services.RemessaService', (remessaService: RemessaService) => {
                return remessaService.listarClassesPorTipoRemessa("RECURSAL")
            }],
            remessa: ['app.recebimento.services.RemessaService', '$stateParams', (remessaService: RemessaService, $stateParams : IStateParams) => {
                let protocoloId = $stateParams['informationId'];
                return remessaService.consultarRemessa(protocoloId);
            }],
            sigilos: ['app.recebimento.services.RemessaService', (remessaService: RemessaService) => {
                return remessaService.listarSigilo();
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: Properties) {
	
	$translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preautuacao/recursal");
}

let preautuacaoRecursal: IModule = angular.module("app.recebimento.preautuacao-recursal", [
    "app.recebimento.services",
    "app.recebimento.preautuacao-devolucao",
    "app.novo-processo",
    "app.support"]);
preautuacaoRecursal.config(config).run(run);
export default preautuacaoRecursal;