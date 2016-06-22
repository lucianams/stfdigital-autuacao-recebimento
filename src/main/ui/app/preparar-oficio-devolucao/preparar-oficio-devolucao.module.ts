import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PrepararOficioDevolucaoService} from "./preparar-oficio-devolucao.service";
import {Remessa} from "./../services/model";

/** @ngInject **/
function config($stateProvider: IStateProvider, properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-preparar-oficio-devolucao', {
        url : '/preparar-oficio-devolucao',
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
            protocolo: () => {
            	return new Promise<number>((resolve, reject) => {
            		//let protocoloIdString : string = prompt("Digite o protocolo.", "2");
            		//let protocoloId : number = parseInt(protocoloIdString);
            		//resolve(protocoloId);
            		resolve(9002);
            	});
            }
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: any) {
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/preparar-oficio-devolucao');
}

let prepararOficioDevolucao: IModule = angular.module('app.recebimento.preparar-oficio-devolucao', ['app.recebimento.preautuacao-services', 'app.novo-processo', 'app.support', 'app.support']);
prepararOficioDevolucao.config(config).run(run);
export default prepararOficioDevolucao;