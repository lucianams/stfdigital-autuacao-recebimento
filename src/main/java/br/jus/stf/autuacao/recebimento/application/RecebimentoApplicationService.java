package br.jus.stf.autuacao.recebimento.application;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stf.autuacao.recebimento.application.commands.AssinarOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.DevolverRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarOriginarioCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarRecursalCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PrepararOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.RegistrarRemessaCommand;
import br.jus.stf.autuacao.recebimento.domain.DevolucaoAdapter;
import br.jus.stf.autuacao.recebimento.domain.ProtocoloAdapter;
import br.jus.stf.autuacao.recebimento.domain.RecebedorAdapter;
import br.jus.stf.autuacao.recebimento.domain.RemessaFactory;
import br.jus.stf.autuacao.recebimento.domain.StatusAdapter;
import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaOriginario;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRecursal;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavelRepository;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucaoRepository;
import br.jus.stf.autuacao.recebimento.domain.model.preferencia.Preferencia;
import br.jus.stf.autuacao.recebimento.domain.model.preferencia.PreferenciaRepository;
import br.jus.stf.autuacao.recebimento.infra.RabbitEventPublisher;
import br.jus.stf.core.framework.component.command.Command;
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.core.shared.classe.ClasseId;
import br.jus.stf.core.shared.documento.ModeloDocumentoId;
import br.jus.stf.core.shared.documento.TextoId;
import br.jus.stf.core.shared.eventos.RecebimentoFinalizado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
import br.jus.stf.core.shared.preferencia.PreferenciaId;
import br.jus.stf.core.shared.processo.Sigilo;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
@ApplicationService
@Transactional
public class RecebimentoApplicationService {

    @Autowired
    private RabbitEventPublisher publisher;
    
    @Autowired
    private RemessaRepository remessaRepository;
    
    @Autowired
    private ClassePeticionavelRepository classeRepository;
    
    @Autowired
    private PreferenciaRepository preferenciaRepository;
    
    @Autowired
    private ModeloDevolucaoRepository modeloDevolucaoRepository;

    @Autowired
    private ProtocoloAdapter protocoloAdapter; 
    
    @Autowired
    private RemessaFactory remessaFactory;

    @Autowired
    private StatusAdapter statusAdapter;
    
    @Autowired
    private DevolucaoAdapter devolucaoAdapter;
    
    @Autowired
    private RecebedorAdapter recebedorAdapter;
     
    /**
     * @param command
     */
    @Command(description = "Nova Petição Física", startProcess = true, listable = false)
    public void handle(RegistrarRemessaCommand command) {
		Protocolo protocolo = protocoloAdapter.novoProtocolo();
		Status status = statusAdapter.nextStatus(protocolo.identity(), command.getTipoProcesso());
		TipoProcesso tipoProcesso = TipoProcesso.valueOf(command.getTipoProcesso());
		FormaRecebimento formaRecebimento = FormaRecebimento.valueOf(command.getFormaRecebimento());
		Sigilo sigilo = Sigilo.valueOf(command.getSigilo());
		Recebedor recebedor = recebedorAdapter.recebedor();
		Remessa remessa = remessaFactory.novaRemessa(protocolo, command.getVolumes(), command.getApensos(),
				formaRecebimento, command.getNumeroSedex(), sigilo, tipoProcesso, recebedor, status);
        
        remessaRepository.save(remessa);
        publisher.publish(new RemessaRegistrada(protocolo.identity().toLong(), protocolo.toString()));
    }

    /**
     * @param command
     */
    @Command(description = "Preautuação de Originário")
    public void handle(PreautuarOriginarioCommand command) {
        RemessaOriginario remessa = (RemessaOriginario) remessaRepository.findOne(new ProtocoloId(command.getProtocoloId()));
        Status status = statusAdapter.nextStatus(remessa.identity(), "AUTUAR");
        Sigilo sigilo = Sigilo.valueOf(command.getSigilo());
        ClassePeticionavel classe = classeRepository.findOne(new ClasseId(command.getClasseId()));
		Set<Preferencia> preferencias = Optional.ofNullable(command.getPreferencias())
				.map(prefs -> prefs.stream().map(pref -> preferenciaRepository.findOne(new PreferenciaId(pref)))
						.collect(Collectors.toCollection(() -> new HashSet<>(0))))
				.get();
            
        remessa.preautuar(classe, preferencias, sigilo, status);
        remessaRepository.save(remessa);
        publisher.publish(new RecebimentoFinalizado(remessa.identity().toLong(), classe.identity().toString(), remessa.tipoProcesso().toString(), remessa.sigilo().toString(), remessa.isCriminalEleitoral()));
    }
    
    /**
     * @param command
     */
    @Command(description = "Preautuação de Recursais")
    public void handle(PreautuarRecursalCommand command) {
    	RemessaRecursal remessa = (RemessaRecursal) remessaRepository.findOne(new ProtocoloId(command.getProtocoloId()));
        Status status = statusAdapter.nextStatus(remessa.identity(), "AUTUAR");
        Sigilo sigilo = Sigilo.valueOf(command.getSigilo());
        ClassePeticionavel classe = classeRepository.findOne(new ClasseId(command.getClasseId()));
		Set<Preferencia> preferencias = Optional.ofNullable(command.getPreferencias())
				.map(prefs -> prefs.stream().map(pref -> preferenciaRepository.findOne(new PreferenciaId(pref)))
						.collect(Collectors.toCollection(() -> new HashSet<>(0))))
				.get();
            
        remessa.preautuar(classe, preferencias, sigilo, command.getNumeroProcessoOrigem(), command.getNumeroUnicoProcesso(), status);
        remessaRepository.save(remessa);
        publisher.publish(new RecebimentoFinalizado(remessa.identity().toLong(), classe.identity().toString(), remessa.tipoProcesso().toString(), remessa.sigilo().toString(), remessa.isCriminalEleitoral()));
    }
    
    /**
     * @param command
     */
    @Command(description = "Devolução")
    public void handle(DevolverRemessaCommand command) {
        Remessa remessa = remessaRepository.findOne(new ProtocoloId(command.getProtocoloId()));
        Status status = statusAdapter.nextStatus(remessa.identity(), "DEVOLVER");
        
        remessa.iniciarDevolucao(command.getMotivo(), status);
        remessaRepository.save(remessa);
    }

    /**
     * @param command
     */
    @Command(description = "Preparar Ofício para Devolução", value = "preparar-oficio-devolucao")
    public void handle(PrepararOficioParaDevolucaoCommand command) {
        Remessa remessa = remessaRepository.findOne(new ProtocoloId(command.getProtocoloId()));
        Status status = statusAdapter.nextStatus(remessa.identity());
        MotivoDevolucao motivo = remessaRepository.findOneMotivoDevolucao(command.getMotivo());
        ModeloDevolucao modelo = modeloDevolucaoRepository.findOne(new ModeloDocumentoId(command.getModeloId()));
        
        remessa.elaborarDevolucao(motivo, modelo, new TextoId(command.getTextoId()), status);
        devolucaoAdapter.concluirTexto(remessa.devolucao().texto());
        remessaRepository.save(remessa);
    }

    /**
     * @param command
     */
    @Command(description = "Assinar Ofício para Devolução", value = "assinar-oficio-devolucao")
    public void handle(AssinarOficioParaDevolucaoCommand command) {
        Remessa remessa = remessaRepository.findOne(new ProtocoloId(command.getProtocoloId()));
        Status status = statusAdapter.nextStatus(remessa.identity());
        
        devolucaoAdapter.assinarTexto(remessa.devolucao().texto(), command.getDocumentoTemporarioId());
        remessa.devolver(status);
        remessaRepository.save(remessa);
    }

}
