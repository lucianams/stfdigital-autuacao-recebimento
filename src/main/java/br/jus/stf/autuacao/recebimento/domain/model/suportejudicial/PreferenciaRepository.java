package br.jus.stf.autuacao.recebimento.domain.model.suportejudicial;

import br.jus.stf.core.shared.preferencia.PreferenciaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 06.04.2016
 */
@FunctionalInterface
public interface PreferenciaRepository {

    /**
     * @param id Identificador da preferência produrada.
     * @return Uma preferência.
     */
    Preferencia findOne(PreferenciaId id);

}
