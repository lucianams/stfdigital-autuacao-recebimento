package br.jus.stf.autuacao.recebimento.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucaoRepository;
import br.jus.stf.core.shared.documento.ModeloDocumentoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 15.04.2016
 */
@Repository
public class ModeloDevolucaoRepositoryImpl extends SimpleJpaRepository<ModeloDevolucao, ModeloDocumentoId> implements ModeloDevolucaoRepository {

	private EntityManager entityManager;
	
	@Autowired
    public ModeloDevolucaoRepositoryImpl(EntityManager entityManager) {
        super(ModeloDevolucao.class, entityManager);
        this.entityManager = entityManager;
    }
	
	@Override
	public List<ModeloDevolucao> findModeloDevolucaoByMotivoDevolucao(MotivoDevolucao motivo) {
		TypedQuery<ModeloDevolucao> query = entityManager.createQuery(
				"SELECT modelo FROM ModeloDevolucao modelo WHERE modelo.tipo IN :tipos ORDER BY modelo.nome",
				ModeloDevolucao.class);

		query.setParameter("tipos", motivo.tiposDocumento());
		return query.getResultList();
	}
    
}
