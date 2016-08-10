package br.jus.stf.autuacao.recebimento;

import static com.github.jsonj.tools.JsonBuilder.field;
import static com.github.jsonj.tools.JsonBuilder.object;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.github.jsonj.JsonObject;

import br.jus.stf.autuacao.recebimento.infra.ProtocoloRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.RabbitConfiguration;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Valida a API de registro de remessas.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 01.08.2016
 */
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false"}, classes = ApplicationContextInitializer.class)
@WithMockOauth2User("recebedor")
@Transactional
public class RemessaRecursalIntegrationTests extends IntegrationTestsSupport {

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
	}
	
	@Test
	@WithMockOauth2User(value = "recebedor", components = "registrar-remessa")
    public void registrarUmaRemessa() throws Exception {
		JsonObject remessaValida = object(
			field("formaRecebimento", "SEDEX"),
			field("volumes", 1),
			field("tipoProcesso", "RECURSAL"),
			field("apensos", 1),
			field("numeroSedex", "SR123456789BR"),
			field("sigilo", "PUBLICO")
		);
		ResultActions result = mockMvc.perform(post("/api/remessas/recebimento")
				.contentType(APPLICATION_JSON).content(remessaValida.toString()));
        
        result.andExpect(status().isOk());
    }
	
	@Test
	@WithMockOauth2User(value = "recebedor", components = "preautuar-recursal")
    public void preautarUmaRemessa() throws Exception {
        loadDataTests("preautuarRemessaRecursal.sql");
        
		JsonObject remessaParaPreautuar = object(
			field("protocoloId", 9007),
			field("classeId", "RE"),
			field("numeroProcessoOrigem", "RE-100"),
			field("numeroUnicoProcesso", "00000100-15.2008.100.0000"),
			field("sigilo", "PUBLICO")
		);

		ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao-recursal").contentType(APPLICATION_JSON)
				.content(remessaParaPreautuar.toString()));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void naoDevePreautuarUmaRemessaInvalida() throws Exception {
        JsonObject remessaInvalida = object(
        		field("classeId", "RE"),
    			field("numeroProcessoOrigem", "RE-100"),
    			field("numeroUnicoProcesso", "00000100-15.2008.100.0000"),
    			field("sigilo", "PUBLICO")
        );
        
		ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao-recursal").contentType(APPLICATION_JSON)
				.content(remessaInvalida.toString()));
        
        result.andExpect(status().isBadRequest());
    }

}