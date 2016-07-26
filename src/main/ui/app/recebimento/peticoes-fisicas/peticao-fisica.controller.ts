import {PeticaoFisicaCommand, PeticaoFisicaService} from "./peticao-fisica.service";
import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import {Sigilo} from "../services/model";
import recebimento from "./peticao-fisica.module";

export class PeticaoFisicaController {

    public tiposProcessos : Object[] = PeticaoFisicaController.mockTiposProcessos();
	public cmd: PeticaoFisicaCommand = new PeticaoFisicaCommand();
    public path = {id: 'novo-processo.recebimento', translation:'Recebimento', uisref: 'app.novo-processo.recebimento-peticao-fisica', parent: 'novo-processo'};

    static $inject = ['$state', 'app.recebimento.peticoes-fisicas.PeticaoFisicaService', 'formasRecebimento', 'messagesService', 'sigilos'];
    
    constructor(private $state: IStateService,
                private peticaoFisicaService: PeticaoFisicaService,
                public formasRecebimento,
                private messagesService: app.support.messaging.MessagesService,
                public sigilos: Sigilo[]) {
    }
    
    public registrarRemessa(): ng.IPromise<any> {
        return this.peticaoFisicaService.registrar(this.cmd)
            .then(() => {
                this.messagesService.success('Remessa registrada com sucesso!');
                return this.$state.go('app.tarefas.minhas-tarefas');
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