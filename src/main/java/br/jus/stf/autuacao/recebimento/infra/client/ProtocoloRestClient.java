package br.jus.stf.autuacao.recebimento.infra.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 05.07.2016
 */
@FeignClient(name = "processos")
public interface ProtocoloRestClient {

    /**
     * @param categoria Representa o ano de criação da remessa.
     * @return Próximo número de remessa para o ano informado.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/identificadores")
    Long identificador(@RequestParam(value = "categoria") String categoria);

    /**
     * @return Número que será utilizado para geração do ProtocoloId.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/identificadores")
    Long identificador();
}
