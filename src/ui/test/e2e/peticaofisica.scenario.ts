/**
 * 
 */
declare var require;

describe('Autuação de Petições Físicas Originárias', function(){
    
    var loginPage : LoginPage = require('LoginPage.js');
    
    it ('Deveria logar na tela', function(){
        loginPage.login('aaa', '123');
    });
    
});