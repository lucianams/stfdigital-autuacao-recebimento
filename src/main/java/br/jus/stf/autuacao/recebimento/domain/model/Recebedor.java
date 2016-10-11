package br.jus.stf.autuacao.recebimento.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;
import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 02.05.2016
 */
@Embeddable
public class Recebedor extends ValueObjectSupport<Recebedor> {

    @Column(name = "SIG_RECEBEDOR")
    private String login;

    @Transient
    private PessoaId pessoa;

    Recebedor() {
        // Construtor default utilizado pelo Hibernate.
    }

    /**
     * @param login Login do usuário recebedor.
     * @param pessoa Pessoa associada ao recebedor.
     */
    public Recebedor(String login, PessoaId pessoa) {
        Validate.notBlank(login, "Login requerido.");
        Validate.notNull(pessoa, "Pessoa requerida.");

        this.login = login;
        this.pessoa = pessoa;
    }

    /**
     * @return Login do usuário recebedor.
     */
    public String login() {
        return login;
    }

    /**
     * @return Pessoa associada ao recebedor.
     */
    public PessoaId pessoa() {
        return pessoa;
    }

    @Override
    public String toString() {
        return String.format("%s", login);
    }

}
