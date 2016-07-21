package br.jus.stf.autuacao.recebimento;

import static br.jus.stf.core.framework.testing.Oauth2TestHelpers.oauthAuthentication;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.autuacao.recebimento.infra.DevolucaoRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.ProtocoloRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.RabbitConfiguration;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Valida a API de registro de remessas.
 * 
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false"}, classes = ApplicationContextInitializer.class)
public class RemessaOriginarioIntegrationTests extends IntegrationTestsSupport {

	@MockBean
	private DevolucaoRestAdapter devolucaoAdapter;
	
	@MockBean
    private ProtocoloRestAdapter protocoloAdapter;
	
	@MockBean
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Before
	public void configuracao() {
		MockitoAnnotations.initMocks(this);
		
		given(protocoloAdapter.novoProtocolo()).willReturn(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)));
		willDoNothing().given(rabbitTemplate).convertAndSend(RabbitConfiguration.REMESSA_REGISTRADA_QUEUE, RemessaRegistrada.class);
		willDoNothing().given(devolucaoAdapter).assinarTexto(new TextoId(9000L), "_DocTemp_12345");
	}
	
	@Test
    public void registrarUmaRemessa() throws Exception {
		String remessaValida = "{\"formaRecebimento\":\"SEDEX\", \"volumes\":1, \"tipoProcesso\":\"ORIGINARIO\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\", \"sigilo\":\"PUBLICO\"}";
		ResultActions result = mockMvc.perform(post("/api/remessas/recebimento")
				.with(oauthAuthentication("recebedor"))
				.contentType(APPLICATION_JSON).content(remessaValida));
        
        result.andExpect(status().isOk());
    }
	
	@Ignore
	@Test
    public void preautarUmaRemessa() throws Exception {
        //loadDataTests("preautarRemessaOriginario.sql");
        
        String remessaParaPreautuar = "{\"protocoloId\":@protocoloId, \"classeId\":\"ADI\", \"preferencias\":[3,8], \"sigilo\":\"PUBLICO\"}";
        String protocoloId = "9000";
        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaParaPreautuar.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	@Ignore
	@Test
    public void devolverUmaRemessa() throws Exception {
		//loadDataTests("devolverRemessaOriginario.sql");
		
		String remessaParaDevolver = "{\"protocoloId\":@protocoloId, \"motivo\":\"Remessa inválida.\"}";
        String protocoloId = "9001";

        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessaParaDevolver.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	@Ignore
	@Test
    public void prepararOficioDevolucaoDaRemessa() throws Exception {
        //loadDataTests("prepararOficioDevolucaoRemessaOriginario.sql");
		
		String remessaParaPrepararOficio = "{\"protocoloId\":@protocoloId, \"motivo\":1, \"modeloId\":1, \"textoId\":9000}";
		String protocoloId = "9002";
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaParaPrepararOficio.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	@Ignore
	@Test
    public void assinarOficioDevolucaoDaRemessa() throws Exception {
        //loadDataTests("assinarOficioDevolucaoRemessaOriginario.sql");
		
        String remessaParaAssinarOficio = "{\"protocoloId\":@protocoloId,\"documentoTemporarioId\":\"_DocTemp_12345\"}";
		String protocoloId = "9003";
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaParaAssinarOficio.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	@Ignore
	@Test
    public void naoDeveRegistrarUmaRemessaInvalida() throws Exception {
        String remessaInvalida = "{\"formaRecebimento\":\"SEDEX\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/recebimento").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }
	@Ignore
	@Test
    public void naoDevePreautuarUmaRemessaInvalida() throws Exception {
        String remessaInvalida = "{\"protocoloId\":1, \"transicao\":\"devolver\", \"movito\":\"Remessa enviado ao STF antes de passar ao STJ\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }
	@Ignore
	@Test
    public void naoDeveElaborarOficioParaDevolucaoDaRemessaInvalida() throws Exception {
        String remessaInvalida = "{}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }
	@Ignore
	@Test
    public void naoDeveAssinarUmOficioDeDevolucao() throws Exception {
        String remessaInvalida = "{}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }

}