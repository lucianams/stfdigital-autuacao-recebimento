import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage}  from "./shared/pages/principal.page";
import {RecebimentoPage} from "./pages/recebimento.page";

describe('Recebimento de Remessas de Processo do Tipo Originário', () => {	
	
    let loginPage: LoginPage = new LoginPage();
    let principalPage: PrincipalPage = new PrincipalPage();
	let recebimentoPage : RecebimentoPage = new RecebimentoPage();
                
    it ('Deveria logar no sistema', () => {
        loginPage.open();
        loginPage.login('recebedor', '123');
    });
    
    it ('Deveria acessar a página de recebimento', () => {
        principalPage.iniciarProcesso();
        principalPage.escolherProcesso('app.novo-processo.recebimento-peticao-fisica');
        principalPage.aguardarUrl('/novo-processo/recebimento');
    });
    
    it('Deveria preencher as informações da remessa', () => {
    	recebimentoPage.preencherQtdVolumes(2);
    	recebimentoPage.preencherQtdApensos(3);
    	recebimentoPage.selecionarFormaRecebimento('Fax');
    	recebimentoPage.selecionarTipoProcesso("Originário");
    	recebimentoPage.registrarPeticao();
    	principalPage.aguardarMensagemSucesso();
    });
    
	it('Deveria fazer o logout do sistema', () => {
		principalPage.logout();
	});

});