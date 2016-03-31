package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class AssinarOficioParaDevolucaoCommand {

    @NotNull
    private ProtocoloId protocoloId;
    
    public ProtocoloId getProtocoloId() {
        return protocoloId;
    }
    
}
