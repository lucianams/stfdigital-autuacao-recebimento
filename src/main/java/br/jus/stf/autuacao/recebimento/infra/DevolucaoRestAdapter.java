package br.jus.stf.autuacao.recebimento.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.DevolucaoAdapter;
import br.jus.stf.autuacao.recebimento.infra.client.DocumentoRestClient;
import br.jus.stf.core.shared.documento.TextoId;

/**
 * @author Tomas Godoi
 * 
 * @since 1.0.0
 */
@Component
public class DevolucaoRestAdapter implements DevolucaoAdapter {

	@Autowired
    private DocumentoRestClient documentoRestClient;
	
	@Override
	public void concluirTexto(TextoId textoId) {
		documentoRestClient.concluirTexto(textoId);
	}

	@Override
	public void assinarTexto(TextoId textoId, String documentoTemporarioId) {
		Map<String, Object> command = new HashMap<>();
		
		command.put("textoId", textoId.toLong());
		command.put("documentoTemporarioId", documentoTemporarioId);
		
		documentoRestClient.assinarTexto(command);
	}

}
