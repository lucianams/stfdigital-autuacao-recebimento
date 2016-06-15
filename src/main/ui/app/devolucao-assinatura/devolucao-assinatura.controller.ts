import IStateService = angular.ui.IStateService;
import {DevolucaoAssinaturaService, AssinarOficioParaDevolucaoCommand} from "./devolucao-assinatura.service";
import devolucaoAssinatura from "./devolucao-assinatura.module";
import {Remessa} from "./../services/model";

export class DevolucaoAssinaturaController {
	
    static $inject = ['$state', 'app.recebimento.devolucao-assinatura.DevolucaoAssinaturaService', 'remessas'];

    constructor(private $state: IStateService, private devolucaoAssinaturaService: DevolucaoAssinaturaService,
                public remessas: Remessa[]) {
    	
    }
    
}

devolucaoAssinatura.controller('app.recebimento.devolucao-assinatura.DevolucaoAssinaturaController', DevolucaoAssinaturaController);
export default devolucaoAssinatura;