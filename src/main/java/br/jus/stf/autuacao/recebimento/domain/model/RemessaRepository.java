package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.List;

import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 21.12.2015
 */
public interface RemessaRepository {
	
	/**
	 * @param id
	 * @return
	 */
	Remessa findOne(ProtocoloId id);
	
	/**
	 * @param remessa
	 * @return
	 */
	<R extends Remessa> R save(R remessa);
	
	/**
	 * @param id
	 * @return
	 */
	MotivoDevolucao findOneMotivoDevolucao(Long id);
	
	/**
	 * @return
	 */
	List<MotivoDevolucao> findAllMotivoDevolucao();
	
	/**
	 * @param motivo
	 * @return
	 */
	<M extends MotivoDevolucao> M saveMotivoDevolucao(M motivo);
	
	/**
	 * @param motivo
	 */
	void deleteMotivoDevolucao(MotivoDevolucao motivo);

}