package br.jus.stf.autuacao.recebimento;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * Valida a API de registro de remessas.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
public class RemessaIntegrationTests extends IntegrationTestsSupport {
    
    @Test
    public void naoDeveRegistrarUmaRemessaInvalida() throws Exception {
        String remessaValida = "{\"formaRecebimento\":\"SEDEX\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaValida));
        
        result.andExpect(status().isBadRequest());
    }
    
    @Test
    public void naoDevePreautuarUmaRemessaInvalida() throws Exception {
        String remessa = "{\"protocoloId\":1, \"transicao\":\"devolver\", \"movito\":\"Remessa enviado ao STF antes de passar ao STJ\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessa));
        
        result.andExpect(status().isBadRequest());
    }

    
    @Test
    public void naoDeveElaborarOficioParaDevolucaoDaRemessaInvalida() throws Exception {
        String remessa = "{}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessa));
        
        result.andExpect(status().isBadRequest());
        
    }

    @Test
    public void naoDeveAssinarUmOficioDeDevolucao() throws Exception {
        String remessa = "{}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/assinatura").contentType(APPLICATION_JSON).content(remessa));
        
        result.andExpect(status().isBadRequest());
    }

}
