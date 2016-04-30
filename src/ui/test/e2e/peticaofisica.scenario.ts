declare var require;

describe('Autuação de Petições Físicas Originárias', () => {
    
    var loginPage : LoginPage = require('./pages/login.page');
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
});