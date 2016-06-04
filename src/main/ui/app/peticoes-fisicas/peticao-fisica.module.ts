import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PeticaoFisicaService} from "./peticao-fisica.service";

/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-peticao-fisica', {
        url : '/recebimento',
        views : {
            'content@app.autenticado' : {
                templateUrl : './peticao-fisica.tpl.html',
                controller : 'app.novo-processo.peticoes-fisicas.PeticaoFisicaController',
                controllerAs: 'registro'
            }
        },
        resolve : {
            formasRecebimento : ['app.novo-processo.peticoes-fisicas.PeticaoFisicaService', (peticaoFisicaService: PeticaoFisicaService) => {
                return peticaoFisicaService.consultarFormasRecebimento();
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any) {
	
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/peticoes-fisicas');
}

let recebimento: IModule = angular.module('app.novo-processo.peticoes-fisicas', ['app.novo-processo', 'app.constants']);
recebimento.config(config).run(run);
export default recebimento;