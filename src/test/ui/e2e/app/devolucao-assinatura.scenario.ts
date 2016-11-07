import {DevolucaoAssinaturaPage} from "./pages/devolucao-assinatura.page";
import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage} from "./shared/pages/principal.page";

import mockCryptoModule from "./shared/mocks/crypto";

import util = require("util");

xdescribe("Assinatura do Ofício de Devolução", () => {
    let loginPage: LoginPage = new LoginPage();
    let principalPage: PrincipalPage = new PrincipalPage();
    let devolucaoAssinaturaPage: DevolucaoAssinaturaPage = new DevolucaoAssinaturaPage();

    let protocolo: number = 9003;

    it("Deveria injetar o módulo mock de criptografia", () => {
        browser.addMockModule("e2e.mocks.crypto", mockCryptoModule);
    });

    it ("Deveria logar no sistema", () => {
        loginPage.open();
        loginPage.login("gestor-recebimento", "123");
    });

    it ("Deveria acessar a tarefa de assinatura do ofício de devolução", () => {
        principalPage.acessarTarefa("Assinar Ofício de Devolução", protocolo);
    });

    it("Deveria selecionar as devoluções para assinatura", () => {
        devolucaoAssinaturaPage.selecionarDevolucoes();
    });

    it("Deveria assinar as devoluções", () => {
        devolucaoAssinaturaPage.assinar();
        principalPage.aguardarMensagem();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
    });

    it("Deveria fazer o logout do sistema", () => {
        principalPage.logout();
    });
});