import {Remessa} from "./../services/model";
import {DevolucaoAssinaturaService, Devolucao} from "./devolucao-assinatura.service";

/** @ngInject **/
function config($stateProvider: ng.ui.IStateProvider, properties: any) {

    $stateProvider.state('app.tarefas.recebimento-devolucao-assinatura', {
        url : '/devolucao-assinatura/:informationId',
        views : {
            'content@app.autenticado' : {
                templateUrl : './devolucao-assinatura.tpl.html',
                controller : 'app.recebimento.devolucao-assinatura.DevolucaoAssinaturaController',
                controllerAs: 'vm'
            }
        },
        resolve : {
            protocolos: /** @ngInject */($stateParams: ng.ui.IStateParamsService, $q: ng.IQService) => $q.when([$stateParams['informationId']]),
            devolucoes: ['app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', 'protocolos', (devolucaoAssinaturaService: DevolucaoAssinaturaService, protocolos: number[]) => {
                return devolucaoAssinaturaService.consultarDevolucoes(protocolos);
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ng.translate.ITranslatePartialLoaderService, properties: any) {
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/devolucao-assinatura');
}

let devolucaoAssinatura: ng.IModule = angular.module('app.recebimento.devolucao-assinatura', ['app.recebimento.services', 'app.novo-processo', 'app.support', 'app.certification', 'checklist-model']);
devolucaoAssinatura.config(config).run(run);
export default devolucaoAssinatura;