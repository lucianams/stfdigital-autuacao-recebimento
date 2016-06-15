import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";
import {RecebimentoPage} from "./pages/recebimento.page";

describe('Recebimento de Petições Físicas Originárias', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	var recebimentoPage : RecebimentoPage = new RecebimentoPage();
                
    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('recebedor', 'recebedor-123');
    });
    
    it ('Deveria acessar a pagina de petição física', () => {
        principalPage.iniciarProcesso();
        principalPage.escolherProcesso('app.novo-processo.recebimento-peticao-fisica');
        principalPage.aguardarUrl('/novo-processo/recebimento');
    });
    
    it('Deveria preencher as informações da petição física', () => {
    	recebimentoPage.preencherQtdVolumes(2);
    	recebimentoPage.preencherQtdApensos(3);
    	recebimentoPage.selecionarFormaRecebimento();
    	recebimentoPage.selecionarTipoRecebimento();
    	recebimentoPage.registrarPeticao();
    	principalPage.aguardarUrl('/tarefas/minhas-tarefas');
    });
    
});