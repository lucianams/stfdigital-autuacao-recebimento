import {PeticaoFisicaCommand, PeticaoFisicaService} from "./peticao-fisica.service";
import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import recebimento from "./peticao-fisica.module";

export class PeticaoFisicaController {

    public tiposProcessos : Object[] = PeticaoFisicaController.mockTiposProcessos();
	public cmd: PeticaoFisicaCommand = new PeticaoFisicaCommand();
	public path = [{translation:'Iniciar Processo', uisref: 'app.novo-processo'},
	               {translation:'Recebimento', uisref: 'app.novo-processo.recebimento-peticao-fisica'}]

    static $inject = ['$state', 'app.recebimento.peticoes-fisicas.PeticaoFisicaService', 'formasRecebimento'];
    
    constructor(private $state: IStateService,
                private peticaoFisicaService: PeticaoFisicaService,
                public formasRecebimento) {

    	this.cmd.sigilo = 'PUBLICO';
    }
    
    public registrarRemessa(): void {
        this.peticaoFisicaService.registrar(this.cmd)
            .then(() => {
                this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
        });
    }
    
    public showSedex(): boolean {
    	if (this.cmd.formaRecebimento && this.cmd.formaRecebimento === "SEDEX") {
    		return true;
    	}
    	this.cmd.numeroSedex = "";
    	return false;
    }
    
    private static mockTiposProcessos() : Object[]{
        return [{ id : 'ORIGINARIO', nome : "Originário" }, { id : 'RECURSAL', nome : "Recursal" }];
    }
}

recebimento.controller('app.recebimento.peticoes-fisicas.PeticaoFisicaController', PeticaoFisicaController);
export default recebimento;