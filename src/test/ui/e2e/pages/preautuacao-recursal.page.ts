import ElementFinder = protractor.ElementFinder;

export class PreautuacaoRecursalPage {

	public selecionarClasse(nome: string) : void {
		let select = element(by.id("classes"));
        select.click();
        select.all(by.tagName('option')).filter(function(elem, index) {
            return elem.getText().then(function(text) {
                return text === nome;
            });
        }).then(function(filteredElements){
            filteredElements[0].click();
        });
        /*
        element(by.id('classes')).click();
		element.all(by.repeater('classe in preautuacao.classes')).get(2).click();
        */
	};
	
	public selecionarPreferencia() : void {
		element(by.id('preferencias')).click();
		element.all(by.repeater('preferencia in preautuacao.preferencias')).get(1).click();
		browser.driver.sleep(200);
		browser.actions().sendKeys(protractor.Key.ESCAPE).perform();
		element(by.id('motivo')).click();
	};
	
	public registrarPreautuacao() : void {
		element(by.id('btnPreautuarRecursal')).click();
	};
	
    /*
	public selecionarClasseProcessual(indice: number, descricao: string) {
        let select = element(by.id("tipoAnexoId-" + indice));
        browser.executeScript("arguments[0].scrollIntoView();", select.getWebElement());
        select.click();
        select.all(by.tagName('option')).filter(function(elem, index) {
            return elem.getText().then(function(text) {
                return text === descricao;
            });
        }).then(function(filteredElements){
            filteredElements[0].click();
        });
    }
	*/
}