import ITranslatePartialLoaderProvider = angular.translate.ITranslatePartialLoaderProvider;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import ITemplateCacheService = angular.ITemplateCacheService;

/** @ngInject **/
function config($translatePartialLoaderProvider: ITranslatePartialLoaderProvider,
                $stateProvider: IStateProvider) {

    $translatePartialLoaderProvider.addPart('http://docker:8765/recebimento/peticoes-fisicas');

    $stateProvider.state('app.novo-processo.recebimento', {
        url: '/peticao-fisica',
        views: {
            'content@app.autenticado': {
                templateUrl: 'http://docker:8765/recebimento/peticoes-fisicas/peticao-fisica.tpl.html',
                controller: 'app.novo-processo.recebimento.PeticaoFisicaController',
                controllerAs: 'vm'
            }
        }
    });
}

let recebimento: IModule = angular.module('app.novo-processo.recebimento', ['app.novo-processo']);
recebimento.config(config);

export default recebimento;