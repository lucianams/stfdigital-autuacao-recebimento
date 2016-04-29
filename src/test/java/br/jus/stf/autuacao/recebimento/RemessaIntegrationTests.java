package br.jus.stf.autuacao.recebimento;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * Valida a API de registro de remessas.
 * 
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@Ignore
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
public class RemessaIntegrationTests extends IntegrationTestsSupport {
	
	private final String remessaValida = "{\"formaRecebimento\":\"SEDEX\", \"volumes\":1, \"tipoProcesso\":\"ORIGINARIO\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\"}";
	private final String remessaParaPreautuar = "{\"protocoloId\":@protocoloId, \"classeId\":\"ADI\", \"preferencias\":[3,8]}";
	private final String remessaParaDevolver = "{\"protocoloId\":@protocoloId, \"motivo\":\"Remessa inválida.\"}";
	private final String remessaParaPrepararOficio = "{\"protocoloId\":@protocoloId, \"motivo\":1, \"modeloId\":1, \"textoId\":1}";
	private final String remessaParaAssinarOficio = "{\"protocoloId\":@protocoloId}";
	
	@Test
    public void registrarUmaRemessa() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaValida));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void preautarUmaRemessa() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaValida));
        String protocoloId = result.andReturn().getResponse().getContentAsString();
        
        result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaParaPreautuar.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void devolverUmaRemessa() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaValida));
        String protocoloId = result.andReturn().getResponse().getContentAsString();

        result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessaParaDevolver.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void prepararOficioDevolucaoDaRemessa() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaValida));
        String protocoloId = result.andReturn().getResponse().getContentAsString();
        
        result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessaParaDevolver.replace("@protocoloId", protocoloId)));
        
        result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaParaPrepararOficio.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void assinarOficioDevolucaoDaRemessa() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaValida));
        String protocoloId = result.andReturn().getResponse().getContentAsString();
        
        result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessaParaDevolver.replace("@protocoloId", protocoloId)));
        
        result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaParaPrepararOficio.replace("@protocoloId", protocoloId)));
        
        result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaParaAssinarOficio.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void naoDeveRegistrarUmaRemessaInvalida() throws Exception {
        String remessaInvalida = "{\"formaRecebimento\":\"SEDEX\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }
	
	@Test
    public void naoDevePreautuarUmaRemessaInvalida() throws Exception {
        String remessaInvalida = "{\"protocoloId\":1, \"transicao\":\"devolver\", \"movito\":\"Remessa enviado ao STF antes de passar ao STJ\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }
	
	@Test
    public void naoDeveElaborarOficioParaDevolucaoDaRemessaInvalida() throws Exception {
        String remessaInvalida = "{}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
        
    }
	
	@Test
    public void naoDeveAssinarUmOficioDeDevolucao() throws Exception {
        String remessaInvalida = "{}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaInvalida));
        
        result.andExpect(status().isBadRequest());
    }

}
