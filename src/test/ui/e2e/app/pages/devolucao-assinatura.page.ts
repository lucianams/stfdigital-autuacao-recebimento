export class DevolucaoAssinaturaPage {

	public open(): void {
		browser.get('/novo-processo/devolucao-assinatura');
	}

	public selecionarDevolucoes(): void {
		element.all(by.repeater('devolucao in vm.devolucoes')).all(by.css('[type=checkbox]')).get(0).click();
	}

	public assinar(): void {
		element(by.id('btnAssinar')).click();
		browser.sleep(5000);
	}

}