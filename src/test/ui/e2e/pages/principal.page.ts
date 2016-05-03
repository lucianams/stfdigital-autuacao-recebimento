import ElementFinder = protractor.ElementFinder;

export class PrincipalPage {
    
    private linkIniciarProcesso: ElementFinder = element(by.css('a[ui-sref="app.novo-processo"]'));
    
   
    public iniciarProcesso() : void {
        this.linkIniciarProcesso.click();
    }
    
    public login(username, password): void {
        /*this.submitButton.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submitButton.click();*/
    }
}