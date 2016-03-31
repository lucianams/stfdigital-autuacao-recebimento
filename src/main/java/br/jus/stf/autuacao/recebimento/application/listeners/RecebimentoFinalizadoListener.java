package br.jus.stf.autuacao.recebimento.application.listeners;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.autuacao.recebimento.infra.RabbitConfiguration;
import br.jus.stf.core.shared.eventos.RecebimentoFinalizado;
import br.jus.stf.core.shared.protocolo.ProtocoloId;

/**
 * TODO Rodrigo Barreiros Analisar se esse listener está no lugar certo
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 26.12.2015
 */
@Component
public class RecebimentoFinalizadoListener {

	/**
	 * TODO Rodrigo Barreiros Analisar se é melhor uma interface com implementação do Rabbit
	 */
	@Autowired
	private RabbitTemplate rabbitTemplate;
    
    public void handle(ProtocoloId protocoloId) {
    	rabbitTemplate.convertAndSend(RabbitConfiguration.REMESSA_RECEBIDA_QUEUE, new RecebimentoFinalizado(protocoloId.toLong()));
    }
    
}