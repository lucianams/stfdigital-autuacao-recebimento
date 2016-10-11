package br.jus.stf.autuacao.recebimento;

import static org.hamcrest.Matchers.equalTo;
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
 * Valida a API de consulta de remessas
 * 
 * @author tomas.godoi
 * @since 27.06.2016
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
@Transactional
public class ConsultasRemessaIntegrationTests extends IntegrationTestsSupport {

    @Test
    @WithMockOauth2User("gestor-recebimento")
    public void consultarDevolucaoDeUmaRemessa() throws Exception {
        loadDataTests("assinarOficioDevolucaoRemessaOriginario.sql");

        String protocoloId = "9003";

        ResultActions result = mockMvc.perform(get("/api/remessas/" + protocoloId + "/devolucao"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.modeloDevolucao.id", equalTo(1)));
    }

    @Test
    @WithMockOauth2User("preautuador-originario")
    public void listarClasses() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/classes"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(34)));
    }

    @Test
    @WithMockOauth2User("recebedor")
    public void consultarSigilos() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/remessas/sigilos"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockOauth2User("recebedor")
    public void consultarFormasRecebimento() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/remessas/formas-recebimento"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @WithMockOauth2User("preautuador-recursal")
    public void consultarClassesPorTipoRemessa() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/classes").param("tipoRemessa", "RECURSAL"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @WithMockOauth2User("gestor-recebimento")
    public void listar() throws Exception {
        loadDataTests("consultarRemessa-limpar.sql", "consultarRemessa.sql");

        ResultActions result = mockMvc.perform(get("/api/remessas"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockOauth2User("recebedor")
    public void consultarRemessa() throws Exception {
        loadDataTests("consultarRemessa-limpar.sql", "consultarRemessa.sql");

        ResultActions result = mockMvc.perform(get("/api/remessas/9010"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.formaRecebimento", equalTo("BALCAO")))
                .andExpect(jsonPath("$.numero", equalTo("9010/2016")))
                .andExpect(jsonPath("$.qtdVolumes", equalTo(1)))
                .andExpect(jsonPath("$.qtdApensos", equalTo(2)));
    }

}