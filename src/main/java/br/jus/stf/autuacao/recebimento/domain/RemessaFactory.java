package br.jus.stf.autuacao.recebimento.domain;

import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginaria;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * O
 * @since 1.0.0
 * @since 25.12.2015
 */
@Component
public class RemessaFactory {

    public Remessa novaRemessa(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento, String numeroSedex, String recebedor, TipoProcesso tipoProcesso, Status status) {
        Remessa remessa;
        
		switch (tipoProcesso) {
		case ORIGINARIO:
			remessa = new RemessaOriginaria(protocolo, volumes, apensos, formaRecebimento, numeroSedex, recebedor, status);
			break;
		case RECURSAL:
			remessa = new RemessaRecursal(protocolo, volumes, apensos, formaRecebimento, numeroSedex, recebedor, status);
			break;
		default:
			throw new IllegalArgumentException(String.format("Tipo de processo não localizado: %s.", tipoProcesso));
		}
    	return remessa;
    }

}
