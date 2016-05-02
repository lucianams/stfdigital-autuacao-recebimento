import ElementFinder = protractor.ElementFinder;

declare var exports;

class PrincipalPage {
    
    private linkIniciarProcesso: ElementFinder = element(by.css('a[ui-sref="app.novo-processo"]'));
    
   
    public iniciciarProcesso() : void {
        this.linkIniciarProcesso.click();
    }
    
    public iniciar
    public login(username, password): void {
        this.submitButton.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submitButton.click();
    }
}

exports = LoginPage;