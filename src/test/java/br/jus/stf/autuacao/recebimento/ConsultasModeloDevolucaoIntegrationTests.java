package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * Valida a API de consulta do modelo de devolução
 * 
 * @author tomas.godoi
 *
 */
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
public class ConsultasModeloDevolucaoIntegrationTests extends IntegrationTestsSupport {

	@Test
    public void consultarModelosPorMotivo() throws Exception {
		String motivoDevolucaoId = "2";
		ResultActions result = mockMvc.perform(get("/api/devolucao/motivos-devolucao/" + motivoDevolucaoId + "/modelos"));
        
        result.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", equalTo(1)))
        	.andExpect(jsonPath("$[0].tipoDocumento", equalTo(8)));
    }
	
}