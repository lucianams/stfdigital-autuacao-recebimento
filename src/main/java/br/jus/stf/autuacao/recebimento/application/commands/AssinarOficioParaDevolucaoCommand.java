package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.NotNull;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
public class AssinarOficioParaDevolucaoCommand {

    @NotNull
    private Long protocoloId;
    
    public Long getProtocoloId() {
        return protocoloId;
    }
    
}
