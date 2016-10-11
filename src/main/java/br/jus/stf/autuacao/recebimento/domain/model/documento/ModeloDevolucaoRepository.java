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
     * @return Todos os modelos de devolução.
     */
    List<ModeloDevolucao> findAll();

    /**
     * @param id Identificador do modelo de devolução procurado.
     * @return Um modelo de devolução.
     */
    ModeloDevolucao findOne(ModeloDocumentoId id);

    /**
     * @param motivo Motivo de devolução associado aos modelos procurados.
     * @return Todos os modelos de devolução do motivo.
     */
    List<ModeloDevolucao> findModeloDevolucaoByMotivoDevolucao(MotivoDevolucao motivo);

}
