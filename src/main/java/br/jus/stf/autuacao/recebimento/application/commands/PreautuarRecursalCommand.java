package br.jus.stf.autuacao.recebimento.application.commands;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author lucas.rodrigues
 *
 */
@ApiModel("Command que realiza a ação de preautuação de uma remessa recursal.")
public class PreautuarRecursalCommand extends PreautuarCommand {

	@ApiModelProperty(value = "Número do processo na origem.", required = true)
    @NotBlank
	private String numeroProcessoOrigem;
	
	@ApiModelProperty(value = "Número único do processo.", required = true)
	@NotBlank
	private String numeroUnicoProcesso;
	
	public PreautuarRecursalCommand() {
		// Construtor default.
	}

	public String getNumeroProcessoOrigem() {
		return numeroProcessoOrigem;
	}
	
	public String getNumeroUnicoProcesso() {
		return numeroUnicoProcesso;
	}
	
}
