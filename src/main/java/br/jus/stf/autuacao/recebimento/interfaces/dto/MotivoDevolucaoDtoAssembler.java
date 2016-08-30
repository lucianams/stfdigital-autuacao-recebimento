package br.jus.stf.autuacao.recebimento.interfaces.dto;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	/**
	 * @param motivo
	 * @return
	 */
	public MotivoDevolucaoDto toDto(MotivoDevolucao motivo) {
		Validate.notNull(motivo);
		Set<Long> tiposDocumento = motivo.tiposDocumento().stream().map(t -> t.toLong()).collect(Collectors.toSet());
		return new MotivoDevolucaoDto(motivo.identity(), motivo.descricao(), tiposDocumento);
	}
}
