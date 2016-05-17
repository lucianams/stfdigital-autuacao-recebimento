/**
 * @author Viniciusk
 */
import {PreautuarRemessaCommand, PreautuacaoService} from "./preautuacao.service";
import IStateService = angular.ui.IStateService;
import IStateParamService = angular.ui.IStateParamsService;
import IPromise = angular.IPromise;
import IHttpService = angular.IHttpService;
import IHttpPromiseCallbackArg = angular.IHttpPromiseCallbackArg;
import preautuacao from "./preautuacao.module";


export class Preferencia {
	constructor (public id : number, public nome : String){}
}

export class Classe {
	constructor (public id : String, public nome : String, public preferencias : Array<Preferencia>){}
}

export class PreautuacaoController {
	
	public basicForm: Object = {};
	public peticao : Object = {};
	public classe : Classe;
	public preferencia : String = "";
	public motivo : String;
	public processoId : number;
	public preferencias : Array<Preferencia>;
	public preferenciasSelecionadas : Array<number>;

	static $inject = ['$state', 'app.novo-processo.preautuacao.PreautuacaoService', 'classes', '$stateParams'];
	
    constructor(private $state: IStateService,
            private preautuacaoService: PreautuacaoService,
            public classes, private $stateParams : IStateParamService,
            private $http: IHttpService, private properties) {
    		
    		//let resource = $stateParams['resources']
    		//this.processo = autuacaoService.consultar(angular.isObject(resource) ? resource.processoId : resource);
    	
    		this.peticao = this.mockPeticaoPreautuacao()
    }
    
    public carregarPreferencias() : void {
    	this.preferencias = this.classe.preferencias;
    }

	public registrarPreautuacao(): void {
	    this.preautuacaoService.preautuar(this.commandPreautuacao())
	        .then(() => {
	            this.$state.go('app.tarefas.minhas-tarefas', {}, { reload: true });
	    });
	};

	private commandPreautuacao(): PreautuarRemessaCommand {
	    return new PreautuarRemessaCommand(1, this.classe.id, this.preferenciasSelecionadas);
	};
	
	public mockPeticaoPreautuacao () : Object {
		let processoMock : Object = {processoId : 1, remessa : {classeSugerida : 'RE',  qtdVolumes : 2, qtdApensos : 3, formaRecebimento : 'SEDEX', numeroSedex : '2000'}} ;
		return processoMock;
	} 
	
}

preautuacao.controller('app.novo-processo.preautuacao.PreautuacaoController', PreautuacaoController);
export default preautuacao;