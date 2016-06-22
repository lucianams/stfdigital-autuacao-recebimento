import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PreautuacaoService} from './../../services/preautuacao.service';

/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state("app.novo-processo.preautuacao-recursal", {
        url : "/preautuacao/recursal",
        views : {
            "content@app.autenticado" : {
                templateUrl : "./preautuacao-recursal.tpl.html",
                controller : "app.recebimento.preautuacao-recursal.PreautuacaoRecursalController",
                controllerAs: "preautuacao"
            }
        },
    	resolve : {
    		classes : ['app.recebimento.preautuacao-services.PreautuacaoService', (preautuacaoService : PreautuacaoService ) => {
    			return preautuacaoService.listarClassesPorTipoRemessa("RECURSAL");
    		}]
    	}
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preautuacao/recursal");
}

let preautuacaoRecursal: IModule = angular.module("app.recebimento.preautuacao-recursal", ["app.recebimento.preautuacao-services", "app.novo-processo", "app.support"]);
preautuacaoRecursal.config(config).run(run);
export default preautuacaoRecursal;