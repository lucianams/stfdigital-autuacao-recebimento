import ElementFinder = protractor.ElementFinder;

import helpers = require('protractor-helpers');

import mdHelpers = require('../shared/helpers/md-helpers');

export class PrepararOficioDevolucaoPage {

	public selecionarMotivo(motivoDevolucao: string): void {
		mdHelpers.mdSelect.selectOptionWithText(element(by.id('motivoDevolucao')), motivoDevolucao);
	}
	
	public selecionarModelo(modelo: string): void {
		mdHelpers.mdSelect.selectOptionWithText(element(by.id('modeloTexto')), modelo);
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
		helpers.waitForElement(element(by.css('.editor-directive.edicao-iniciada')), 20000);
	}

	public finalizarElaboracao(): void {
		let botaoFinalizarTexto = element(by.id('btnFinalizarTexto'));
		browser.executeScript("arguments[0].scrollIntoView();", botaoFinalizarTexto.getWebElement());
		botaoFinalizarTexto.click();
	}
	
}