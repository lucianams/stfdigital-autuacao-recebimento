package br.jus.stf.autuacao.recebimento.application;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stf.autuacao.recebimento.application.commands.AssinarOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.DevolverRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PrepararOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.RegistrarRemessaCommand;
import br.jus.stf.autuacao.recebimento.domain.ProtocoloAdapter;
import br.jus.stf.autuacao.recebimento.domain.RemessaFactory;
import br.jus.stf.autuacao.recebimento.domain.StatusAdapter;
import br.jus.stf.autuacao.recebimento.domain.model.FormaRecebimento;
import br.jus.stf.autuacao.recebimento.domain.model.MotivoDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavel;
import br.jus.stf.autuacao.recebimento.domain.model.classe.ClassePeticionavelRepository;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucao;
import br.jus.stf.autuacao.recebimento.domain.model.documento.ModeloDevolucaoRepository;
import br.jus.stf.autuacao.recebimento.domain.model.preferencia.Preferencia;
import br.jus.stf.autuacao.recebimento.domain.model.preferencia.PreferenciaRepository;
import br.jus.stf.autuacao.recebimento.infra.RabbitEventPublisher;
import br.jus.stf.core.shared.classe.ClasseId;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.processo.TipoProcesso;
import br.jus.stf.core.shared.protocolo.Protocolo;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
@Component
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
    
    @Transactional
    public Long handle(RegistrarRemessaCommand command) {
    	Protocolo protocolo = protocoloAdapter.novoProtocolo();
    	Status status = statusAdapter.nextStatus(protocolo.identity(), command.getTipoProcesso());
    	TipoProcesso tipoProcesso = TipoProcesso.valueOf(command.getTipoProcesso());
    	FormaRecebimento formaRecebimento = FormaRecebimento.valueOf(command.getFormaRecebimento());
        //TODO: Alterar para pegar dados do recebedor pelo usuário da sessão.
    	Recebedor recebedor = new Recebedor("USUARIO_FALSO", new PessoaId(1L));
    	Remessa remessa = remessaFactory.novaRemessa(protocolo, command.getVolumes(), command.getApensos(),
				formaRecebimento, command.getNumeroSedex(), recebedor, tipoProcesso, status);
        
        remessaRepository.save(remessa);
        publisher.publish(new RemessaRegistrada(protocolo.identity().toLong(), protocolo.toString()));
        
        return remessa.identity().toLong();
    }

    @Transactional
    public void handle(PreautuarRemessaCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        Status status = statusAdapter.nextStatus(remessa.identity(), "AUTUAR");
        ClassePeticionavel classe = classeRepository.findOne(new ClasseId(command.getClasseId()));
		Set<Preferencia> preferencias = Optional.ofNullable(command.getPreferencias())
				.map(prefs -> prefs.stream().map(pref -> preferenciaRepository.findOne(pref))
						.collect(Collectors.toCollection(() -> new HashSet<Preferencia>())))
				.get();
            
        remessa.preautuar(classe, preferencias, status);
        remessaRepository.save(remessa);
    }
    
    @Transactional
    public void handle(DevolverRemessaCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        Status status = statusAdapter.nextStatus(remessa.identity(), "DEVOLVER");
        
        remessa.iniciarDevolucao(command.getMotivo(), status);
        remessaRepository.save(remessa);
    }

    @Transactional
    public void handle(PrepararOficioParaDevolucaoCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        Status status = statusAdapter.nextStatus(remessa.identity());
        MotivoDevolucao motivo = remessaRepository.findOneMotivoDevolucao(command.getMotivo());
        ModeloDevolucao modelo = modeloDevolucaoRepository.findOne(command.getModeloId());
        
        remessa.elaborarDevolucao(motivo, modelo, command.getTextoId(), status);
        remessaRepository.save(remessa);
    }

    @Transactional
    public void handle(AssinarOficioParaDevolucaoCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        Status status = statusAdapter.nextStatus(remessa.identity());
        
        remessa.devolver(status);
        remessaRepository.save(remessa);
    }

}
