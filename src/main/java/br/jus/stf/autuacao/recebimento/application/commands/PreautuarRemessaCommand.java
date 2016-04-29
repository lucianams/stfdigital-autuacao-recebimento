package br.jus.stf.autuacao.recebimento.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.jus.stf.core.shared.preferencia.PreferenciaId;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Objeto usado para enviar os dados necessários para préautuar uma remessa física.
 * 
 * @author Anderson Araújo
 * @author Rodrigo Barreiros
 * 
 * @version 1.0.0
 * @since 15.09.2015
 */
public class PreautuarRemessaCommand {

	@NotNull
	private ProtocoloId protocoloId;
	
	@NotBlank
	private String classeId;
	
    private Set<PreferenciaId> preferencias;
	
	public ProtocoloId getProtocoloId() {
		return protocoloId;
	}
	
	public String getClasseId() {
		return classeId;
	}
	
	public Set<PreferenciaId> getPreferencias() {
		return preferencias;
	}
	
}

