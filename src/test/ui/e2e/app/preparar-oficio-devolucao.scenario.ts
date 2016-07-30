import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage}  from "./shared/pages/principal.page";
import {PrepararOficioDevolucaoPage} from "./pages/preparar-oficio-devolucao.page";

describe('Preparação do Ofício de Devolução', () => {	
	
    var loginPage: LoginPage = new LoginPage();
    var principalPage: PrincipalPage = new PrincipalPage();
	let devolucaoPage: PrepararOficioDevolucaoPage = new PrepararOficioDevolucaoPage();

    let protocolo: number = 9002;

    it ('Deveria logar no sistema', () => {
        loginPage.open();
        loginPage.login('cartoraria', '123');
    });
    
    it ('Deveria acessar a tarefa de preparação do ofício de devolução', () => {
        principalPage.acessarTarefa('Elaborar Ofício para Devolução', protocolo);
    });

    it('Deveria preencher motivo e modelo', () => {
	    devolucaoPage.selecionarMotivo('Faltam Peças');
	    devolucaoPage.selecionarModelo('Ofício de devolução de remessa');
	    devolucaoPage.aguardarCarregamentoTags();
    });
    
	it('Deveria gerar texto', () => {
	    devolucaoPage.gerarTexto();
	    devolucaoPage.aguardarTextoCarregado();
    });
	
	it('Deveria finalizar elaboração do texto', () => {
	    devolucaoPage.finalizarElaboracao();
        principalPage.aguardarMensagem();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
    });

    it('Deveria fazer o logout do sistema', () => {
		principalPage.logout();
	});

});