import {PeticaoFisicaCommand, PeticaoFisicaService} from "./peticao-fisica.service";
import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import recebimento from "./peticao-fisica.module";

export class PeticaoFisicaController {

    public basicForm: Object = {};
	public qtdVolumes : number;
	public qtdApensos : number;
	public numeroSedex : string;
    public formaRecebimento: string = "";
    public tiposProcessos : Object[] = PeticaoFisicaController.mockTiposProcessos();
    public tipoProcesso : string = "";

    static $inject = ['$state', 'app.recebimento.peticoes-fisicas.PeticaoFisicaService', 'formasRecebimento'];
    
    constructor(private $state: IStateService,
                private peticaoFisicaService: PeticaoFisicaService,
                public formasRecebimento) { }

    public registrarPeticao(): void {
        this.peticaoFisicaService.registrar(this.commandPeticaoFisica())
            .then(() => {
                this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
        });
    }

    private commandPeticaoFisica(): PeticaoFisicaCommand {
        return new PeticaoFisicaCommand(this.formaRecebimento.toUpperCase(), this.qtdVolumes, this.qtdApensos, this.numeroSedex, this.tipoProcesso.toUpperCase());
    }
    
    private static mockTiposProcessos() : Object[]{
        return [{ id : 'ORIGINARIO', nome : "Originário" }, { id : 'RECURSAL', nome : "Recursal" } ];
    }
}

recebimento.controller('app.recebimento.peticoes-fisicas.PeticaoFisicaController', PeticaoFisicaController);
export default recebimento;