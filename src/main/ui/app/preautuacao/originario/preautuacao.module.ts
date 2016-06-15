import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;

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
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preautuacao/originario");
}

let preautuacao: IModule = angular.module("app.recebimento.preautuacao-recursal", ["app.recebimento.preautuacao-services", "app.novo-processo", "app.constants"]);
preautuacao.config(config).run(run);
export default preautuacao;