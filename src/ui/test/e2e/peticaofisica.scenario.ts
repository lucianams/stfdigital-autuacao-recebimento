import {LoginPage} from "./pages/login.page";

describe('Autuação de Petições Físicas Originárias', () => {	
	
    var loginPage: LoginPage = new LoginPage();
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
});