declare var require;

describe('Autuação de Petições Físicas Originárias', () => {
    
    var loginPage : LoginPage = require('./pages/login.page');
    var principaPage : PrincipalPage = require('./pages/principal.page');
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
    it ('Deveria acessar a pagina de peticao física', () => {
        principalPage.iniciarProcesso();
    });
    
});