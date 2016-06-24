package br.jus.stf.autuacao.recebimento.infra;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.jus.stf.autuacao.recebimento.domain.DevolucaoAdapter;
import br.jus.stf.core.shared.documento.TextoId;

@Component
public class DevolucaoRestAdapter implements DevolucaoAdapter {

	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Override
	public void concluirTexto(TextoId textoId) {
		URI servicesUri = serviceUri();
		Map<String, Long> params = new HashMap<>();
		
		params.put("textoId", textoId.toLong());
		
		URI uri = UriComponentsBuilder.fromUri(servicesUri).path("/api/textos/concluir").build().toUri();
		new RestTemplate().postForObject(uri, params, Void.class);
	}

	private URI serviceUri() {
		ServiceInstance instance = discoveryClient.getInstances("documents").stream().findAny().get();
		URI servicesUri = instance.getUri();
		return servicesUri;
	}

	@Override
	public void assinarTexto(TextoId textoId, String documentoTemporarioId) {
		URI servicesUri = serviceUri();
		Map<String, Object> params = new HashMap<>();
		
		params.put("textoId", textoId.toLong());
		params.put("documentoTemporarioId", documentoTemporarioId);
		URI uri = UriComponentsBuilder.fromUri(servicesUri).path("/api/textos/assinar").build().toUri();
		new RestTemplate().postForObject(uri, params, Void.class);
	}

}
