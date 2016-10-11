package br.jus.stf.autuacao.recebimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.jus.stf.autuacao.recebimento.domain.RemessaFactory;
import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.TipoRecebimento;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Testes unitários para a fábrica de remessas.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 29.09.2016
 */
public class RemessaFactoryUnitTests {

    @Test
    public void criaRemessaRecursalValida() {
        RemessaRecursal remessa = (RemessaRecursal) new RemessaFactory().novaRemessa(
                new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 1, 0,
                new TipoRecebimento(FormaRecebimento.BALCAO, null),
                Sigilo.PUBLICO, TipoProcesso.RECURSAL, new Recebedor("RECEBEDOR", new PessoaId(1L)),
                br.jus.stf.autuacao.recebimento.domain.model.Status.PREAUTUACAO);

        assertNotNull("Remessa não pode ser nula.", remessa);
        assertEquals("Tipo do processo deve ser RECURSAL.", TipoProcesso.RECURSAL, remessa.tipoProcesso());
        assertEquals("ProtocoloId deve ser igual a 1.", new ProtocoloId(1L), remessa.identity());
        assertEquals("Número deve ser igual a 1/2016.", new Numero(1L, 2016), remessa.numero());
        assertEquals("Sigilo deve ser PUBLICO.", Sigilo.PUBLICO, remessa.sigilo());
        assertEquals("Forma de recebimento deve ser BALCAO.", FormaRecebimento.BALCAO,
                remessa.tipoRecebimento().formaRecebimento());
    }

    @Test
    public void criaRemessaOriginariaValida() {
        RemessaOriginario remessa = (RemessaOriginario) new RemessaFactory().novaRemessa(
                new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 1, 0,
                new TipoRecebimento(FormaRecebimento.BALCAO, null),
                Sigilo.PUBLICO, TipoProcesso.ORIGINARIO, new Recebedor("RECEBEDOR", new PessoaId(1L)),
                br.jus.stf.autuacao.recebimento.domain.model.Status.PREAUTUACAO);

        assertNotNull("Remessa não pode ser nula.", remessa);
        assertEquals("Tipo do processo deve ser ORIGINARIO.", TipoProcesso.ORIGINARIO, remessa.tipoProcesso());
        assertEquals("ProtocoloId deve ser igual a 1.", new ProtocoloId(1L), remessa.identity());
        assertEquals("Número deve ser igual a 1/2016.", new Numero(1L, 2016), remessa.numero());
        assertEquals("Sigilo deve ser PUBLICO.", Sigilo.PUBLICO, remessa.sigilo());
        assertEquals("Forma de recebimento deve ser BALCAO.", FormaRecebimento.BALCAO,
                remessa.tipoRecebimento().formaRecebimento());
    }

}
