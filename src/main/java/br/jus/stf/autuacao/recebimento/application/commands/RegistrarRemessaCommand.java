package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@ApiModel("Command que realiza a ação de registro de uma remessa.")
public class RegistrarRemessaCommand {

    @NotBlank
    @ApiModelProperty(value = "Forma de recebimento da remessa..", required = true)
    private String formaRecebimento;

    @ApiModelProperty(value = "Número do sedex. Usado para forma de recebimento SEDEX.")
    private String numeroSedex;

    @Min(1L)
    @ApiModelProperty(value = "Quantidade de volumes.", required = true)
    private int volumes;

    @Min(0L)
    @ApiModelProperty(value = "Quantidade de apensos.", required = true)
    private int apensos;

    @NotBlank
    @ApiModelProperty(value = "Tipo do processo da remessa.", required = true)
    private String tipoProcesso;

    @NotBlank
    @ApiModelProperty(value = "Grau de sigilo da remessa.", required = true)
    private String sigilo;

    public RegistrarRemessaCommand() {
        // Construtor default
    }

    public String getFormaRecebimento() {
        return formaRecebimento;
    }

    public String getNumeroSedex() {
        return numeroSedex;
    }

    public int getVolumes() {
        return volumes;
    }

    public int getApensos() {
        return apensos;
    }

    public String getTipoProcesso() {
        return tipoProcesso;
    }

    public String getSigilo() {
        return sigilo;
    }

}
