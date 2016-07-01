import {LoginPage} from "./pages/login.page";
import {PrincipalPage}  from "./pages/principal.page";
import {PrepararOficioDevolucaoPage} from "./pages/preparar-oficio-devolucao.page";

xdescribe('Preparação do Ofício de Devolução', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	let devolucaoPage: PrepararOficioDevolucaoPage = new PrepararOficioDevolucaoPage();

    it ('Deveria logar na tela', () => {
        loginPage.open();
        loginPage.login('aaa', '123');
    });
    
    it ('Deveria acessar a pagina de devolução', () => {
        devolucaoPage.open();
    });
    
    it('Deveria preencher motivo e modelo', () => {
	    devolucaoPage.selecionarMotivo();
	    devolucaoPage.selecionarModelo();
	    devolucaoPage.aguardarCarregamentoTags();
    });
    
	it('Deveria gerar texto', () => {
	    devolucaoPage.gerarTexto();
	    devolucaoPage.aguardarTextoCarregado();
    });
	
	it('Deveria finalizar elaboração do texto', () => {
	    devolucaoPage.finalizarElaboracao();
    });
});