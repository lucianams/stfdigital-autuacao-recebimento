package br.jus.stf.autuacao.recebimento.interfaces.dto;

import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Remessa;

/**
 * @author viniciusk
 * 
 * @since 11.05.2016
 */
@Component
public class RemessaDtoAssembler {

    /**
     * @param remessa Remessa.
     * @return Um DTO da remessa.
     */
    public RemessaDto toDto(Remessa remessa) {
        Validate.notNull(remessa);

        String classe = Optional.ofNullable(remessa.classe())
                .map(classePeticionavel -> classePeticionavel.identity().toString()).orElse("");

        return new RemessaDto(remessa.identity().toLong(), classe, remessa.volumes(), remessa.apensos(),
                remessa.tipoRecebimento().formaRecebimento().toString(), remessa.tipoRecebimento().numeroSedex(),
                remessa.sigilo().name(), remessa.numero().numero() + "/" + remessa.numero().ano().toString());
    }
}
