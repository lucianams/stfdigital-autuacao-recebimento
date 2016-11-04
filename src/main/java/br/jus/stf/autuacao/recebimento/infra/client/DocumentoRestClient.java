package br.jus.stf.autuacao.recebimento.infra.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 05.07.2016
 */
@FeignClient(name = "documents")
public interface DocumentoRestClient {

    /**
     * @param command Mapa que representa command para concluir texto.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/api/textos/{textoId}/conclusao")
    void concluirTexto(@PathVariable("textoId") Long textoId, Map<String, Object> command);

    /**
     * @param command Mapa que representa command para assinar texto.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/api/textos/{textoId}/assinatura")
    void assinarTexto(@PathVariable("textoId") Long textoId, Map<String, Object> command);

}
