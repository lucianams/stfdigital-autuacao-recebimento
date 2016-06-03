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
//@Ignore
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
public class RemessaOriginarioIntegrationTests extends IntegrationTestsSupport {
	
	@Test
    public void registrarUmaRemessa() throws Exception {
		String remessaValida = "{\"formaRecebimento\":\"SEDEX\", \"volumes\":1, \"tipoProcesso\":\"ORIGINARIO\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\", \"sigilo\":\"PUBLICO\"}";
		ResultActions result = mockMvc.perform(post("/api/remessas/recebimento").contentType(APPLICATION_JSON).content(remessaValida));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void preautarUmaRemessa() throws Exception {
        loadDataTests("preautarRemessaOriginario.sql");
        
        String remessaParaPreautuar = "{\"protocoloId\":@protocoloId, \"classeId\":\"ADI\", \"preferencias\":[3,8], \"sigilo\":\"PUBLICO\"}";
        String protocoloId = "9000";
        ResultActions result = mockMvc.perform(post("/api/remessas/preautuacao").contentType(APPLICATION_JSON).content(remessaParaPreautuar.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void devolverUmaRemessa() throws Exception {
		loadDataTests("devolverRemessaOriginario.sql");
		
		String remessaParaDevolver = "{\"protocoloId\":@protocoloId, \"motivo\":\"Remessa inválida.\"}";
        String protocoloId = "9001";

        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao").contentType(APPLICATION_JSON).content(remessaParaDevolver.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void prepararOficioDevolucaoDaRemessa() throws Exception {
        loadDataTests("prepararOficioDevolucaoRemessaOriginario.sql");
		
		String remessaParaPrepararOficio = "{\"protocoloId\":@protocoloId, \"motivo\":1, \"modeloId\":1, \"textoId\":1}";
		String protocoloId = "9002";
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-oficio").contentType(APPLICATION_JSON).content(remessaParaPrepararOficio.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void assinarOficioDevolucaoDaRemessa() throws Exception {
        loadDataTests("assinarOficioDevolucaoRemessaOriginario.sql");
		
        String remessaParaAssinarOficio = "{\"protocoloId\":@protocoloId}";
		String protocoloId = "9003";
        ResultActions result = mockMvc.perform(post("/api/remessas/devolucao-assinatura").contentType(APPLICATION_JSON).content(remessaParaAssinarOficio.replace("@protocoloId", protocoloId)));
        
        result.andExpect(status().isOk());
    }
	
	@Test
    public void naoDeveRegistrarUmaRemessaInvalida() throws Exception {
        String remessaInvalida = "{\"formaRecebimento\":\"SEDEX\", \"apensos\":1, \"numeroSedex\":\"SR123456789BR\"}";
        
        ResultActions result = mockMvc.perform(post("/api/remessas/recebimento").contentType(APPLICATION_JSON).content(remessaInvalida));
        
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
