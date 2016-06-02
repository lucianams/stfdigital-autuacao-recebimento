import ElementFinder = protractor.ElementFinder;

export class PrincipalPage {
    
    private linkIniciarProcesso: ElementFinder = element.all(by.css('a[ui-sref="app.novo-processo"]')).get(0);
	private linkNovaPeticaoFisica: ElementFinder = element(by.css('div[ui-sref="app.novo-processo.recebimento-peticao-fisica"]'));
	private linkPreautuacao: ElementFinder = element(by.css('div[ui-sref="app.novo-processo.recebimento-preautuacao"]'));
	private linkDevolucao: ElementFinder = element(by.css('div[ui-sref="app.novo-processo.recebimento-devolucao"]'));
    
   
    public iniciarProcesso() : void {
        this.linkIniciarProcesso.click();
    }
    
    public iniciarPeticaoFisica() : void {
    	this.linkNovaPeticaoFisica.click();
    	browser.sleep(2000);
    }
    
    public iniciarPreautuacao() : void {
    	this.linkPreautuacao.click();
    	browser.sleep(2000);
    }
    
    public iniciarDevolucao(): void {
    	this.linkDevolucao.click();
    	browser.sleep(2000);
    }
}