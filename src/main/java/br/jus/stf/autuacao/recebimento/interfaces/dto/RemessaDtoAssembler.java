package br.jus.stf.autuacao.recebimento.interfaces.dto;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.infra.RemessaDto;

@Component
public class RemessaDtoAssembler {
	
		public RemessaDto toDto(Remessa remessa) {
			Validate.notNull(remessa);
			return new RemessaDto(remessa.classe().toString(), remessa.volumes(), remessa.apensos(), remessa.formaRecebimento().toString(), remessa.numeroSedex());
		} 
	

}
