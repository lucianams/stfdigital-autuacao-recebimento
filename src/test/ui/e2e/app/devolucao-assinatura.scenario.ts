import {LoginPage} from './shared/pages/login.page';
import {PrincipalPage} from './shared/pages/principal.page';
import {DevolucaoAssinaturaPage} from './pages/devolucao-assinatura.page';

import mockCryptoModule from './shared/mocks/crypto';

import util = require('util');

xdescribe('Assinatura do Ofício de Devolução', () => {

	let loginPage: LoginPage = new LoginPage();
	let principalPage: PrincipalPage = new PrincipalPage();
	let devolucaoAssinaturaPage: DevolucaoAssinaturaPage = new DevolucaoAssinaturaPage();

	it('Deveria logar no sistema', ()=> {
		loginPage.open();
		loginPage.login('gestor-recebimento', '123');
	});

	it('Deveria injetar o módulo mock de criptografia', () => {
		browser.addMockModule('e2e.mocks.crypto', mockCryptoModule);
	});

	it('Deveria acessar a página de devolução de assinatura', () => {
		devolucaoAssinaturaPage.open();
	});

	it('Deveria selecionar as devoluções para assinatura', () => {
		devolucaoAssinaturaPage.selecionarDevolucoes();
		devolucaoAssinaturaPage.assinar();
		principalPage.aguardarMensagemSucesso(10000);
	});

});