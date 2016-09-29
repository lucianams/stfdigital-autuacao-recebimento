package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Objeto usado para enviar os dados necessários para devolver uma remessa.
 * 
 * @author Rafael Alencar
 * 
 * @version 1.0.0
 * @since 28.04.2016
 */
@ApiModel("Command que realiza a ação de devolução de uma remessa.")
public class DevolverRemessaCommand {

	@ApiModelProperty(value = "Protocolo da remessa.", required = true)
	@NotNull
	private Long protocoloId;
	
	@ApiModelProperty(value = "Motivo de devolução da remessa.", required = true)
	@NotBlank
	private String motivo;
	
	public DevolverRemessaCommand() {
		// Construtor default.
	}
	
	public Long getProtocoloId() {
		return protocoloId;
	}
	
	public String getMotivo() {
        return motivo;
    }
	
}

