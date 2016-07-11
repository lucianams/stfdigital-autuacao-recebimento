import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage}  from "./shared/pages/principal.page";
import {PreautuacaoPage} from "./pages/preautuacao.page";

xdescribe('Preautuação de Remessa Originária', () => {	
	
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