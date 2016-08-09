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
    
    @NotBlank
	private String numeroProcessoOrigem;
	
	@NotBlank
	private String numeroUnicoProcesso;
	
	public PreautuarRecursalCommand() {
		// Construtor default
	}
	
    /**
     * @param protocoloId
     * @param classeId
     * @param sigilo
     * @param preferencias
     * @param numeroProcessoOrigem
     * @param numeroUnicoProcesso
     */
    public PreautuarRecursalCommand(Long protocoloId, String classeId, String sigilo, Set<Long> preferencias, String numeroProcessoOrigem, String numeroUnicoProcesso) {
		this.protocoloId = protocoloId;
		this.classeId = classeId;
		this.sigilo = sigilo;
		this.preferencias = preferencias;
		this.numeroProcessoOrigem = numeroProcessoOrigem;
		this.numeroUnicoProcesso = numeroUnicoProcesso;
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

	public String getNumeroProcessoOrigem() {
		return numeroProcessoOrigem;
	}
	
	public String getNumeroUnicoProcesso() {
		return numeroUnicoProcesso;
	}
	
}
