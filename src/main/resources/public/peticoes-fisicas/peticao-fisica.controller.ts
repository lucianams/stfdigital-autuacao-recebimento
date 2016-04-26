import {PeticaoFisica, PeticaoFisicaService} from "./peticao-fisica.service";
import IStateService = angular.ui.IStateService;
import IPromise = angular.IPromise;
import recebimento from "./peticao-fisica.module";

export class PeticaoFisicaController {

    public basicForm: Object = {};
    public formWizard: Object = {};
//        public states: Object[] = PeticaoFisicaController.mockClasses();
    public tiposProcessos : Object[] = PeticaoFisicaController.mockTiposProcessos();

    static $inject = ['$state', 'app.novo-processo.peticoes-fisicas.PeticaoFisicaService', 'formasRecebimento'];
    
    constructor(private $state: IStateService,
                private peticaoFisicaService: PeticaoFisicaService,
                public formasRecebimento) { }

    public sendForm(): void {
        this.peticaoFisicaService.registrar(PeticaoFisicaController.mockPeticao())
            .then(() => {
                this.formWizard = {};
                this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
        });
    }

/*        private static mockClasses(): Object[] {
            return ('ADI ADO')
                .split(' ')
                .map(state => { return {abbrev: state}; });
        }
*///
    private static mockPeticao(): PeticaoFisica {
        return new PeticaoFisica('BALCAO', 1, 1, "SR123456789BR", "ORIGINARIO");
    }
    
    private static mockTiposProcessos() : Object[]{
        return [{ id : 'ORIGINARIO', nome : "Originário" }, { id : 'RECURSAL', nome : "Recursal" } ];
    }
}

recebimento.controller('app.novo-processo.peticoes-fisicas.PeticaoFisicaController', PeticaoFisicaController);
export default recebimento;