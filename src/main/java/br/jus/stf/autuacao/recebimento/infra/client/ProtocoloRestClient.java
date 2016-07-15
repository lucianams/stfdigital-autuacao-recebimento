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
@FeignClient(name = "services")
public interface ProtocoloRestClient {

	/**
	 * @param categoria
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/api/identificadores")
	Long identificador(@RequestParam(value = "categoria") String categoria);
	
	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/api/identificadores")
	Long identificador();
}
