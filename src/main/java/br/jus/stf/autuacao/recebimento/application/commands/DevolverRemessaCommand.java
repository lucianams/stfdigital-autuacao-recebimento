package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Objeto usado para enviar os dados necessários para devolver uma remessa.
 * 
 * @author Rafael Alencar
 * 
 * @version 1.0.0
 * @since 28.04.2016
 */
public class DevolverRemessaCommand {

	@NotNull
	private Long protocoloId;
	
	@NotBlank
	private String motivo;
	
	public Long getProtocoloId() {
		return protocoloId;
	}
	
	public String getMotivo() {
        return motivo;
    }
	
}

