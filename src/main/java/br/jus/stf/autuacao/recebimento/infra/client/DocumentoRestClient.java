package br.jus.stf.autuacao.recebimento.infra.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.stf.core.shared.documento.TextoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 05.07.2016
 */
@FeignClient(name = "documents")
public interface DocumentoRestClient {

	/**
	 * @param textoId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/textos/concluir")
	List<Map<String, Object>> concluirTexto(TextoId textoId);
	
	/**
	 * @param command
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/api/textos/assinar")
	void assinarTexto(Map<String, Object> command);

}
