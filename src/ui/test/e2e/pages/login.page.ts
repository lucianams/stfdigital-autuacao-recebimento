/**
 * Page da tela de login
 */
declare var module;

class LoginPage {
    usernameInput = element(by.id('username'));
    passwordInput = element(by.id('password'));
    
    submitButton = element(by.css('Button[ng-click^=["loginCtrl.login"]'));
    
    login(username, password) : void {
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.submitButton.click();
    }
}

module.exports = new LoginPage();