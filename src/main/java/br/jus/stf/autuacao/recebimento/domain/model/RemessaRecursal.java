package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.apache.commons.lang3.Validate;

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
@DiscriminatorValue("RECURSAL")
public class RemessaRecursal extends Remessa {

    @Column(name = "NUM_PROCESSO_ORIGEM")
    private String numeroProcessoOrigem;

    @Column(name = "NUM_UNICO_PROCESSO")
    private String numeroUnicoProcesso;

    RemessaRecursal() {
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
    public RemessaRecursal(Protocolo protocolo, Integer volumes, Integer apensos, TipoRecebimento tipoRecebimento,
            Sigilo sigilo, Recebedor recebedor, Status status) {
        super(protocolo, volumes, apensos, tipoRecebimento, sigilo, recebedor, status);
    }

    /**
     * @param classe Classe da remessa.
     * @param preferencias Subconjunto de preferências da classe que estão associadas com a remessa.
     * @param sigilo Revisão do grau de sigilo da remessa.
     * @param numeroProcessoOrigem Número do processo na origem.
     * @param numeroUnicoProcesso Número único do processo (NUP).
     * @param status Status do BPM ligado com a fase de preautuação.
     */
    public void preautuar(ClassePeticionavel classe, Set<Preferencia> preferencias, Sigilo sigilo,
            String numeroProcessoOrigem, String numeroUnicoProcesso, Status status) {
        super.preautuar(classe, preferencias, sigilo, status);

        Validate.notBlank(numeroProcessoOrigem, "Número na origem requerido.");
        Validate.notBlank(numeroUnicoProcesso, "Número único requerido.");

        this.numeroProcessoOrigem = numeroProcessoOrigem;
        this.numeroUnicoProcesso = numeroUnicoProcesso;
    }

    @Override
    public TipoProcesso tipoProcesso() {
        return TipoProcesso.RECURSAL;
    }

}
