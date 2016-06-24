package br.jus.stf.autuacao.recebimento.domain;

import br.jus.stf.core.shared.documento.TextoId;

public interface DevolucaoAdapter {

	void concluirTexto(TextoId textoId);

	void assinarTexto(TextoId texto, String documentoTemporarioId);
	
}
