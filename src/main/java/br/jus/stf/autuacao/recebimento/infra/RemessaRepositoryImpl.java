package br.jus.stf.autuacao.recebimento.infra;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.02.2016
 */
@Repository
public class RemessaRepositoryImpl extends SimpleJpaRepository<Remessa, ProtocoloId> implements RemessaRepository {

	@Autowired
    public RemessaRepositoryImpl(EntityManager entityManager) {
        super(Remessa.class, entityManager);
    }

}
