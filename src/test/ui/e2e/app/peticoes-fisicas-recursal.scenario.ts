import {LoginPage} from "./shared/pages/login.page";
import {PrincipalPage} from "./shared/pages/principal.page";

import {RecebimentoPage} from "./pages/recebimento.page";

describe('Recebimento de Petições Físicas de Processo do Tipo Recursal', () => {

    let loginPage: LoginPage = new LoginPage();
    let principalPage: PrincipalPage = new PrincipalPage();
	let recebimentoPage : RecebimentoPage = new RecebimentoPage();

    let proximoProtocolo: number;

    it ('Deveria logar no sistema', () => {
        loginPage.open();
        loginPage.login('recebedor', '123');
    });

    it('Deveria prever o próximo protocolo', () => {
        principalPage.detectarProximoProtocolo((val) => val < 9000).then((protocolo) => {
            proximoProtocolo = protocolo;
        });
    });

    it ('Deveria acessar a página de remessa', () => {
        principalPage.iniciarProcesso();
        principalPage.escolherProcesso('app.novo-processo.recebimento-peticao-fisica');
        principalPage.aguardarUrl('/novo-processo/recebimento');
    });

    it('Deveria preencher as informações da remessa', () => {
    	recebimentoPage.preencherQtdVolumes(2);
    	recebimentoPage.preencherQtdApensos(3);
    	recebimentoPage.selecionarFormaRecebimento('Malote');
    	recebimentoPage.selecionarTipoProcesso("Recursal");
    });

    it('Deveria registrar a remessa', () => {
        recebimentoPage.registrarRemessa();
        expect(principalPage.exibiuMensagemSucesso()).toBeTruthy();
        expect(principalPage.mensagem()).toEqual('Remessa registrada com sucesso!');
    });

    it('Deveria gerar a próxima tarefa', () => {
        principalPage.atualizarTarefas();
        expect(principalPage.tarefaPresente('Pré-Autuar Remessa de Processo Recursal', proximoProtocolo))
            .toBeTruthy('Tarefa com protocolo ' + proximoProtocolo + ' não encontrada.');
    });

	it('Deveria fazer o logout do sistema', () => {
		principalPage.logout();
	});

});