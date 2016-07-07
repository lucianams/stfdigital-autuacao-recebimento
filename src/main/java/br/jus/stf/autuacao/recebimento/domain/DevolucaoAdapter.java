package br.jus.stf.autuacao.recebimento.domain;

import br.jus.stf.core.shared.documento.TextoId;

/**
 * @author Tomas Godoi
 * 
 * @since 1.0.0
 */
public interface DevolucaoAdapter {

	/**
	 * @param textoId
	 */
	void concluirTexto(TextoId textoId);

	/**
	 * @param texto
	 * @param documentoTemporarioId
	 */
	void assinarTexto(TextoId texto, String documentoTemporarioId);
	
}
