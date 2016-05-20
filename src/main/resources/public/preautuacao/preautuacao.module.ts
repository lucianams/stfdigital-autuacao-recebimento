import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
//import {AutuacaoService} from "./autuacao.service";
import {ClasseService} from "./classe.service";

/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-preautuacao', {
        url : '/preautuacao',
        views : {
            'content@app.autenticado' : {
                templateUrl : properties.apiUrl + '/recebimento/preautuacao/preautuacao.tpl.html',
                controller : 'app.novo-processo.preautuacao.PreautuacaoController',
                controllerAs: 'preautuacao'
            }
        },
        resolve : {
            classes : ['app.novo-processo.preautuacao.ClasseService', (classeService: ClasseService) => {
                return classeService.listar();
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/preautuacao');
}

let preautuacao: IModule = angular.module('app.novo-processo.preautuacao', ['app.novo-processo', 'app.constants']);
preautuacao.config(config).run(run);
export default preautuacao;