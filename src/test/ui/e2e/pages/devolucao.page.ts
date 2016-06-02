import ElementFinder = protractor.ElementFinder;

export class DevolucaoPage {
	
	public selecionarMotivo(): void {
		element(by.id('motivoDevolucao')).click();
		element.all(by.repeater('motivoDevolucao in vm.motivosDevolucao')).get(1).click();
	}
	
	public selecionarModelo(): void {
		element(by.id('modeloTexto')).click();
		element.all(by.repeater('modelo in vm.modelos')).get(0).click();
	}
	
	public aguardarCarregamentoTags(): void {
		let textoPreenchaTags = element(by.id('textoPreenchaTags'));
		browser.wait(textoPreenchaTags.isDisplayed());
	}
	
}