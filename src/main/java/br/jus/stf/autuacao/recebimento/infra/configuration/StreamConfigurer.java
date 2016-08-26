package br.jus.stf.autuacao.recebimento.infra.configuration;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import br.jus.stf.core.framework.stream.StreamConfigurerSupport;
import br.jus.stf.core.shared.eventos.PeticaoRegistrada;
import br.jus.stf.core.shared.eventos.RecebimentoFinalizado;
import br.jus.stf.core.shared.eventos.RemessaRegistrada;

/**
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
	 * Configuração dos canais que serão usados pelo serviço de peticionamento
	 * para publicação de eventos de domínio.
	 * 
	 * @author Rodrigo Barreiros
	 * 
	 * @since 1.0.0
	 * @since 18.08.2016
	 */
	public interface Channels {

		/**
		 * O canal que será usado para publicação de eventos do tipo {@link PeticaoRegistrada}.
		 * 
		 * @return o canal para as petições registradas
		 */
		@Output(RemessaRegistrada.EVENT_KEY)
		MessageChannel remessaRegistrada();

		@Output(RecebimentoFinalizado.EVENT_KEY)
		MessageChannel recebimentoFinalizado();

	}

}
