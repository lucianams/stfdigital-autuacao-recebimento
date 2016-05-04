"use strict";

var PrincipalPage = (function () {
	
    function PrincipalPage() {
        this.linkIniciarProcesso = element(by.id('bntIniciarProcesso'));
        this.linkNovaPeticaoFisica = element(by.css('div[ui-sref="app.novo-processo.recebimento"]'));
    }
    
    PrincipalPage.prototype.iniciarProcesso = function () {
        this.linkIniciarProcesso.click();
        this.linkNovaPeticaoFisica.click();
    };
    
    PrincipalPage.prototype.login = function (username, password) {
        /*this.submitButton.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submitButton.click();*/
    };
    
    return PrincipalPage;
}());

exports.PrincipalPage = PrincipalPage;
