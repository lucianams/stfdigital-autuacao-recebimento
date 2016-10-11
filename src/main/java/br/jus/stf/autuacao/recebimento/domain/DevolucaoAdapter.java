package br.jus.stf.autuacao.recebimento.domain;

import br.jus.stf.core.shared.documento.TextoId;

/**
 * @author Tomas Godoi
 * 
 * @since 1.0.0
 */
public interface DevolucaoAdapter {

    /**
     * @param textoId Identificador do texto que será concluído.
     */
    void concluirTexto(TextoId textoId);

    /**
     * @param texto Identificador do texto que será assinado.
     * @param documentoTemporarioId Documento temporário assinado que será associado ao texto.
     */
    void assinarTexto(TextoId texto, String documentoTemporarioId);

}
