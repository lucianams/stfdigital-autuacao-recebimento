package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;

/**
 * Valida a API de consulta de remessas
 * 
 * @author tomas.godoi
 *
 */
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false"}, classes = ApplicationContextInitializer.class)
@WithMockOauth2User("recebedor")
@Transactional
public class ConsultasRemessaIntegrationTests extends IntegrationTestsSupport {
	
	@Test
    public void consultarDevolucaoDeUmaRemessa() throws Exception {
		loadDataTests("assinarOficioDevolucaoRemessaOriginario.sql");
		
		String protocoloId = "9003";
		
		ResultActions result = mockMvc.perform(get("/api/remessas/" + protocoloId + "/devolucao"));
        
        result.andExpect(status().isOk()).andExpect(jsonPath("$.modeloDevolucao.id", equalTo(1)));
    }
	
}
