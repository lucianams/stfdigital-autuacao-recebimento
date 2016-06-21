package br.jus.stf.autuacao.recebimento.domain;

import br.jus.stf.core.shared.documento.TextoId;

public interface DocumentoAdapter {

	void concluirTexto(TextoId textoId);
	
}
