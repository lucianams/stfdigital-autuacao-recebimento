package br.jus.stf.autuacao.recebimento.infra.configuration;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import br.jus.stf.core.framework.stream.StreamConfigurerSupport;
import br.jus.stf.core.shared.eventos.RecebimentoFinalizado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;

/**
 * Configuração do mecanismo que será usado pelo serviço para 
 * publicação e/ou recebimento de eventos de domínio.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.08.2016
 */
@EnableBinding(StreamConfigurer.Channels.class)
public class StreamConfigurer extends StreamConfigurerSupport {
	
	/**
	 * @see br.jus.stf.core.framework.stream.StreamConfigurerSupport#serviceSchema()
	 */
	@Override
	protected String serviceSchema() {
		return "recebimento";
	}
	
	/**
	 * Declaração dos canais mencionados acima.
	 * 
	 * @author Rodrigo Barreiros
	 * 
	 * @since 1.0.0
	 * @since 18.08.2016
	 */
	public interface Channels {

		/**
		 * O canal que será usado para publicação de eventos do tipo {@link RemessaRegistrada}.
		 * 
		 * @return o canal para as remessas registradas
		 */
		@Output(RemessaRegistrada.EVENT_KEY)
		MessageChannel remessaRegistrada();

		/**
		 * O canal que será usado para publicação de eventos do tipo {@link RecebimentoFinalizado}.
		 * 
		 * @return o canal para os recebimentos finalizados
		 */
		@Output(RecebimentoFinalizado.EVENT_KEY)
		MessageChannel recebimentoFinalizado();

	}

}
