package br.jus.stf.autuacao.recebimento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.infra.RemessaRepositoryImpl;
import br.jus.stf.core.shared.documento.TipoDocumentoId;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.protocolo.Numero;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * Testes unitários para repositório de remessas.
 * 
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 28.09.2016
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.discovery.enabled:false",
		"spring.cloud.config.enabled:false" }, classes = { ApplicationContextInitializer.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
		SqlScriptsTestExecutionListener.class })
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
@ActiveProfiles("test")
public class RemessaRepositoryUnitTests {

	@Autowired
    private TestEntityManager entityManager;

	private RemessaRepository repository;
	
	private Long protocoloId = 1L;
	
	@Before
	public void setUp() {
		repository = new RemessaRepositoryImpl(entityManager.getEntityManager());
	}
	
	@Test
	public void salvarRemessa() {
		Remessa remessa = remessa();
		Remessa remessaSalva = repository.save(remessa);
		
		assertNotNull("Remessa salva não pode ser nula.", remessaSalva);
		assertEquals(String.format("Tipo da remessa deve ser %s.", remessa.tipoProcesso()), remessa.tipoProcesso(),
				remessaSalva.tipoProcesso());
		assertEquals(String.format("ProtocoloId deve ser igual a %d.", remessa.identity().toLong()), remessa.identity(),
				remessaSalva.identity());
		assertEquals(String.format("Número da remessa deve ser %s.", remessa.numero()), remessa.numero(),
				remessaSalva.numero());
	}
	
	@Test
	public void recuperarRemessaPeloId() {
		Remessa remessaSalva = repository.save(remessa());
		Remessa remessaRecuperada = repository.findOne(remessaSalva.identity());
		
		assertNotNull("Remessa recuperada não pode ser nula.", remessaRecuperada);
		assertEquals(String.format("Tipo da remessa deve ser %s.", remessaSalva.tipoProcesso()),
				remessaSalva.tipoProcesso(), remessaRecuperada.tipoProcesso());
		assertEquals(String.format("ProtocoloId deve ser igual a %d.", remessaSalva.identity().toLong()),
				remessaSalva.identity(), remessaRecuperada.identity());
		assertEquals(String.format("Número da remessa deve ser %s.", remessaSalva.numero()), remessaSalva.numero(),
				remessaRecuperada.numero());
	}
	
	@Test
	public void listarRemessas() {
		repository.save(remessa());
		repository.save(remessa());
		
		List<Remessa> remessas = repository.findAll();
		
		assertNotNull("Lista de remessas não pode ser nula.", remessas);
		assertEquals("Lista de remessas deve ter dois elementos.", 2, remessas.size());
	}
	
	@Test
	public void salvarMotivoDevolucao() {
		MotivoDevolucao motivo = motivoDevolucao();
		MotivoDevolucao motivoSalvo = repository.saveMotivoDevolucao(motivo);
		
		assertNotNull("Motivo salvo não pode ser nulo.", motivoSalvo);
		assertEquals(String.format("Descrição deve ser igual a %s.", motivo.descricao()), motivo.descricao(),
				motivoSalvo.descricao());
	}
	
	@Test
	public void atualizarMotivoDevolucao() {
		MotivoDevolucao motivo = motivoDevolucao();
		
		motivo.atribuirTiposDocumento(new HashSet<TipoDocumentoId>(Arrays.asList(new TipoDocumentoId(1L))));
		motivo = repository.saveMotivoDevolucao(motivo);
		
		assertEquals("O motivo deve ter um tipo de documento associado.", 1, motivo.tiposDocumento().size());
		
		motivo.removerTiposDocumento(new HashSet<TipoDocumentoId>(Arrays.asList(new TipoDocumentoId(1L))));
		motivo = repository.saveMotivoDevolucao(motivo);
		
		assertTrue("O motivo não deve ter tipo de documento associado.", motivo.tiposDocumento().isEmpty());
	}
	
	@Test
	public void recuperarMotivoDevolucaoPeloId() {
		MotivoDevolucao motivoSalvo = repository.saveMotivoDevolucao(motivoDevolucao());
		MotivoDevolucao motivoRecuperado = repository.findOneMotivoDevolucao(motivoSalvo.identity());
		
		assertNotNull("Motivo recuperado não pode ser nulo.", motivoRecuperado);
		assertEquals(String.format("Id do motivo deve ser igual a %d.", motivoSalvo.identity()), motivoSalvo.identity(),
				motivoRecuperado.identity());
		assertEquals(String.format("Descrição deve ser igual a %s.", motivoSalvo.descricao()), motivoSalvo.descricao(),
				motivoRecuperado.descricao());
	}
	
	@Test
	public void listarMotivosDevolucao() {
		int tamanhoInicial = repository.findAllMotivoDevolucao().size();
		
		repository.saveMotivoDevolucao(motivoDevolucao());
		repository.saveMotivoDevolucao(motivoDevolucao());
		
		List<MotivoDevolucao> motivos = repository.findAllMotivoDevolucao();
		
		assertNotNull("Lista de motivos não pode ser nula.", motivos);
		assertEquals("Lista de motivos deve ter dois elementos.", (tamanhoInicial + 2), motivos.size());
	}
	
	@Test
	public void excluirMotivoDevolucao() {
		MotivoDevolucao motivoSalvo = repository.saveMotivoDevolucao(motivoDevolucao());
		MotivoDevolucao motivoRecuperado = repository.findOneMotivoDevolucao(motivoSalvo.identity());
		
		assertEquals("Motivos devem ser iguais.", motivoSalvo, motivoRecuperado);
		
		repository.deleteMotivoDevolucao(motivoSalvo);
		
		motivoRecuperado = repository.findOneMotivoDevolucao(motivoSalvo.identity());
		
		assertNull("Motivo recuperado deve ser nulo após exclusão.", motivoRecuperado);
	}

	private Remessa remessa() {
		// Apenas para utilizar os dois tipos de remessa (Originária e Recursal)
		Remessa remessa = (new Random().nextInt() % 2 == 0)
				? new RemessaOriginario(new Protocolo(new ProtocoloId(protocoloId), new Numero(protocoloId++, 2016)), 1,
						0, FormaRecebimento.BALCAO, null, Sigilo.PUBLICO, new Recebedor("recebedor", new PessoaId(1L)),
						Status.PREAUTUACAO)
				: new RemessaRecursal(new Protocolo(new ProtocoloId(protocoloId), new Numero(protocoloId++, 2016)), 1,
						0, FormaRecebimento.BALCAO, null, Sigilo.PUBLICO, new Recebedor("recebedor", new PessoaId(1L)),
						Status.PREAUTUACAO);

		return remessa;
	}
	
	private MotivoDevolucao motivoDevolucao() {
		return new MotivoDevolucao(RandomStringUtils.randomAlphabetic(10));
	}

}
