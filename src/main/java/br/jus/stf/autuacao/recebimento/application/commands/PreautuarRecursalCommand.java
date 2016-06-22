package br.jus.stf.autuacao.recebimento.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author lucas.rodrigues
 *
 */
public class PreautuarRecursalCommand {

	@NotNull
	private Long protocoloId;
	
	@NotBlank
	private String classeId;
	
	@NotBlank
	private String sigilo;
	
    private Set<Long> preferencias;
    
    private String motivo;
	
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

	public String getMotivo() {
		return motivo;
	}
}
