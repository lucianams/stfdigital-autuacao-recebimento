import ElementFinder = protractor.ElementFinder;

import mdHelpers = require('../shared/helpers/md-helpers');

export class PreautuacaoRecursalPage {

    public preencherNumeroOrigem(numero: number) {
		element(by.id('txtNumeroProcessoOrigem')).sendKeys(numero.toString());
    }

    public preencherNumeroUnico(numero: number) {
		element(by.id('txtNumeroUnico')).sendKeys(numero.toString());
    }

	public selecionarClasse(classe: string): void {
        mdHelpers.selectOptionWithText(element(by.id('cboClasses')), classe);
	}

    public selecionarSigilo(sigilo: string): void {
        mdHelpers.selectOptionWithText(element(by.id('cboSigilo')), sigilo);
	}
	
	public selecionarPreferencia(...preferencias: string[]): void {
        mdHelpers.multipleSelectOptionsWithText(element(by.id('cboPreferencias')), preferencias);
	};
	
	public registrarPreautuacao() : void {
		element(by.id('preautuar-recursal')).click();
	}

}