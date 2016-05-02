import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";

describe('Autuação de Petições Físicas Originárias', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
    it ('Deveria acessar a pagina de peticao física', () => {
        principalPage.iniciarProcesso();
    });
    
});