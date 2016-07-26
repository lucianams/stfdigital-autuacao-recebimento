import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PeticaoFisicaService} from "./peticao-fisica.service";
import {RemessaService} from './../services/remessa.service';
import cmd = app.support.command; 
import Properties = app.support.constants.Properties;

/** @ngInject **/
function config($stateProvider: IStateProvider,
                properties: any) {

    $stateProvider.state('app.novo-processo.recebimento-peticao-fisica', {
        url : '/recebimento',
        views : {
            'content@app.autenticado' : {
                templateUrl : './peticao-fisica.tpl.html',
                controller : 'app.recebimento.peticoes-fisicas.PeticaoFisicaController',
                controllerAs: 'registro'
            }
        },
        resolve : {
            formasRecebimento : ['app.recebimento.peticoes-fisicas.PeticaoFisicaService', (peticaoFisicaService: PeticaoFisicaService) => {
                return peticaoFisicaService.consultarFormasRecebimento();
            }],
            sigilos: ['app.recebimento.services.RemessaService', (remessaService: RemessaService) => {
                return remessaService.listarSigilo();
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: Properties) {
	
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/peticoes-fisicas');
}

let recebimento: IModule = angular.module('app.recebimento.peticoes-fisicas', ['app.recebimento.services', 'app.novo-processo', 'app.support']);
recebimento.config(config).run(run);
export default recebimento;