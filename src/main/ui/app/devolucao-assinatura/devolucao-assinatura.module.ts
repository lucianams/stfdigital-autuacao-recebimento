import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {Remessa} from "./../services/model";
import {PreautuacaoService} from "./../services/preautuacao.service";
import "./../services/preautuacao.service";

/** @ngInject **/
function config($stateProvider: IStateProvider, properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-devolucao-assinatura', {
        url : '/devolucao-assinatura',
        views : {
            'content@app.autenticado' : {
                templateUrl : './devolucao-assinatura.tpl.html',
                controller : 'app.recebimento.devolucao-assinatura.DevolucaoAssinaturaController',
                controllerAs: 'vm'
            }
        },
        resolve : {
            remessas: () => {
            	return [new Remessa('a', 1, 2, 'b', 'c'), new Remessa('b', 1, 2, 'b', 'c')];
            },
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: any) {
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/devolucao-assinatura');
}

let devolucaoAssinatura: IModule = angular.module('app.recebimento.devolucao-assinatura', ['app.recebimento.preautuacao-services', 'app.novo-processo', 'app.support', 'app.support']);
devolucaoAssinatura.config(config).run(run);
export default devolucaoAssinatura;