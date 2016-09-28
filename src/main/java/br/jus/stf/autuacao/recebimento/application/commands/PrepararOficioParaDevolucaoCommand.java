package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
@ApiModel("Command que realiza a ação de preparação para devolução de uma remessa.")
public class PrepararOficioParaDevolucaoCommand {

	@ApiModelProperty(value = "Id do protocolo da remessa.", required = true)
	@NotNull
	private Long protocoloId;
    
	@ApiModelProperty(value = "Id do motivo da devolução.", required = true)
    @NotNull
    private Long motivo;
    
	@ApiModelProperty(value = "Id do modelo de devolução.", required = true)
    @NotNull
    private Long modeloId;
    
	@ApiModelProperty(value = "Id do texto vinculado com a devolução.", required = true)
    @NotNull
    private Long textoId;
    
    public PrepararOficioParaDevolucaoCommand() {
    	// Construtor default.
    }
    
    public Long getProtocoloId() {
        return protocoloId;
    }
    
    public Long getMotivo() {
    	return motivo;
    }
    
    public Long getModeloId() {
    	return modeloId;
    }
    
    public Long getTextoId() {
    	return textoId;
    }
    
}
