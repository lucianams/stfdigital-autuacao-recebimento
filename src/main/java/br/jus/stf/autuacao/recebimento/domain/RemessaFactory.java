package br.jus.stf.autuacao.recebimento.domain;

import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.core.shared.protocolo.Protocolo;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 25.12.2015
 */
@Component
public class RemessaFactory {

    public Remessa novaRemessa(Protocolo protocolo, Integer volumes, Integer apensos, String formaRecebimento, String numeroSedex, String tipoProcesso, Status status) {
        return new Remessa(protocolo, volumes, apensos, formaRecebimento, numeroSedex, tipoProcesso, status);
    }

}
