import ElementFinder = protractor.ElementFinder;

export class PreautuacaoPage {
		
	public selecionarClasse() : void {
		element(by.id('classes')).click();
		element.all(by.repeater('classe in preautuacao.classes')).get(2).click();
	};
	
	public selecionarPreferencia() : void {
		element(by.id('preferencias')).click();
		element.all(by.repeater('preferencia in preautuacao.preferencias')).get(1).click();
		browser.driver.sleep(200);
		browser.actions().sendKeys(protractor.Key.ESCAPE).perform();
		element(by.id('motivo')).click();
	};
	
	public registrarPreautuacao() : void {
		element(by.id('btnRegistrarpreautuacao')).click();
	};
}