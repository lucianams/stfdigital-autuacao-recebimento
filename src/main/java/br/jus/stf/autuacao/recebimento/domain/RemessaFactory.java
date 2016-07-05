package br.jus.stf.autuacao.recebimento.domain;

import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.core.shared.processo.Sigilo;
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

    public Remessa novaRemessa(Protocolo protocolo, Integer volumes, Integer apensos, FormaRecebimento formaRecebimento, String numeroSedex, Sigilo sigilo, TipoProcesso tipoProcesso, Recebedor recebedor, Status status) {
        Remessa remessa;
        
		switch (tipoProcesso) {
		case ORIGINARIO:
			remessa = new RemessaOriginario(protocolo, volumes, apensos, formaRecebimento, numeroSedex, sigilo, recebedor, status);
			break;
		case RECURSAL:
			remessa = new RemessaRecursal(protocolo, volumes, apensos, formaRecebimento, numeroSedex, sigilo, recebedor, status);
			break;
		default:
			throw new IllegalArgumentException(String.format("Tipo de processo não localizado: %s.", tipoProcesso));
		}
    	return remessa;
    }

}
