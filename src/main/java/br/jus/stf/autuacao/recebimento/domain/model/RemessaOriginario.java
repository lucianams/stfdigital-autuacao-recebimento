package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.Preferencia;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 14.04.2016
 */
@Entity
@DiscriminatorValue("ORIGINARIO")
public class RemessaOriginario extends Remessa {

    public RemessaOriginario() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    /**
     * @param protocolo
     * @param volumes
     * @param apensos
     * @param formaRecebimento
     * @param numeroSedex
     * @param sigilo
     * @param recebedor
     * @param status
     */
    public RemessaOriginario(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento, String numeroSedex, Sigilo sigilo, Recebedor recebedor, Status status) {
		super(protocolo, volumes, apensos, formaRecebimento, numeroSedex, sigilo, recebedor, status);
    }
    
    @Override
    public void preautuar(ClassePeticionavel classe, Set<Preferencia> preferencias, Sigilo sigilo, Status status) {
		super.preautuar(classe, preferencias, sigilo, status);
    }
    
    @Override
    public TipoProcesso tipoProcesso() {
    	return TipoProcesso.ORIGINARIO;
    }
    
}
