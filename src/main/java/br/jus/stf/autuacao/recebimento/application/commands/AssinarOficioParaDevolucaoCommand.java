package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
@ApiModel(description = "Command que realiza a ação de assinatura do ofício de devolução de uma remessa.")
public class AssinarOficioParaDevolucaoCommand {

    @NotNull
    @ApiModelProperty(value = "Protocolo da remessa.", required = true)
    private Long protocoloId;

    @NotBlank
    @ApiModelProperty(value = "Id do documento temporário assinado.", required = true)
    private String documentoTemporarioId;

    public AssinarOficioParaDevolucaoCommand() {
        // Construtor default.
    }

    public Long getProtocoloId() {
        return protocoloId;
    }

    public String getDocumentoTemporarioId() {
        return documentoTemporarioId;
    }

}
