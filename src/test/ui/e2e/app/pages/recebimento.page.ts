import ElementFinder = protractor.ElementFinder;

import helpers = require('protractor-helpers');
import mdHelpers = require('../shared/helpers/md-helpers');

export class RecebimentoPage {
	
	public preencherQtdVolumes(quantidade : number) : void {
		element(by.id('qtdVolumes')).sendKeys(quantidade.toString());
	}
	
	public selecionarFormaRecebimento(formaRecebimento: string) : void {
		mdHelpers.selectOptionWithText(element(by.id('formaRecebimento')), formaRecebimento);
	}
	
	public preencherQtdApensos(quantidade : number) : void {
		element(by.id('qtdApensos')).sendKeys(quantidade.toString());
	}
	
	public preencherNumSedex(quantidade : number) : void {
		element(by.id('numeroSedex')).sendKeys(quantidade.toString());
	}

    public selecionarSigilo(sigilo: string): void {
        mdHelpers.selectOptionWithText(element(by.id('sigilo')), sigilo);
    }

	public selecionarTipoProcesso(tipoProcesso: string) : void {
		mdHelpers.selectOptionWithText(element(by.id('tipoProcesso')), tipoProcesso);
	}
	
	public registrarRemessa() : void {
		element(by.id('registrar-remessa')).click();
	}
	
}