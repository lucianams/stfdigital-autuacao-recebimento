package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.hasSize;
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
 * Valida a API de consulta de sigilos.
 * 
 * @author Rafael Alencar
 * @since 13.10.2016
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
@Transactional
public class ConsultasSigiloIntegrationTests extends IntegrationTestsSupport {

    @Test
    @WithMockOauth2User("recebedor")
    public void consultarSigilos() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/remessas/sigilos"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

}