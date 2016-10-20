package br.jus.stf.autuacao.recebimento.infra;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.ImmutableMap;

import br.jus.stf.autuacao.recebimento.infra.DevolucaoRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.client.DocumentoRestClient;
import br.jus.stf.core.shared.documento.TextoId;

/**
 * Valida o Adapter de devolução.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 29.09.2016
 */
public class DevolucaoRestAdapterUnitTests {

    @Mock
    private DocumentoRestClient documentoRestClient;

    @InjectMocks
    private DevolucaoRestAdapter devolucaoRestAdapter;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void concluirTexto() {
        TextoId textoId = new TextoId(1L);
        Map<String, Object> command = ImmutableMap.of("textoId", textoId.toLong());

        devolucaoRestAdapter.concluirTexto(textoId);

        Mockito.verify(documentoRestClient, Mockito.times(1)).concluirTexto(command);
    }

    @Test
    public void assinarTexto() {
        TextoId textoId = new TextoId(1L);
        String documentoTemporarioId = "_DocTemp_";
        Map<String, Object> command = new HashMap<>();

        command.put("textoId", textoId.toLong());
        command.put("documentoTemporarioId", documentoTemporarioId);

        devolucaoRestAdapter.assinarTexto(textoId, documentoTemporarioId);

        Mockito.verify(documentoRestClient, Mockito.times(1)).assinarTexto(command);
    }

}
