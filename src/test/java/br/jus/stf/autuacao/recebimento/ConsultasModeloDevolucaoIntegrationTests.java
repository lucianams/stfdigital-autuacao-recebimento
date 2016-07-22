package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;

/**
 * Valida a API de consulta do modelo de devolução
 * 
 * @author tomas.godoi
 *
 */
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false"}, classes = ApplicationContextInitializer.class)
@WithMockOauth2User("recebedor")
public class ConsultasModeloDevolucaoIntegrationTests extends IntegrationTestsSupport {

	@Test
    public void consultarModelosPorMotivo() throws Exception {
		String motivoDevolucaoId = "2";
		ResultActions result = mockMvc.perform(get("/api/devolucao/motivos-devolucao/" + motivoDevolucaoId + "/modelos"));
        
        result.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", equalTo(1)))
        	.andExpect(jsonPath("$[0].tipoDocumento", equalTo(8)));
    }
	
}