package br.jus.stf.autuacao.recebimento.application.commands;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author lucas.rodrigues
 * @since 13.06.2016
 *
 */
@ApiModel("Command que realiza a ação de preautuação de uma remessa recursal.")
public class PreautuarRecursalCommand extends PreautuarCommand {

    @NotBlank
    @ApiModelProperty(value = "Número do processo na origem.", required = true)
    private String numeroProcessoOrigem;

    @NotBlank
    @ApiModelProperty(value = "Número único do processo.", required = true)
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
