package br.jus.stf.autuacao.recebimento;

import static com.github.jsonj.tools.JsonBuilder.array;
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

import br.jus.stf.autuacao.recebimento.infra.DevolucaoRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.ProtocoloRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.RabbitConfiguration;
import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;
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
@WithMockOauth2User("recebedor")
@Transactional
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
		JsonObject remessaValida = object(
			field("formaRecebimento", "SEDEX"),
			field("volumes", 1),
			field("tipoProcesso", "ORIGINARIO"),
			field("apensos", 1),
			field("numeroSedex", "SR123456789BR"),
			field("sigilo", "PUBLICO")
		);
		ResultActions result = mockMvc.perform(post("/api/remessas/recebimento")
				.contentType(APPLICATION_JSON).content(remessaValida.toString()));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void preautarUmaRemessa() throws Exception {
        loadDataTests("preautarRemessaOriginario.sql");
        
		JsonObject remessaParaPreautuar = object(
			field("protocoloId", 9000),
			field("classeId", "ADI"),
			field("preferencias", array(3, 8)),
			field("sigilo", "PUBLICO")
		);

        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaParaPreautuar.toString()));
        
        result.andExpect(status().isOk());
    }

	@Test
    public void devolverUmaRemessa() throws Exception {
		loadDataTests("devolverRemessaOriginario.sql");

        JsonObject remessaParaDevolver = object(
        	field("protocoloId", 9001),
        	field("motivo", "Remessa inválida.")
        );
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessaParaDevolver.toString()));
        
        result.andExpect(status().isOk());
    }

	@Test
    public void prepararOficioDevolucaoDaRemessa() throws Exception {
        loadDataTests("prepararOficioDevolucaoRemessaOriginario.sql");
		
        JsonObject remessaParaPrepararOficio = object(
        	field("protocoloId", 9002),
        	field("motivo", 1),
        	field("modeloId", 1),
        	field("textoId", 9000)
        );
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaParaPrepararOficio.toString()));
        
        result.andExpect(status().isOk());
    }

	@Test
    public void assinarOficioDevolucaoDaRemessa() throws Exception {
        loadDataTests("assinarOficioDevolucaoRemessaOriginario.sql");
		
        JsonObject remessaParaAssinarOficio = object(
        	field("protocoloId", 9003),
        	field("documentoTemporarioId", "_DocTemp_12345")
        );
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaParaAssinarOficio.toString()));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void naoDeveRegistrarUmaRemessaInvalida() throws Exception {
        JsonObject remessaInvalida = object(
        	field("formaRecebimento", "SEDEX"),
        	field("apensos", 1),
        	field("numeroSedex", "SR123456789BR")
        );
        
        ResultActions result = mockMvc.perform(post("/api/remessas/recebimento").contentType(APPLICATION_JSON).content(remessaInvalida.toString()));
        
        result.andExpect(status().isBadRequest());
    }
	
	@Test
    public void naoDevePreautuarUmaRemessaInvalida() throws Exception {
        JsonObject remessaInvalida = object(
        	field("protocoloId", 1),
        	field("transicao", "devolver"),
        	field("motivo", "Remessa enviado ao STF antes de passar ao STJ")
        );
        
        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaInvalida.toString()));
        
        result.andExpect(status().isBadRequest());
    }

	@Test
    public void naoDeveElaborarOficioParaDevolucaoDaRemessaInvalida() throws Exception {
		JsonObject remessaInvalida = object().get();
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaInvalida.toString()));
        
        result.andExpect(status().isBadRequest());
    }
	
	@Test
    public void naoDeveAssinarUmOficioDeDevolucao() throws Exception {
        JsonObject remessaInvalida = object().get();
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaInvalida.toString()));
        
        result.andExpect(status().isBadRequest());
    }

}