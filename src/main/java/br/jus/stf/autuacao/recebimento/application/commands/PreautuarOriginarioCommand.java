package br.jus.stf.autuacao.recebimento.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Objeto usado para enviar os dados necessários para préautuar uma remessa física.
 * 
 * @author Anderson Araújo
 * @author Rodrigo Barreiros
 * 
 * @version 1.0.0
 * @since 15.09.2015
 */
public class PreautuarOriginarioCommand {

	@NotNull
	private Long protocoloId;
	
	@NotBlank
	private String classeId;
	
	@NotBlank
	private String sigilo;
	
    private Set<Long> preferencias;
    
    public PreautuarOriginarioCommand() {
    	
    }
	
	public PreautuarOriginarioCommand(Long protocoloId, String classeId, String sigilo, Set<Long> preferencias) {
		this.protocoloId = protocoloId;
		this.classeId = classeId;
		this.sigilo = sigilo;
		this.preferencias = preferencias;
	}
    
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
