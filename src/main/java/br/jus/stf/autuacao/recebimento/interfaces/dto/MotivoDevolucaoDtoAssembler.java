package br.jus.stf.autuacao.recebimento.interfaces.dto;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;

/**
 * Cria objetos MotivoDevolucaoDto.
 * 
 * @author Anderson.Araujo
 * @since 15.03.2016
 *
 */
@Component
public class MotivoDevolucaoDtoAssembler {
	public MotivoDevolucaoDto toDto(MotivoDevolucao motivo) {
		Validate.notNull(motivo);
		return new MotivoDevolucaoDto(motivo.identity(), motivo.descricao());
	}
}
