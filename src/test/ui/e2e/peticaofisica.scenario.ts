import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";
import {RecebimentoPage} from "./pages/recebimento.page";

describe('Autuação de Petições Físicas Originárias', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	var recebimentoPage : RecebimentoPage = new RecebimentoPage();
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
    it ('Deveria acessar a pagina de peticao física', () => {
        principalPage.iniciarProcesso();
        principalPage.iniciarPeticaoFisica();
    });
    
    it('Deveria preencher as informações da petição física', () => {
    	recebimentoPage.preencherQtdVolumes(2);
    	recebimentoPage.preencherQtdApensos(3);
    	recebimentoPage.selecionarFormaRecebimento();
    	recebimentoPage.selecionarTipoRecebimento();
    	recebimentoPage.registrarPeticao();
    });
    
});