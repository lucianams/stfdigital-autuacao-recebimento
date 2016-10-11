package br.jus.stf.autuacao.recebimento.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Alencar
 *
 * @since 10.10.2016
 */
@Embeddable
public class TipoRecebimento extends ValueObjectSupport<TipoRecebimento> {

    @Enumerated(EnumType.STRING)
    @Column(name = "TIP_FORMA_RECEBIMENTO", nullable = false)
    private FormaRecebimento formaRecebimento;

    @Column(name = "NUM_SEDEX")
    private String numeroSedex;

    TipoRecebimento() {
        // Construtor default utilizado pelo Hibernate.
    }

    /**
     * @param formaRecebimento Forma de recebimento.
     * @param numeroSedex Número do sedex.
     */
    public TipoRecebimento(FormaRecebimento formaRecebimento, String numeroSedex) {
        Validate.notNull(formaRecebimento, "Forma de recebimento requerida.");
        Validate.isTrue(isNumeroSedexInformadoQuandoExigido(formaRecebimento, numeroSedex),
                "Forma de recebimento exige número de sedex.");

        this.formaRecebimento = formaRecebimento;
        this.numeroSedex = numeroSedex;
    }

    private static boolean isNumeroSedexInformadoQuandoExigido(FormaRecebimento formaRecebimento, String numeroSedex) {
        return !formaRecebimento.exigeNumeracao() || !StringUtils.isEmpty(numeroSedex);
    }

    /**
     * @return Forma de recebimento.
     */
    public FormaRecebimento formaRecebimento() {
        return formaRecebimento;
    }

    /**
     * @return Número de sedex.
     */
    public String numeroSedex() {
        return numeroSedex;
    }
}