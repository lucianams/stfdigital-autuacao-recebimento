package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;

/**
 * Valida a API de consulta de motivo de devolução
 * 
 * @author tomas.godoi
 * @since 27.06.2016
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
@WithMockOauth2User("cartoraria")
public class ConsultasMotivoDevolucaoIntegrationTests extends IntegrationTestsSupport {

    @Test
    public void listarMotivosDevolucao() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/devolucao/motivos-devolucao"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
    }

}