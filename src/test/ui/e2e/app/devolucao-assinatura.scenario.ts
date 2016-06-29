import {LoginPage} from './pages/login.page';
import {PrincipalPage} from './pages/principal.page';
import {DevolucaoAssinaturaPage} from './pages/devolucao-assinatura.page';

import mockCryptoModule from './mocks/crypto';

import util = require('util');

describe('Assinatura do Ofício de Devolução', () => {

	let loginPage: LoginPage = new LoginPage();
	let principalPage: PrincipalPage = new PrincipalPage();
	let devolucaoAssinaturaPage: DevolucaoAssinaturaPage = new DevolucaoAssinaturaPage();

	it('Deveria logar no sistema', ()=> {
		//loginPage.open();
		//loginPage.login('gestor-recebimento', '123');
	});

	it('Deveria injetar o módulo mock de criptografia', () => {
		//browser.addMockModule('e2e.mocks.crypto', mockCryptoModule);
		//browser.refresh();
	});

	it('Deveria acessar a página de devolução de assinatura', () => {
		devolucaoAssinaturaPage.open();
		browser.addMockModule('e2e.mocks.crypto', mockCryptoModule);
		browser.refresh();
		browser.addMockModule('e2e.mocks.crypto', mockCryptoModule);
		browser.sleep(5000);
	});

	it('Deveria selecionar as devoluções para assinatura', () => {
		browser.addMockModule('e2e.mocks.crypto', mockCryptoModule);
		devolucaoAssinaturaPage.selecionarDevolucoes();
		browser.addMockModule('e2e.mocks.crypto', mockCryptoModule);
		devolucaoAssinaturaPage.assinar();
		browser.sleep(2000);
		browser.manage().logs().get('browser').then(
   			function(logs){ console.log('LOG: ' + util.inspect(logs)); }
		);
	});

});