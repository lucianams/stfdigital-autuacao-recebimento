import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PeticaoFisicaService, ValidaPeticaoFisica, PeticaoFisicaCommand} from "./peticao-fisica.service";
import cmd = app.support.command; 

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
            }]
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService,
			 properties: any, commandService : cmd.CommandService ) {
	
	$translatePartialLoader.addPart(properties.apiUrl + '/recebimento/peticoes-fisicas');
	let handlers: cmd.ConditionHandler<any>[] = [];
	handlers.push(ValidaPeticaoFisica);
	commandService.addHandlers('registrar-remessa', handlers);
}

let recebimento: IModule = angular.module('app.recebimento.peticoes-fisicas', ['app.novo-processo', 'app.support']);
recebimento.config(config).run(run);
export default recebimento;