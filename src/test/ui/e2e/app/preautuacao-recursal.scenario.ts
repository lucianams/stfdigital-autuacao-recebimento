import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";
import {PreautuacaoRecursalPage} from "./pages/preautuacao-recursal.page";

describe('Preautuação de Remessa Recursal', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	var preautuacaoRecursalPage : PreautuacaoRecursalPage = new PreautuacaoRecursalPage();
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('preautuador-recursal', '123');
    });
    
    it ('Deveria acessar a página de preautuação recursal', () => {
        preautuacaoRecursalPage.open();
    });
    
    it('Deveria preencher as informações da preautuação recursal', () => {
    	preautuacaoRecursalPage.selecionarClasse("RE");
        preautuacaoRecursalPage.selecionarPreferencia();
        preautuacaoRecursalPage.registrarPreautuacao();
        principalPage.aguardarMensagemSucesso();
    });
    
});