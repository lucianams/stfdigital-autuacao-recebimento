package br.jus.stf.autuacao.recebimento.domain.model;

import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 21.12.2015
 */
public interface RemessaRepository {
	
	<R extends Remessa> R save(R remessa);
	
	Remessa findOne(ProtocoloId id);

}