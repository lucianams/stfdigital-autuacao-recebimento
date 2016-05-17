package br.jus.stf.autuacao.recebimento.interfaces.dto;

import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavel;

/**
 * @author anderson.araujo
 * 
 * @since 1.0.0
 * @since 21.07.2015
 */
@Component
public class ClasseDtoAssembler {
	
	@Autowired
	private PreferenciaDtoAssembler preferenciaDtoAssembler;
	
	public ClasseDto toDto(ClassePeticionavel classe) {
		Validate.notNull(classe);
		
		return new ClasseDto(classe.identity().toString(), classe.nome(), classe.preferencias().stream()
    			.map(preferencia -> preferenciaDtoAssembler.toDto(preferencia)).collect(Collectors.toSet()));
	}
}

