package br.jus.stf.autuacao.recebimento.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.documento.TipoDocumentoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 15.04.2016
 */
@Entity
@Table(name = "MOTIVO_DEVOLUCAO", schema = "RECEBIMENTO", uniqueConstraints = @UniqueConstraint(columnNames = {"DSC_MOTIVO_DEVOLUCAO"}))
public class MotivoDevolucao extends EntitySupport<MotivoDevolucao, Long> {
	
	@Id
	@Column(name = "SEQ_MOTIVO_DEVOLUCAO")
	@SequenceGenerator(name = "MOTIVODEVOLUCAOID", sequenceName = "RECEBIMENTO.SEQ_MOTIVO_DEVOLUCAO", allocationSize = 1)
	@GeneratedValue(generator = "MOTIVODEVOLUCAOID", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "DSC_MOTIVO_DEVOLUCAO", nullable = false)
	private String descricao;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "MOTIVO_TIPO_DOCUMENTO", schema = "RECEBIMENTO",
		joinColumns = @JoinColumn(name = "SEQ_MOTIVO_DEVOLUCAO", nullable = false))
	private Set<TipoDocumentoId> tiposDocumento = new HashSet<TipoDocumentoId>(0);
	
	public MotivoDevolucao() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public MotivoDevolucao(String descricao) {
		Validate.notBlank(descricao, "Descrição requerida.");
		
		this.descricao = descricao;
	}
	
	public String descricao() {
		return descricao;
	}
	
	public Set<TipoDocumentoId> tiposDocumento() {
		return Collections.unmodifiableSet(tiposDocumento);
	}
	
	public void atribuirTiposDocumento(Set<TipoDocumentoId> tiposDocumento) {
		Validate.notEmpty(tiposDocumento, "Tipos de documento requeridos.");
		
		this.tiposDocumento.addAll(tiposDocumento);
	}
	
	public boolean removerTiposDocumento(Set<TipoDocumentoId> tiposDocumento) {
		Validate.notEmpty(tiposDocumento, "Tipos de documento requeridos.");
		
		return this.tiposDocumento.removeAll(tiposDocumento);
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public Long identity() {
		return id;
	}

}
