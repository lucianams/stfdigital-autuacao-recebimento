package br.jus.stf.autuacao.recebimento.interfaces.dto;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.Preferencia;

/**
 * @author anderson.araujo
 * 
 * @since 1.0.0
 * @since 21.07.2015
 */
@Component
public class PreferenciaDtoAssembler {

    /**
     * @param prefenrecia Preferência.
     * @return Um DTO da preferência.
     */
    public PreferenciaDto toDto(Preferencia prefenrecia) {
        Validate.notNull(prefenrecia);
        return new PreferenciaDto(prefenrecia.identity().toLong(), prefenrecia.nome());
    }
}
