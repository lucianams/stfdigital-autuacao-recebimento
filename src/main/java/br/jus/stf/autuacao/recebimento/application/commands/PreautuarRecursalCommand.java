package br.jus.stf.autuacao.recebimento.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author lucas.rodrigues
 *
 */
@ApiModel("Command que realiza a ação de preautuação de uma remessa recursal.")
public class PreautuarRecursalCommand {

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
    
	@ApiModelProperty(value = "Número do processo na origem.", required = true)
    @NotBlank
	private String numeroProcessoOrigem;
	
	@ApiModelProperty(value = "Número único do processo.", required = true)
	@NotBlank
	private String numeroUnicoProcesso;
	
	public PreautuarRecursalCommand() {
		// Construtor default.
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
