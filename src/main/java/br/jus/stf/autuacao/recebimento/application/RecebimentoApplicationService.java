package br.jus.stf.autuacao.recebimento.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stf.autuacao.recebimento.application.commands.AssinarOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PreautuarRemessaCommand;
import br.jus.stf.autuacao.recebimento.application.commands.PrepararOficioParaDevolucaoCommand;
import br.jus.stf.autuacao.recebimento.application.commands.RegistrarRemessaCommand;
import br.jus.stf.autuacao.recebimento.domain.ProtocoloAdapter;
import br.jus.stf.autuacao.recebimento.domain.RemessaFactory;
import br.jus.stf.autuacao.recebimento.domain.StatusAdapter;
import br.jus.stf.autuacao.recebimento.domain.model.Remessa;
import br.jus.stf.autuacao.recebimento.domain.model.RemessaRepository;
import br.jus.stf.autuacao.recebimento.domain.model.Status;
import br.jus.stf.autuacao.recebimento.infra.RabbitEventPublisher;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;
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
    private ProtocoloAdapter protocoloAdapter; 
    
    @Autowired
    private RemessaFactory remessaFactory;

    @Autowired
    private StatusAdapter statusAdapter;
    
    @Transactional
    public void handle(RegistrarRemessaCommand command) {
    	Protocolo protocolo = protocoloAdapter.novoProtocolo();
    	
        Status status = statusAdapter.nextStatus(protocolo.identity(), command.getTipoProcesso());
        
        Remessa remessa = remessaFactory.novaRemessa(protocolo, command.getVolumes(), command.getApensos(), command.getFormaRecebimento(), command.getNumeroSedex(), command.getTipoProcesso(), status);
        
        remessaRepository.save(remessa);
        
        publisher.publish(new RemessaRegistrada(protocolo.identity().toLong(), protocolo.toString()));
    }

    @Transactional
    public void handle(PreautuarRemessaCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        
        Status status = statusAdapter.nextStatus(remessa.identity(), command.getTransicao());
        
        remessa.classificar(command.getClasseId(), command.getMotivo(), status);
        
        remessaRepository.save(remessa);
    }

    @Transactional
    public void handle(PrepararOficioParaDevolucaoCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        
        Status status = statusAdapter.nextStatus(remessa.identity());
        
        remessa.prepararDevolucao(status);
        
        remessaRepository.save(remessa);
    }

    @Transactional
    public void handle(AssinarOficioParaDevolucaoCommand command) {
        Remessa remessa = remessaRepository.findOne(command.getProtocoloId());
        
        Status status = statusAdapter.nextStatus(remessa.identity());
        
        remessa.concluirDevolucao(status);
        
        remessaRepository.save(remessa);
    }

}
