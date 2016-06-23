import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {Remessa} from "./../services/model";
import {DevolucaoAssinaturaService, Devolucao} from "./devolucao-assinatura.service";

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
            devolucoes: ['app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', (devolucaoAssinaturaService: DevolucaoAssinaturaService) => {
            	let protocolo: number = 9002;
                return devolucaoAssinaturaService.consultarDevolucao(protocolo).then((devolucao: Devolucao) => {
                    return [devolucao, <Devolucao>{remessaNumero: 456, remessaAno: 2016, textoId: devolucao.textoId, modeloDevolucao: devolucao.modeloDevolucao}];
                });
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: any) {
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/devolucao-assinatura');
}

let devolucaoAssinatura: IModule = angular.module('app.recebimento.devolucao-assinatura', ['app.recebimento.preautuacao-services', 'app.novo-processo', 'app.support', 'app.certification', 'checklist-model']);
devolucaoAssinatura.config(config).run(run);
export default devolucaoAssinatura;