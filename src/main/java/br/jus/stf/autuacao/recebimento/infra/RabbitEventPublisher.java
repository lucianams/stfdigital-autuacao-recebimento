package br.jus.stf.autuacao.recebimento.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.core.framework.domaindrivendesign.DomainEvent;
import br.jus.stf.core.framework.domaindrivendesign.DomainEventPublisher;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 04.03.2016
 */
@Component
public class RabbitEventPublisher implements DomainEventPublisher {
	
	private static Map<Class<?>, String> queues = new HashMap<>();
	
	static {
		queues.put(RemessaRegistrada.class, RabbitConfiguration.REMESSA_REGISTRADA_QUEUE);
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void publish(DomainEvent<?> event) {
		rabbitTemplate.convertAndSend(queues.get(event.getClass()), event);
	}

}
