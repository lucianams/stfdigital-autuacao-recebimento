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