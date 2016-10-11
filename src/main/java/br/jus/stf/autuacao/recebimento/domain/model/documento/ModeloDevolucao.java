package br.jus.stf.autuacao.recebimento.domain.model.documento;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.documento.DocumentoId;
import br.jus.stf.core.shared.documento.ModeloDocumentoId;
import br.jus.stf.core.shared.documento.TipoDocumentoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 15.04.2016
 */
@Entity
@Table(name = "MODELO_DEVOLUCAO", schema = "RECEBIMENTO")
public class ModeloDevolucao extends EntitySupport<ModeloDevolucao, ModeloDocumentoId> {

    @EmbeddedId
    private ModeloDocumentoId id;

    @Column(name = "NOM_MODELO_DOCUMENTO", nullable = false)
    private String nome;

    @Embedded
    @Column(nullable = false)
    private TipoDocumentoId tipo;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "SEQ_DOCUMENTO_TEMPLATE", nullable = false))
    private DocumentoId template;

    ModeloDevolucao() {
        // Construtor default utilizado pelo Hibernate.
    }

    /**
     * @param id Identificador do modelo de devolução.
     * @param nome Nome do modelo de devolução.
     * @param tipo Tipo de documento associado ao modelo de devolução.
     * @param template Identificador do documento de template do modelo de devolução.
     */
    public ModeloDevolucao(ModeloDocumentoId id, String nome, TipoDocumentoId tipo, DocumentoId template) {
        Validate.notNull(id, "Id requerido.");
        Validate.notBlank(nome, "Nome requerido.");
        Validate.notNull(tipo, "Tipo requerido.");
        Validate.notNull(template, "Template requerido.");

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.template = template;
    }

    /**
     * @return Nome do modelo de devolução.
     */
    public String nome() {
        return nome;
    }

    /**
     * @return Tipo de documento associado ao modelo de devolução.
     */
    public TipoDocumentoId tipo() {
        return tipo;
    }

    /**
     * @return Identificador do documento de template do modelo de devolução.
     */
    public DocumentoId template() {
        return template;
    }

    @Override
    public ModeloDocumentoId identity() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d - %s", id.toLong(), nome);
    }

}
