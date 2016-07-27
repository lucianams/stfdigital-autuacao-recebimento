import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage}  from "./shared/pages/principal.page";
import {PreautuacaoRecursalPage} from "./pages/preautuacao-recursal.page";

describe('Preautuação de Remessa Recursal', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	var preautuacaoRecursalPage : PreautuacaoRecursalPage = new PreautuacaoRecursalPage();
                
    it ('Deveria logar no sistema', () => {
        loginPage.open();
        loginPage.login('preautuador-recursal', '123');
    });
    
    it ('Deveria acessar a tarefa de preautuação recursal', () => {
        principalPage.acessarTarefa('Pré-Autuar Remessa de Processo Recursal', 9007);
    });
    
    it('Deveria preencher as informações da preautuação recursal', () => {
        preautuacaoRecursalPage.preencherNumeroOrigem(123);
    	preautuacaoRecursalPage.preencherNumeroUnico(1234567890);
    	preautuacaoRecursalPage.selecionarClasse('RECURSO EXTRAORDINÁRIO');
        preautuacaoRecursalPage.selecionarPreferencia('Medida Liminar');
        preautuacaoRecursalPage.selecionarSigilo('Público');
        preautuacaoRecursalPage.preencherMotivo('Petição Recursal OK.');
    });
    
    it('Deveria preautuar', () => {
        preautuacaoRecursalPage.registrarPreautuacao();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
        expect(principalPage.mensagem()).toEqual('Remessa recursal preautuada com sucesso.');
    });

	it('Deveria fazer o logout do sistema', () => {
		principalPage.logout();
	});
});

describe('Preautuação de Remessa de Processo Recursal - Devolver', () => {
	
    let loginPage: LoginPage = new LoginPage();
    let principalPage: PrincipalPage = new PrincipalPage();
	var preautuacaoRecursalPage : PreautuacaoRecursalPage = new PreautuacaoRecursalPage();

    let protocolo: number = 9008;

    it ('Deveria logar no sistema', () => {
        loginPage.open();
        loginPage.login('preautuador-recursal', '123');
    });
    
    it ('Deveria acessar a tarefa de preautuação de remessa de processo recursal', () => {
        principalPage.acessarTarefa('Pré-Autuar Remessa de Processo Recursal', protocolo);
    });

    it('Deveria preencher as informações da devolução da preautuação originária', () => {
    	preautuacaoRecursalPage.preencherMotivo('Petição indevida.');
    });
    
    it('Deveria devolver', () => {
	    preautuacaoRecursalPage.devolver();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
    });
    
    xit('Deveria gerar a próxima tarefa', () => {
        principalPage.atualizarTarefas();
        expect(principalPage.tarefaPresente('Elaborar Ofício para Devolução', protocolo))
            .toBeTruthy('Tarefa com protocolo ' + protocolo + ' não encontrada.');
    });

	it('Deveria fazer o logout do sistema', () => {
		principalPage.logout();
	});

});