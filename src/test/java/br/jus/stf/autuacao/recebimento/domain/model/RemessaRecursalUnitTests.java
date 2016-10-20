package br.jus.stf.autuacao.recebimento.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
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
 * Testes unitários para registro de remessas recursais.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
public class RemessaRecursalUnitTests {

    @Test
    public void registraRemessaRecursalValida() {
        RemessaRecursal remessa = remessaRecursalValida();

        assertNotNull("Remessa não pode ser nula.", remessa);
        assertEquals("Tipo da remessa deve ser RECURSAL.", TipoProcesso.RECURSAL, remessa.tipoProcesso());
        assertEquals("ProtocoloId deve ser igual a 2.", new ProtocoloId(2L), remessa.identity());
        assertEquals("Número da remessa deve ser 2/2016.", new Numero(2L, 2016), remessa.numero());
    }

    @Test
    public void preAutuaRemessaRecursal() {
        RemessaRecursal remessa = remessaRecursalValida();
        Preferencia medidaLiminar = new Preferencia(new PreferenciaId(8L), "Medida Liminar");
        Preferencia reuPreso = new Preferencia(new PreferenciaId(12L), "Réu Preso");
        Set<Preferencia> preferenciasClasse = new HashSet<>(0);

        preferenciasClasse.add(medidaLiminar);
        preferenciasClasse.add(reuPreso);

        ClassePeticionavel classe = new ClassePeticionavel(new ClasseId("RE"), "Recurso Extraordinário",
                TipoProcesso.RECURSAL, preferenciasClasse);
        Set<Preferencia> preferenciasRemessa = new HashSet<>(0);

        preferenciasRemessa.add(medidaLiminar);
        remessa.preautuar(classe, preferenciasRemessa, Sigilo.SEGREDO_JUSTICA, "RE-100", "00000100-15.2008.100.0000",
                Status.RECEBIDA);

        assertEquals("Classe deve ser igual a RE.", classe, remessa.classe());
        assertEquals("Sigilo deve ser igual a SEGREDO_JUSTICA.", Sigilo.SEGREDO_JUSTICA, remessa.sigilo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDevePreAutuarRemessaRecursalComTipoProcessoIncompativel() {
        RemessaRecursal remessa = remessaRecursalValida();
        ClassePeticionavel classe = new ClassePeticionavel(new ClasseId("ADI"), "Ação Direta de Inconstitucionalidade",
                TipoProcesso.ORIGINARIO, null);

        remessa.preautuar(classe, null, Sigilo.SEGREDO_JUSTICA, "ADI-100", "00000100-15.2008.100.0000",
                Status.RECEBIDA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDevePreAutuarRemessaRecursalComPreferenciaIncompativel() {
        RemessaRecursal remessa = remessaRecursalValida();
        Preferencia medidaLiminar = new Preferencia(new PreferenciaId(8L), "Medida Liminar");
        ClassePeticionavel classe = new ClassePeticionavel(new ClasseId("RE"), "Recurso Extraordinário",
                TipoProcesso.RECURSAL, null);
        Set<Preferencia> preferenciasRemessa = new HashSet<>(0);

        preferenciasRemessa.add(medidaLiminar);
        remessa.preautuar(classe, preferenciasRemessa, Sigilo.SEGREDO_JUSTICA, "RE-100", "00000100-15.2008.100.0000",
                Status.RECEBIDA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCriarRemessaRecursalComVolumeMenorQue1() {
        new RemessaRecursal(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 0, 0,
                new TipoRecebimento(FormaRecebimento.BALCAO, null), Sigilo.PUBLICO,
                new Recebedor("recebedor", new PessoaId(1L)),
                Status.PREAUTUACAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCriarRemessaRecursalComApensoMenorQue0() {
        new RemessaRecursal(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 1, -1,
                new TipoRecebimento(FormaRecebimento.BALCAO, null), Sigilo.PUBLICO,
                new Recebedor("recebedor", new PessoaId(1L)),
                Status.PREAUTUACAO);
    }

    private RemessaRecursal remessaRecursalValida() {
        return new RemessaRecursal(new Protocolo(new ProtocoloId(2L), new Numero(2L, 2016)), 1, 0,
                new TipoRecebimento(FormaRecebimento.BALCAO, null), Sigilo.PUBLICO,
                new Recebedor("recebedor", new PessoaId(1L)),
                Status.PREAUTUACAO);
    }

}
