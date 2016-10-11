package br.jus.stf.autuacao.recebimento.domain;

import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.domain.model.TipoRecebimento;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;

/**
 * @author Rodrigo Barreiros
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 25.12.2015
 */
@Component
public class RemessaFactory {

    /**
     * @param protocolo Protocolo com identificação da remessa.
     * @param volumes Quantidade de volumes da remessa.
     * @param apensos Quantidade de apensos da remessa.
     * @param tipoRecebimento Dados do tipo de recebimento da remessa.
     * @param sigilo Grau de sigilo da remessa.
     * @param tipoProcesso Tipo do processo associado com a remessa.
     * @param recebedor Usuário que registrou a remessa.
     * @param status Status inicial do BPM para remessa.
     * @return
     */
    public Remessa novaRemessa(Protocolo protocolo, Integer volumes, Integer apensos, TipoRecebimento tipoRecebimento,
            Sigilo sigilo, TipoProcesso tipoProcesso, Recebedor recebedor, Status status) {
        Remessa remessa;

        switch (tipoProcesso) {

            case ORIGINARIO:
                remessa = new RemessaOriginario(protocolo, volumes, apensos,
                        tipoRecebimento, sigilo,
                        recebedor, status);
                break;
            case RECURSAL:
                remessa = new RemessaRecursal(protocolo, volumes, apensos,
                        tipoRecebimento, sigilo,
                        recebedor, status);
                break;
            default:
                throw new IllegalArgumentException(String.format("Tipo de processo não localizado: %s.", tipoProcesso));
        }
        return remessa;
    }

}
