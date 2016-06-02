import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";
import {PreautuacaoPage} from "./pages/preautuacao.page";
import {DevolucaoPage} from "./pages/devolucao.page";

describe('Devolucação de Petições Físicas Originárias', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	var preautuacaoPage : PreautuacaoPage = new PreautuacaoPage();
	let devolucaoPage: DevolucaoPage = new DevolucaoPage();

    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
    it ('Deveria acessar a pagina de preautuacao', () => {
        principalPage.iniciarProcesso();
        principalPage.iniciarPreautuacao();
    });
    
    it('Deveria devolver a remessa', () => {
    	preautuacaoPage.selecionarClasse();
    	preautuacaoPage.selecionarPreferencia();
    	preautuacaoPage.devolver();
    });
    
    it('Deveria acessar a pagina de devolução', () => {
    	principalPage.iniciarProcesso();
        principalPage.iniciarDevolucao();
    });
    
    it('Deveria preencher motivo e modelo', () => {
	    devolucaoPage.selecionarMotivo();
	    devolucaoPage.selecionarModelo();
	    devolucaoPage.aguardarCarregamentoTags();
    });
    
	it('Deveria preencher tags', () => {
	    
    });
    
	it('Deveria gerar texto', () => {
	    
    });
	
	it('Deveria finalizar elaboração do texto', () => {
	    
    });
});