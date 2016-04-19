package br.jus.stf.autuacao.recebimento.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 13.04.2016
 */
@Entity
@Table(name = "DEVOLUCAO", schema = "RECEBIMENTO")
public class Devolucao extends ValueObjectSupport<Devolucao> {
	
	@EmbeddedId
	private ProtocoloId remessa;
	
	@Column(name = "DSC_MOTIVACAO", nullable = false)
	private String motivacao;
	
	@ManyToOne
    @JoinColumn(name = "SEQ_MOTIVO_DEVOLUCAO")
	private MotivoDevolucao motivo;
	
	@ManyToOne
    @JoinColumn(name = "SEQ_MODELO_DOCUMENTO")
	private ModeloDevolucao modelo;
	
	@Embedded
	private TextoId texto;
	
	public Devolucao() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Devolucao(String motivacao) {
		Validate.notBlank(motivacao, "Motivação requerida.");
		
		this.motivacao = motivacao;
	}
	
	public Devolucao(String motivacao, MotivoDevolucao motivo, ModeloDevolucao modelo, TextoId texto) {
		this(motivacao);
		
		Validate.notNull(motivo, "Motivo requerido.");
		Validate.notNull(modelo, "Modelo requerido.");
		Validate.notNull(texto, "Texto requerido.");
		
		this.motivo = motivo;
		this.modelo = modelo;
		this.texto = texto;
	}
	
	public ProtocoloId remessa() {
		return remessa;
	}
	
	public String motivacao() {
		return motivacao;
	}
	
	public ModeloDevolucao modelo() {
		return modelo;
	}
	
	public MotivoDevolucao motivo() {
		return motivo;
	}
	
	public TextoId texto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", motivo.descricao(), motivacao);
	}

}
