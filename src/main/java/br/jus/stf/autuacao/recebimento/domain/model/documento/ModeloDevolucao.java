package br.jus.stf.autuacao.recebimento.domain.model.documento;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	
	public ModeloDevolucao() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public ModeloDevolucao(ModeloDocumentoId id, String nome, TipoDocumentoId tipo, DocumentoId template) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.template = template;
	}
	
	@Override
	public ModeloDocumentoId identity() {
		return id;
	}
	
	public String nome() {
		return nome;
	}
	
	public TipoDocumentoId tipo() {
		return tipo;
	}
	
	public DocumentoId template() {
		return template;
	}
	
	@Override
	public String toString() {
		return String.format("%d - %s", id.toLong(), nome);
	}

}
