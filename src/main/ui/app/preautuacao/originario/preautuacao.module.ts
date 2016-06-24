import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {RemessaService} from './../../services/remessa.service';


/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-preautuacao', {
        url : "/preautuacao/originario",
        views : {
            "content@app.autenticado" : {
                templateUrl : "./preautuacao.tpl.html",
                controller : "app.recebimento.preautuacao-originario.PreautuacaoController",
                controllerAs: "preautuacao"
            }
        },
    	resolve : {
    		classes : ['app.recebimento.services.RemessaService', (remessaService ) => {
    			return remessaService.listarClassesPorTipoRemessa("ORIGINARIO");
    		}],
            remessa: ['app.recebimento.services.RemessaService', (remessaService: RemessaService) => {
                let protocoloId = 9000;
                return remessaService.consultarRemessa(protocoloId);
            }]
    	}
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + "/recebimento/preautuacao/originario");
}

let preautuacao: IModule = angular.module("app.recebimento.preautuacao-originario", ["app.recebimento.services", "app.novo-processo", "app.support"]);
preautuacao.config(config).run(run);
export default preautuacao;