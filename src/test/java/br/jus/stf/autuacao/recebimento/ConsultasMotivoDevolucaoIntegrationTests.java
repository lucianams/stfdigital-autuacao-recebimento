package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * Valida a API de consulta de motivo de devolução
 * 
 * @author tomas.godoi
 *
 */
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
@WebIntegrationTest({"server.port:0", "eureka.client.enabled:false"})
public class ConsultasMotivoDevolucaoIntegrationTests extends IntegrationTestsSupport {

	@Test
    public void listarMotivosDevolucao() throws Exception {
		ResultActions result = mockMvc.perform(get("/api/devolucao/motivos-devolucao"));
        
        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
    }
	
}