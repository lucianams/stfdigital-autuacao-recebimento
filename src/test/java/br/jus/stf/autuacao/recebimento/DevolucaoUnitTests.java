package br.jus.stf.autuacao.recebimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.core.shared.documento.DocumentoId;
import br.jus.stf.core.shared.documento.ModeloDocumentoId;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.documento.TipoDocumentoId;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Testes unitários para devolução de remessas.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
public class DevolucaoUnitTests {
	
	@Test
	public void iniciaDevolucao() {
		Remessa remessa = remessaValida();
		String motivacao = "Remessa inválida.";
		
		remessa.iniciarDevolucao(motivacao, Status.DEVOLUCAO);
		
		assertNotNull("Devolução não pode ser nula.", remessa.devolucao());
		assertEquals("Motivação deve ser igual a Remessa inválida.", motivacao, remessa.devolucao().motivacao());
	}
	
	@Test
	public void elaboraOficioDevolucao() {
		Remessa remessa = remessaValida();
		TipoDocumentoId oficio = new TipoDocumentoId(8L);
		ModeloDevolucao modelo = new ModeloDevolucao(new ModeloDocumentoId(1L), "Ofício de devolução de remessa",
				oficio, new DocumentoId(2L));
		MotivoDevolucao motivo = new MotivoDevolucao("Faltam Peças");
		Set<TipoDocumentoId> tipos = new HashSet<>(1);
		
		tipos.add(oficio);
		motivo.atribuirTiposDocumento(tipos);
		remessa.iniciarDevolucao("Remessa inválida.", Status.DEVOLUCAO);
		remessa.elaborarDevolucao(motivo, modelo, new TextoId(1L), Status.ASSINATURA);
		
		assertEquals("Descrição do motivo deve ser igual a Faltam Peças.", motivo.descricao(), remessa.devolucao().motivo().descricao());
		assertEquals("Modelo deve ser igual a Ofício de devolução de remessa.", modelo, remessa.devolucao().modelo());
		assertEquals("Texto deve ser igual a 1.", new TextoId(1L), remessa.devolucao().texto());
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveElaborarOficioDevolucaoSemIniciarDevolucao() {
		Remessa remessa = remessaValida();
		TipoDocumentoId oficio = new TipoDocumentoId(8L);
		ModeloDevolucao modelo = new ModeloDevolucao(new ModeloDocumentoId(1L), "Ofício de devolução de remessa",
				oficio, new DocumentoId(2L));
		MotivoDevolucao motivo = new MotivoDevolucao("Faltam Peças");

		remessa.elaborarDevolucao(motivo, modelo, new TextoId(1L), Status.ASSINATURA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveElaborarOficioDevolucaoComModeloMotivoIncompativeis() {
		Remessa remessa = remessaValida();
		TipoDocumentoId alvara = new TipoDocumentoId(1L);
		ModeloDevolucao modelo = new ModeloDevolucao(new ModeloDocumentoId(1L), "Ofício de devolução de remessa",
				alvara, new DocumentoId(2L));
		MotivoDevolucao motivo = new MotivoDevolucao("Faltam Peças");
		Set<TipoDocumentoId> tipos = new HashSet<>(1);
		TipoDocumentoId oficio = new TipoDocumentoId(8L);
		
		tipos.add(oficio);
		motivo.atribuirTiposDocumento(tipos);
		remessa.iniciarDevolucao("Remessa inválida.", Status.DEVOLUCAO);
		remessa.elaborarDevolucao(motivo, modelo, new TextoId(1L), Status.ASSINATURA);
	}
	
	private Remessa remessaValida() {
		// Apenas para utilizar os dois tipos de remessa (Originária e Recursal)
		Remessa remessa = (new Random().nextInt() % 2 == 0)
				? new RemessaOriginario(new Protocolo(new ProtocoloId(1L), new Numero(1L, 2016)), 1, 0,
						FormaRecebimento.BALCAO, null, Sigilo.PUBLICO, new Recebedor("recebedor", new PessoaId(1L)),
						Status.PREAUTUACAO)
				: new RemessaRecursal(new Protocolo(new ProtocoloId(2L), new Numero(2L, 2016)), 1, 0,
						FormaRecebimento.BALCAO, null, Sigilo.PUBLICO, new Recebedor("recebedor", new PessoaId(1L)),
						Status.PREAUTUACAO);
		
		return remessa;
	}

}
