package br.jus.stf.autuacao.recebimento.domain;

import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.core.framework.workflow.StatusAdapterSupport;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 24.12.2015
 */
@Component
public class StatusAdapter extends StatusAdapterSupport<ProtocoloId, Status> {

    private static final String RECEBIMENTO_PROCESS_KEY = "recebimento";
    
    private static final String RECEBIMENTO_ID_PATTERN = "RE:%s";
    
    @Override
    protected Status statusFromDescription(String description) {
        return Status.valueOf(description);
    }

    @Override
    protected String processId(ProtocoloId informationId) {
        return String.format(RECEBIMENTO_ID_PATTERN, informationId.toString());
    }
    
    @Override
    protected String processKey() {
        return RECEBIMENTO_PROCESS_KEY;
    }
    
}
