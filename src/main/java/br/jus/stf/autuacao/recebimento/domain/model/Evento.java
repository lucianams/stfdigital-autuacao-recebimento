package br.jus.stf.autuacao.recebimento.domain.model;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 24.08.2016
 */
@Entity
@Table(name = "EVENTO", schema = "RECEBIMENTO")
public class Evento extends EntitySupport<Evento, Long> {

	@Id
	@Column(name = "SEQ_EVENTO")
	@SequenceGenerator(name = "EVENTO_ID", sequenceName = "RECEBIMENTO.SEQ_EVENTO", allocationSize = 1)
	@GeneratedValue(generator = "EVENTO_ID", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "NOM_EVENTO")
	private String tipo;
	
	@Column(name = "DAT_CRIACAO")
	private Date criacao;
	
	@Column(name = "BIN_DETALHE")
	@Lob
	private String detalhes;
	
	@Column(name = "TIP_STATUS")
	private Integer status = 1;
	
	Evento() {
    	// Usado apenas pelo Jackson durante a conversação de Json para uma nova instância.
	}
	
	/**
	 * @param detalhes
	 */
	public Evento(Object detalhes) {
		try {
			this.detalhes = new ObjectMapper().writeValueAsString(detalhes);
		}
		catch (IOException e) {
			throw new IllegalArgumentException(String.format("Não foi possível converter o objeto: %s", detalhes), e);
		}
		this.tipo = detalhes.getClass().getName();
		criacao = new Date();
	}

	/**
	 * @return
	 */
	public Date criacao() {
		return criacao;
	}
	
	/**
	 * @return
	 */
	public String tipo() {
		return tipo;
	}
	
	/**
	 * @return
	 */
	public String detalhes() {
		return detalhes;
	}
	
	@Override
	public Long identity() {
		return id;
	}
	
}
