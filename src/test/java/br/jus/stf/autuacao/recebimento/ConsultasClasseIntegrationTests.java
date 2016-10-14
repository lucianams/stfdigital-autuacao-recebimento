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
 * Valida a API de consulta de classes
 * 
 * @author Rafael Alencar
 * @since 13.10.2016
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
@Transactional
public class ConsultasClasseIntegrationTests extends IntegrationTestsSupport {

    @Test
    @WithMockOauth2User("preautuador-originario")
    public void listarClasses() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/remessas/classes"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(34)));
    }

    @Test
    @WithMockOauth2User("preautuador-recursal")
    public void consultarClassesPorTipoRemessa() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/remessas/classes").param("tipoRemessa", "RECURSAL"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
    }

}