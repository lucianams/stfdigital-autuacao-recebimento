package br.jus.stf.autuacao.recebimento.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Objeto usado para enviar os dados necessários para préautuar uma remessa.
 * 
 * @author Rafael Alencar
 * 
 * @version 1.0.0
 * @since 29.09.2015
 */
@ApiModel("Command que realiza a ação de preautuação de uma remessa.")
public abstract class PreautuarCommand {

	@ApiModelProperty(value = "Protocolo da remessa.", required = true)
	@NotNull
	private Long protocoloId;
	
	@ApiModelProperty(value = "Classe da remessa.", required = true)
	@NotBlank
	private String classeId;

	@ApiModelProperty(value = "Grau de sigilo da remessa.", required = true)
	@NotBlank
	private String sigilo;
	
	@ApiModelProperty(value = "Lista de preferências da remessa.")
    private Set<Long> preferencias;
    
    public Long getProtocoloId() {
		return protocoloId;
	}
	
	public String getClasseId() {
		return classeId;
	}
	
	public Set<Long> getPreferencias() {
		return preferencias;
	}
	
	public String getSigilo() {
		return sigilo;
	}
	
}

