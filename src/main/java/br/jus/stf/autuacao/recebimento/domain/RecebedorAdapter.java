package br.jus.stf.autuacao.recebimento.domain;

import br.jus.stf.autuacao.recebimento.domain.model.Recebedor;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 05.07.2016
 */
@FunctionalInterface
public interface RecebedorAdapter {
	
	/**
	 * @return
	 */
	Recebedor recebedor();

}
