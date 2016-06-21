import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PreautuacaoService} from './../../services/preautuacao.service';


/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-preautuacao', {
        url : "/preautuacao/originario",
        views : {
            "content@app.autenticado" : {
                templateUrl : "./preautuacao.tpl.html",
                controller : "app.recebimento.preautuacao.PreautuacaoController",
                controllerAs: "preautuacao"
            }
        },
    	resolve : {
    		classes : ['app.recebimento.preautuacao-services.PreautuacaoService', (preautuacaoService ) => {
    			return preautuacaoService.listarClassesPorTipoRemessa("ORIGINARIO");
    		}]
    	}
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preautuacao/originario");
}

let preautuacao: IModule = angular.module("app.recebimento.preautuacao-originario", ["app.recebimento.preautuacao-services", "app.novo-processo", "app.support"]);
preautuacao.config(config).run(run);
export default preautuacao;