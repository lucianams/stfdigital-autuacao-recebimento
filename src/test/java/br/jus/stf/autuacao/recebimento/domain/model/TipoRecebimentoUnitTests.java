package br.jus.stf.autuacao.recebimento.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.TipoRecebimento;

/**
 * Testes unitários para tipo de recebimento.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 10.10.2016
 */
public class TipoRecebimentoUnitTests {

    @Test
    public void criaTipoRecebimentoComFormaQueExigeNumeracaoValido() {
        TipoRecebimento tipo = new TipoRecebimento(FormaRecebimento.SEDEX, "123");

        assertNotNull("Tipo não pode ser nulo.", tipo);
        assertEquals("Forma deve ser SEDEX.", FormaRecebimento.SEDEX, tipo.formaRecebimento());
        assertEquals("Número sedex deve ser igual a 123.", "123", tipo.numeroSedex());
    }

    @Test
    public void criaTipoRecebimentoComFormaQueNaoExigeNumeracaoValido() {
        TipoRecebimento tipo = new TipoRecebimento(FormaRecebimento.E_MAIL, null);

        assertNotNull("Tipo não pode ser nulo.", tipo);
        assertEquals("Forma deve ser E_MAIL.", FormaRecebimento.E_MAIL, tipo.formaRecebimento());
        assertNull("Número sedex deve ser nula.", tipo.numeroSedex());
    }

    @Test
    public void criaTipoRecebimentoComFormaQueNaoExigeNumeracaoEInformaNumero() {
        TipoRecebimento tipo = new TipoRecebimento(FormaRecebimento.BALCAO, "123");

        assertNotNull("Tipo não pode ser nulo.", tipo);
        assertEquals("Forma deve ser BALCAO.", FormaRecebimento.BALCAO, tipo.formaRecebimento());
        assertEquals("Número sedex deve ser igual a 123.", "123", tipo.numeroSedex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCriarTipoRecebimentoComFormaQueExigeNumeracaoSemInformarNumero() {
        new TipoRecebimento(FormaRecebimento.SEDEX, null);
    }
}
