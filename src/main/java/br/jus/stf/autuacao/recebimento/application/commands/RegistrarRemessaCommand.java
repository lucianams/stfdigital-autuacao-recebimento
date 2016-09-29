package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Representa o comando de registro de remessas.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
@ApiModel("Command que realiza a ação de registro de uma remessa.")
public class RegistrarRemessaCommand {
    
	@ApiModelProperty(value = "Forma de recebimento da remessa..", required = true)
	@NotBlank
    private String formaRecebimento;
    
	@ApiModelProperty(value = "Quantidade de volumes.", required = true)
    @Min(1L)
    private int volumes;
    
	@ApiModelProperty(value = "Quantidade de apensos.", required = true)
    @Min(0L)
    private int apensos;
    
	@ApiModelProperty(value = "Número do sedex. Usado para forma de recebimento SEDEX.")
    private String numeroSedex;
    
	@ApiModelProperty(value = "Tipo do processo da remessa.", required = true)
    @NotBlank
    private String tipoProcesso;
    
	@ApiModelProperty(value = "Grau de sigilo da remessa.", required = true)
    @NotBlank
    private String sigilo;
    
    public RegistrarRemessaCommand() {
    	// Construtor default
    }
    
    public String getFormaRecebimento() {
        return formaRecebimento;
    }

    public int getVolumes() {
        return volumes;
    }

    public int getApensos() {
        return apensos;
    }

    public String getNumeroSedex() {
        return numeroSedex;
    }
    
    public String getTipoProcesso() {
        return tipoProcesso;
    }

	public String getSigilo() {
		return sigilo;
	}
    
}
