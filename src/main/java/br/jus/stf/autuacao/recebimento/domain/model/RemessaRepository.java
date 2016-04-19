package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.List;
import java.util.Set;

import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 21.12.2015
 */
public interface RemessaRepository {
	
	Remessa findOne(ProtocoloId id);
	
	<R extends Remessa> R save(R remessa);
	
	Set<FormaRecebimento> findAllFormaRecebimento();
	
	MotivoDevolucao findOneMotivoDevolucao(Long id);
	
	List<MotivoDevolucao> findAllMotivoDevolucao();
	
	<M extends MotivoDevolucao> M saveMotivoDevolucao(M motivo);
	
	void deleteMotivoDevolucao(MotivoDevolucao motivo);

}