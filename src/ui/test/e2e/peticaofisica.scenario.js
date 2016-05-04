"use strict";

var login_page_1 = require("./pages/login.page");
var principal_page_1 = require("./pages/principal.page");

describe('Autuação de Petições Físicas Originárias', function () {
	
    var loginPage = new login_page_1.LoginPage();
    var principalPage = new principal_page_1.PrincipalPage();
    
    it('Deveria logar na tela', function () {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
    it('Deveria acessar a pagina de peticao física', function () {
        principalPage.iniciarProcesso();
        principalPage.iniciarPeticaoFisica();
    });
});
