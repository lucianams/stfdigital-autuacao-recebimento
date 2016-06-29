import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";
import {PreautuacaoPage} from "./pages/preautuacao.page";

describe('Preautuação de Petições Físicas Originárias', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	var preautuacaoPage : PreautuacaoPage = new PreautuacaoPage();
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('preautuador', '123');
    });
    
    it ('Deveria acessar a pagina de preautuacao', () => {
        preautuacaoPage.open();
    });
    
    it('Deveria selecionar a classe', () => {
    	preautuacaoPage.selecionarClasse();
    });
    
    it('Deveria selecionar a preferência', () => {
    	preautuacaoPage.selecionarPreferencia();
    });
    
    it('Deveria registrar a pré-autuação', () => {
	    preautuacaoPage.registrarPreautuacao();
    });
    
});