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
	
	public gerarTexto(): void {
		let botaoGerarTexto = element(by.id('btnGerarTexto'));
		browser.executeScript("arguments[0].scrollIntoView();", botaoGerarTexto.getWebElement());
		botaoGerarTexto.click();
	}
	
	public aguardarTextoCarregado(): void {
		browser.wait(() => {
			var els = element.all(by.css('.editor-directive.edicao-iniciada'));
			return els.count().then(function(size) {
				return size > 0;
			});
		}, 10000);
	}
	
	public finalizarElaboracao(): void {
		let botaoFinalizarTexto = element(by.id('btnFinalizarTexto'));
		browser.executeScript("arguments[0].scrollIntoView();", botaoFinalizarTexto.getWebElement());
		botaoFinalizarTexto.click();
	}
	
}