package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import br.jus.stf.core.shared.documento.TextoId;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class PrepararOficioParaDevolucaoCommand {

    @NotNull
	private Long protocoloId;
    
    @NotNull
    private Long motivo;
    
    @NotNull
    private Long modeloId;
    
    @NotNull
    private TextoId textoId;
    
    public Long getProtocoloId() {
        return protocoloId;
    }
    
    public Long getMotivo() {
    	return motivo;
    }
    
    public Long getModeloId() {
    	return modeloId;
    }
    
    public TextoId getTextoId() {
    	return textoId;
    }
    
}
