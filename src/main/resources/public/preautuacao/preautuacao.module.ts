import ITranslatePartialLoaderProvider = angular.translate.ITranslatePartialLoaderProvider;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
//import {AutuacaoService} from "./autuacao.service";
import {ClasseService} from "./classe.service";

/** @ngInject **/
function config($translatePartialLoaderProvider: ITranslatePartialLoaderProvider,
                $stateProvider: IStateProvider,
                properties: any) {

    $translatePartialLoaderProvider.addPart(properties.apiUrl + '/recebimento/preautuacao');

    $stateProvider.state('app.novo-processo.recebimento', {
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

let preautuacao: IModule = angular.module('app.novo-processo.preautuacao', ['app.novo-processo', 'app.constants']);
preautuacao.config(config);
export default preautuacao;