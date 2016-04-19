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

	List<ModeloDevolucao> findAll();
	
	ModeloDevolucao findOne(ModeloDocumentoId id);
	
	List<ModeloDevolucao> findModeloDevolucaoByMotivoDevolucao(MotivoDevolucao motivo);

}
