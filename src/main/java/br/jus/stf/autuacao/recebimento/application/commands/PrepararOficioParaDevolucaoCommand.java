package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import br.jus.stf.core.shared.documento.ModeloDocumentoId;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class PrepararOficioParaDevolucaoCommand {

    @NotNull
	private ProtocoloId protocoloId;
    
    @NotNull
    private Long motivo;
    
    @NotNull
    private ModeloDocumentoId modeloId;
    
    @NotNull
    private TextoId textoId;
    
    public ProtocoloId getProtocoloId() {
        return protocoloId;
    }
    
    public Long getMotivo() {
    	return motivo;
    }
    
    public ModeloDocumentoId getModeloId() {
    	return modeloId;
    }
    
    public TextoId getTextoId() {
    	return textoId;
    }
    
}
