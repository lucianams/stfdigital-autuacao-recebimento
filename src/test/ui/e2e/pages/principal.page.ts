import ElementFinder = protractor.ElementFinder;

export class PrincipalPage {
    
    private linkIniciarProcesso: ElementFinder = element.all(by.css('a[ui-sref="app.novo-processo"]')).get(0);
	private linkNovaPeticaoFisica: ElementFinder = element(by.css('div[ui-sref="app.novo-processo.recebimento"]'));
    
   
    public iniciarProcesso() : void {
        this.linkIniciarProcesso.click();
    }
    
    public iniciarPeticaoFisica() : void {
    	this.linkNovaPeticaoFisica.click();
    	browser.sleep(5000);
    }
}