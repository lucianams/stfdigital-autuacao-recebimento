import ITranslatePartialLoaderProvider = angular.translate.ITranslatePartialLoaderProvider;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import {PeticaoFisicaService} from "./peticao-fisica.service";

/** @ngInject **/
function config($translatePartialLoaderProvider: ITranslatePartialLoaderProvider,
                $stateProvider: IStateProvider,
                properties: any) {

    $translatePartialLoaderProvider.addPart(properties.apiUrl + '/recebimento/peticoes-fisicas');

    $stateProvider.state('app.novo-processo.recebimento-peticao-fisica', {
        url : '/peticao-fisica',
        views : {
            'content@app.autenticado' : {
                templateUrl : properties.apiUrl + '/recebimento/peticoes-fisicas/peticao-fisica.tpl.html',
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

let recebimento: IModule = angular.module('app.novo-processo.peticoes-fisicas', ['app.novo-processo', 'app.constants']);
recebimento.config(config);
export default recebimento;