import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {DevolucaoService} from "./devolucao.service";

/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-devolucao', {
        url : '/devolucao',
        views : {
            'content@app.autenticado' : {
                templateUrl : properties.apiUrl + '/recebimento/devolucao/devolucao.tpl.html',
                controller : 'app.novo-processo.devolucao.DevolucaoController',
                controllerAs: 'vm'
            }
        },
        resolve : {
            motivosDevolucao : ['app.novo-processo.devolucao.DevolucaoService', (devolucaoService: DevolucaoService) => {
                return devolucaoService.listarMotivosDevolucao();
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/devolucao');
}

let devolucao: IModule = angular.module('app.novo-processo.devolucao', ['app.novo-processo', 'app.constants']);
devolucao.config(config).run(run);
export default devolucao;