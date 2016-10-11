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

    RemessaOriginario() {
        // Construtor default que deve ser utilizado apenas pelo Hibernate.
    }

    /**
     * @param protocolo Protocolo com identificação da remessa.
     * @param volumes Quantidade de volumes da remessa.
     * @param apensos Quantidade de apensos da remessa.
     * @param tipoRecebimento Dados do tipo de recebimento da remessa.
     * @param sigilo Grau de sigilo da remessa.
     * @param recebedor Usuário que cadastrou a remessa.
     * @param status Status inicial do BPM para remessa.
     */
    public RemessaOriginario(Protocolo protocolo, Integer volumes, Integer apensos, TipoRecebimento tipoRecebimento,
            Sigilo sigilo, Recebedor recebedor, Status status) {
        super(protocolo, volumes, apensos, tipoRecebimento, sigilo, recebedor, status);
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
