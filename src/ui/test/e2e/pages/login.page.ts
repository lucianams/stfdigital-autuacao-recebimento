import ElementFinder = protractor.ElementFinder;

declare var exports;

class LoginPage {
    
    private usernameInput: ElementFinder = element(by.model('vm.form.usuario'));
    private passwordInput: ElementFinder = element(by.model('vm.form.senha'));
    private submitButton: ElementFinder = element(by.css('button[type="submit"]'));
    
    public open(): void {
        browser.get('/login');
    }
    
    public login(username, password): void {
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submitButton.click();
    }
}

exports = LoginPage;