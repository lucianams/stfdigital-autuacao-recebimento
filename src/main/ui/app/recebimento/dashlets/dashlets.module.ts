import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: app.support.constants.Properties) {
    $translatePartialLoader.addPart(properties.apiUrl + '/recebimento/dashlets');
}

let dashlets: ng.IModule = angular.module('app.recebimento.dashlets', ['app.support.dashboards']);
dashlets.run(run);

export default dashlets;