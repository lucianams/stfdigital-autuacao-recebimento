import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import devolucao from "./devolucao.module";

export class DevolucaoController {
	
    static $inject = ['$state', 'motivosDevolucao'];
    
    constructor(private $state: IStateService,
                public motivosDevolucao) { }
	
}

devolucao.controller('app.novo-processo.devolucao.DevolucaoController', DevolucaoController);
export default devolucao;