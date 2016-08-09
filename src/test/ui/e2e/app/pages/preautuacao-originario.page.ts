import ElementFinder = protractor.ElementFinder;

import mdHelpers = require('../shared/helpers/md-helpers');

export class PreautuacaoOriginarioPage {

	public selecionarClasse(classe: string): void {
		mdHelpers.mdSelect.selectOptionWithText(element(by.id('classes')), classe);
	}
	
	public selecionarPreferencia(...preferencias: string[]): void {
		mdHelpers.mdSelect.selectMultipleOptionsWithText(element(by.id('preferencias')), preferencias);
	}
	
	public preautuar(): void {
		element(by.id('preautuar-originario')).click();
	}
	
    public selecionarSigilo(sigilo: string): void {
        mdHelpers.mdSelect.selectOptionWithText(element(by.id('sigilo')), sigilo);
    }

	public preencherMotivo(motivo: string): void {
		element(by.id('motivo')).sendKeys(motivo);
	}

	public devolver() : void {
		element(by.id('devolver-remessa')).click();
	}
}