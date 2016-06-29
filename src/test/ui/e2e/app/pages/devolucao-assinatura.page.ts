import helpers = require('protractor-helpers');

export class DevolucaoAssinaturaPage {

	public open(): void {
		browser.get('/novo-processo/devolucao-assinatura');
		helpers.waitForElement(element(by.css('[translate="REMESSA.DEVOLUCAO-ASSINATURA.TITULO"]')));
	}

	public selecionarDevolucoes(): void {
		element.all(by.repeater('devolucao in vm.devolucoes')).all(by.css('[type=checkbox]')).click();
	}

	public assinar(): void {
		element(by.id('btnAssinar')).click();
	}

}