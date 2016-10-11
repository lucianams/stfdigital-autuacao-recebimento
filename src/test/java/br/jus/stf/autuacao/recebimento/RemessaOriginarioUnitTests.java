package br.jus.stf.autuacao.recebimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.domain.model.TipoRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.suportejudicial.Preferencia;
import br.jus.stf.core.shared.classe.ClasseId;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.preferencia.PreferenciaId;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Testes unitários para registro de remessas originárias.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 29.06.2016
 */
public class RemessaOriginarioUnitTests {

    @Test
    public void registraRemessaOriginariaValida() {
        RemessaOriginario remessa = remessaOriginariaValida();

        assertNotNull("Remessa não pode ser nula.", remessa);
        assertEquals("Tipo da remessa deve ser ORIGINARIO.", TipoProcesso.ORIGINARIO, remessa.tipoProcesso());
        assertEquals("ProtocoloId deve ser igual a 1.", new ProtocoloId(1L), remessa.identity());
        assertEquals("Número da remessa deve ser 1/2016.", new Numero(1L, 2016), remessa.numero());
    }

    @Test
    public void preAutuaRemessaOriginaria() {
        RemessaOriginario remessa = remessaOriginariaValida();
        Preferencia medidaLiminar = new Preferencia(new PreferenciaId(8L), "Medida Liminar");
        Preferencia reuPreso = new Preferencia(new PreferenciaId(12L), "Réu Preso");
        Set<Preferencia> preferenciasClasse = new HashSet<>(2);

        preferenciasClasse.add(medidaLiminar);
        preferenciasClasse.add(reuPreso);

        ClassePeticionavel classe = new ClassePeticionavel(new ClasseId("ADI"), "Ação Direta de Inconstitucionalidade",
                TipoProcesso.ORIGINARIO, preferenciasClasse);
        Set<Preferencia> preferenciasRemessa = new HashSet<>(1);

        preferenciasRemessa.add(medidaLiminar);
        remessa.preautuar(classe, preferenciasRemessa, Sigilo.PUBLICO, Status.RECEBIDA);

        assertEquals("Classe deve ser igual a ADI.", classe, remessa.classe());
        assertEquals("Sigilo deve ser igual a PUBLICO.", Sigilo.PUBLICO, remessa.sigilo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDevePreAutuarRemessaOriginariaComTipoProcessoIncompativel() {
        RemessaOriginario remessa = remessaOriginariaValida();
        ClassePeticionavel classe = new ClassePeticionavel(new ClasseId("RE"), "Recurso Extraordinário",
                TipoProcesso.RECURSAL, null);

        remessa.preautuar(classe, null, Sigilo.PUBLICO, Status.RECEBIDA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDevePreAutuarRemessaOriginariaComPreferenciaIncompativel() {
        RemessaOriginario remessa = remessaOriginariaValida();
        Preferencia medidaLiminar = new Preferencia(new PreferenciaId(8L), "Medida Liminar");
        ClassePeticionavel classe = new ClassePeticionavel(new ClasseId("ADI"), "Ação Direta de Inconstitucionalidade",
                TipoProcesso.ORIGINARIO, null);
        Set<Preferencia> preferenciasRemessa = new HashSet<>(1);

        preferenciasRemessa.add(medidaLiminar);
        remessa.preautuar(classe, preferenciasRemessa, Sigilo.PUBLICO, Status.RECEBIDA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCriarRemessaOriginariaComVolumeMenorQue1() {
        new RemessaOriginario(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 0, 0,
                new TipoRecebimento(FormaRecebimento.BALCAO, null), Sigilo.PUBLICO,
                new Recebedor("recebedor", new PessoaId(1L)),
                Status.PREAUTUACAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCriarRemessaOriginariaComApensoMenorQue0() {
        new RemessaOriginario(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 1, -1,
                new TipoRecebimento(FormaRecebimento.BALCAO, null), Sigilo.PUBLICO,
                new Recebedor("recebedor", new PessoaId(1L)),
                Status.PREAUTUACAO);
    }

    private RemessaOriginario remessaOriginariaValida() {
        return new RemessaOriginario(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 1, 0,
                new TipoRecebimento(FormaRecebimento.BALCAO, null), Sigilo.PUBLICO,
                new Recebedor("recebedor", new PessoaId(1L)),
                Status.PREAUTUACAO);
    }

}
