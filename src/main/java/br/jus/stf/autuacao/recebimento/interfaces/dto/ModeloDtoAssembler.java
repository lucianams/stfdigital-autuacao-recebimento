package br.jus.stf.autuacao.recebimento.interfaces.dto;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;

/**
 * Assembler de ModeloDevolucaoDto
 * 
 * @author Tomas.Godoi
 *
 */
@Component
public class ModeloDtoAssembler {

	public ModeloDevolucaoDto toDto(ModeloDevolucao modelo) {
		Validate.notNull(modelo);

		return new ModeloDevolucaoDto(modelo.identity().toLong(), modelo.tipo().toLong(), modelo.nome(),
				modelo.template().toLong());
	}

}
