package br.jus.stf.autuacao.recebimento.domain.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
public class RemessaOriginaria extends Remessa {

    public RemessaOriginaria() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public RemessaOriginaria(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento, String numeroSedex, String recebedor, Status status) {
		super(protocolo, volumes, apensos, formaRecebimento, numeroSedex, recebedor, status);
    }
    
    public TipoProcesso tipoProcesso() {
    	return TipoProcesso.ORIGINARIO;
    }
    
}
