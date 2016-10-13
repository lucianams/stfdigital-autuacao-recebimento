import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage}  from "./shared/pages/principal.page";
import {PreautuacaoOriginarioPage} from "./pages/preautuacao-originario.page";

describe("Preautuação de Remessa de Processo Originário - Preautuar", () => {
    let loginPage: LoginPage = new LoginPage();
    let principalPage: PrincipalPage = new PrincipalPage();
    let preautuacaoOriginarioPage: PreautuacaoOriginarioPage = new PreautuacaoOriginarioPage();

    it ("Deveria logar no sistema", () => {
        loginPage.open();
        loginPage.login("preautuador-originario", "123");
    });

    it ("Deveria acessar a tarefa de preautuação de remessa de processo originário", () => {
        principalPage.acessarTarefa("Pré-Autuar Remessa de Processo Originário", 9000);
    });

    it("Deveria preencher as informações da preautuação originária", () => {
        preautuacaoOriginarioPage.selecionarClasse("MANDADO DE SEGURANÇA");
        preautuacaoOriginarioPage.selecionarPreferencia("Medida Liminar", "Réu Preso");
        preautuacaoOriginarioPage.selecionarSigilo("Público");
        preautuacaoOriginarioPage.preencherMotivo("Petição OK.");
    });

    it("Deveria preautuar", () => {
        preautuacaoOriginarioPage.preautuar();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
        expect(principalPage.mensagem()).toEqual("Remessa preautuada com sucesso.");
    });

    it("Deveria fazer o logout do sistema", () => {
        principalPage.logout();
    });
});

describe("Preautuação de Remessa de Processo Originário - Devolver", () => {
    let loginPage: LoginPage = new LoginPage();
    let principalPage: PrincipalPage = new PrincipalPage();
    let preautuacaoOriginarioPage: PreautuacaoOriginarioPage = new PreautuacaoOriginarioPage();

    let protocolo: number = 9001;

    it ("Deveria logar no sistema", () => {
        loginPage.open();
        loginPage.login("preautuador-originario", "123");
    });

    it ("Deveria acessar a tarefa de preautuação de remessa de processo originário", () => {
        principalPage.acessarTarefa("Pré-Autuar Remessa de Processo Originário", protocolo);
    });

    it("Deveria preencher as informações da devolução da preautuação originária", () => {
        preautuacaoOriginarioPage.preencherMotivo("Petição indevida.");
    });

    it("Deveria devolver", () => {
        preautuacaoOriginarioPage.devolver();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
    });

    it("Deveria fazer o logout do sistema", () => {
        principalPage.logout();
    });

});