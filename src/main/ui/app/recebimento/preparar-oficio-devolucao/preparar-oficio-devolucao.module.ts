import {PrepararOficioDevolucaoService} from "./preparar-oficio-devolucao.service";
import {Remessa} from "./../services/model";

/** @ngInject **/
function config($stateProvider: ng.ui.IStateProvider, properties: app.support.constants.Properties) {

    $stateProvider.state('app.tarefas.recebimento-preparar-oficio-devolucao', {
        url : '/preparar-oficio-devolucao/:informationId',
        views : {
            'content@app.autenticado' : {
                templateUrl : './preparar-oficio-devolucao.tpl.html',
                controller : 'app.recebimento.preparar-oficio-devolucao.PrepararOficioDevolucaoController',
                controllerAs: 'vm'
            }
        },
        resolve : {
            motivosDevolucao : ['app.recebimento.preparar-oficio-devolucao.PrepararOficioDevolucaoService', (prepararOficioDevolucaoService: PrepararOficioDevolucaoService) => {
                return prepararOficioDevolucaoService.listarMotivosDevolucao();
            }],
            protocolo: /** @ngInject */($stateParams: ng.ui.IStateParamsService, $q: ng.IQService) => $q.when($stateParams['informationId'])
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ng.translate.ITranslatePartialLoaderService, properties: app.support.constants.Properties) {
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/preparar-oficio-devolucao');
}

let prepararOficioDevolucao: ng.IModule = angular.module('app.recebimento.preparar-oficio-devolucao', ['app.recebimento.services', 'app.novo-processo', 'app.support', 'app.support']);
prepararOficioDevolucao.config(config).run(run);
export default prepararOficioDevolucao;