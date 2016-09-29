package br.jus.stf.autuacao.recebimento.domain.model.documento;

import java.util.List;

import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.core.shared.documento.ModeloDocumentoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 15.04.2016
 */
public interface ModeloDevolucaoRepository {

	/**
	 * @return
	 */
	List<ModeloDevolucao> findAll();
	
	/**
	 * @param id
	 * @return
	 */
	ModeloDevolucao findOne(ModeloDocumentoId id);
	
	/**
	 * @param motivo
	 * @return
	 */
	List<ModeloDevolucao> findModeloDevolucaoByMotivoDevolucao(MotivoDevolucao motivo);

}
