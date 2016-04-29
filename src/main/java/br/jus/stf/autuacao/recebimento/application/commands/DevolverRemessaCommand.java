package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.jus.stf.core.shared.protocolo.ProtocoloId;

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
	private ProtocoloId protocoloId;
	
	@NotBlank
	private String motivo;
	
	public ProtocoloId getProtocoloId() {
		return protocoloId;
	}
	
	public String getMotivo() {
        return motivo;
    }
	
}

