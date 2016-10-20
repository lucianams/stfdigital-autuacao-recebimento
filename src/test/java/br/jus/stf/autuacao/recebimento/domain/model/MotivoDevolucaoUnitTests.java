package br.jus.stf.autuacao.recebimento.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.core.shared.documento.TipoDocumentoId;

/**
 * Testes unitários para motivo devolução.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 29.09.2016
 */
public class MotivoDevolucaoUnitTests {

    @Test
    public void criaMotivoDevolucaoValido() {
        MotivoDevolucao motivo = motivoDevolucaoValido();

        assertNotNull("Motivo não pode ser nulo.", motivo);
        assertEquals("Descrição do motivo deve ser Dados inválidos.", "Dados inválidos", motivo.descricao());
        assertTrue("Motivo não deve ter tipos de documentos associados.", motivo.tiposDocumento().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void naoDeveCriarMotivoComDescricaoNula() {
        new MotivoDevolucao(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveCriarMotivoComDescricaoVazia() {
        new MotivoDevolucao("");
    }

    @Test
    public void atribuiTiposDocumentosAoMotivoDevolucao() {
        MotivoDevolucao motivo = motivoDevolucaoValido();
        Set<TipoDocumentoId> tipos = new HashSet<TipoDocumentoId>(Arrays.asList(new TipoDocumentoId(1L)));

        motivo.atribuirTiposDocumento(tipos);

        assertNotNull("Motivo não pode ser nulo.", motivo);
        assertEquals("Motivo deve ter um tipo de documento associado.", 1, motivo.tiposDocumento().size());
        assertEquals("Tipos de documentos devem ser iguais.", tipos, motivo.tiposDocumento());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAtribuirTiposDocumentosVazioAoMotivoDevolucao() {
        MotivoDevolucao motivo = motivoDevolucaoValido();

        motivo.atribuirTiposDocumento(Collections.emptySet());
    }

    @Test
    public void removeTiposDocumentosDoMotivoDevolucao() {
        MotivoDevolucao motivo = motivoDevolucaoValido();
        Set<TipoDocumentoId> tipos = new HashSet<TipoDocumentoId>(Arrays.asList(new TipoDocumentoId(1L)));

        motivo.atribuirTiposDocumento(tipos);

        assertEquals("Motivo deve ter um tipo de documento associado.", 1, motivo.tiposDocumento().size());

        motivo.removerTiposDocumento(tipos);

        assertTrue("Motivo não deve ter tipos de documentos associados.", motivo.tiposDocumento().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveRemoverTiposDocumentosVazioDoMotivoDevolucao() {
        MotivoDevolucao motivo = motivoDevolucaoValido();
        Set<TipoDocumentoId> tipos = new HashSet<TipoDocumentoId>(Arrays.asList(new TipoDocumentoId(1L)));

        motivo.atribuirTiposDocumento(tipos);
        motivo.removerTiposDocumento(Collections.emptySet());
    }

    private MotivoDevolucao motivoDevolucaoValido() {
        return new MotivoDevolucao("Dados inválidos");
    }

}
