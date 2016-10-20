package br.jus.stf.autuacao.recebimento.infra;

import static java.time.Year.now;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.jus.stf.autuacao.recebimento.infra.ProtocoloRestAdapter;
import br.jus.stf.autuacao.recebimento.infra.client.ProtocoloRestClient;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Valida o Adapter de número de protocolo.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 28.09.2016
 */
public class ProtocoloRestAdapterUnitTests {

    @Mock
    private ProtocoloRestClient protocoloRestClient;

    @InjectMocks
    private ProtocoloRestAdapter protocoloRestAdapter;

    private String ano = String.valueOf(now().getValue());

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(protocoloRestClient.identificador()).thenReturn(1L);
        Mockito.when(protocoloRestClient.identificador(ano)).thenReturn(1L);
    }

    @Test
    public void novoProtocolo() {
        Protocolo protocolo = protocoloRestAdapter.novoProtocolo();

        Assert.assertNotNull("Protocolo não pode ser nulo.", protocolo);
        Assert.assertEquals("Id do protocolo deve ser igual a 1.", new ProtocoloId(1L), protocolo.identity());
        Assert.assertEquals(String.format("Número deve ser igual a 1/%s.", ano), new Numero(1L, new Integer(ano)),
                protocolo.numero());

        Mockito.verify(protocoloRestClient, Mockito.times(1)).identificador();
        Mockito.verify(protocoloRestClient, Mockito.times(1)).identificador(ano);
    }

}
