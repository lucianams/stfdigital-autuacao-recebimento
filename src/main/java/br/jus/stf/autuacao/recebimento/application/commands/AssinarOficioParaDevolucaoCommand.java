package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class AssinarOficioParaDevolucaoCommand {

    @NotNull
    private Long protocoloId;
    
    @NotBlank
    private String documentoTemporarioId;
    
    public AssinarOficioParaDevolucaoCommand() {
    	
    }
    
    public AssinarOficioParaDevolucaoCommand(Long protocoloId, String documentoTemporarioId) {
    	this.protocoloId = protocoloId;
    	this.documentoTemporarioId = documentoTemporarioId;
    }
    
    public Long getProtocoloId() {
        return protocoloId;
    }

	public String getDocumentoTemporarioId() {
		return documentoTemporarioId;
	}
    
}
