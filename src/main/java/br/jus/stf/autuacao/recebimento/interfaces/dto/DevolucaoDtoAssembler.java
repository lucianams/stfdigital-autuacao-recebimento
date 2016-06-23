package br.jus.stf.autuacao.recebimento.interfaces.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Remessa;

@Component
public class DevolucaoDtoAssembler {

	@Autowired
	private ModeloDtoAssembler modeloDtoAssembler;
	
	public DevolucaoDto toDto(Remessa remessa) {
		return new DevolucaoDto(remessa.numero().numero(),
			remessa.numero().ano(),
			modeloDtoAssembler.toDto(remessa.devolucao().modelo()),
			remessa.devolucao().texto().toLong());
	}
	
}
