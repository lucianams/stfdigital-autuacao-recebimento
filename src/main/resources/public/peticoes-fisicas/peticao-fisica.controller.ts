import IStateService = angular.ui.IStateService;
import {PeticaoFisicaService, IPeticaoFisica, FormaRecebimento} from "./peticao-fisica.service";
import recebimento from "./peticao-fisica.module";

export class PeticaoFisicaController {
    
    public basicForm: Object = {};
    public formWizard: Object = {};
    public states: Object[] = PeticaoFisicaController.mockClasses();

    static $inject = ['$state', 'app.novo-processo.recebimento.PeticaoFisicaService'];
    
    constructor(private $state: IStateService, 
                private peticaoFisicaService: PeticaoFisicaService) { }

    public sendForm(): void {
        this.peticaoFisicaService.registrar(PeticaoFisicaController.mockPeticao())
            .then(() => {
                this.formWizard = {};
                this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
            });
    }

    private static mockClasses(): Object[] {
        return ('ADI ADO')
            .split(' ')
            .map(state => { return { abbrev: state }; });
    }

    private static mockPeticao(): IPeticaoFisica {
        return {
            formaRecebimento: FormaRecebimento.SEDEX.valueOf(),
            volumes: 1,
            apensos: 1,
            numeroSedex: "SR123456789BR",
            tipoProcesso: "originario"
        };
    }
}

recebimento.controller('app.novo-processo.recebimento.PeticaoFisicaController', PeticaoFisicaController);

export default recebimento;