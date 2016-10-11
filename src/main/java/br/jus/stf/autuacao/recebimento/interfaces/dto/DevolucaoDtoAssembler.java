package br.jus.stf.autuacao.recebimento.interfaces.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Remessa;

/**
 * @author Tomas Godoi
 *
 * @since 22.06.2016
 */
@Component
public class DevolucaoDtoAssembler {

    @Autowired
    private ModeloDtoAssembler modeloDtoAssembler;

    /**
     * @param remessa Remessa.
     * @return Um DTO da remessa.
     */
    public DevolucaoDto toDto(Remessa remessa) {
        return new DevolucaoDto(remessa.identity().toLong(),
                remessa.numero().numero(),
                remessa.numero().ano(),
                modeloDtoAssembler.toDto(remessa.devolucao().modelo()),
                remessa.devolucao().texto().toLong());
    }

}
