package br.jus.stf.autuacao.recebimento.application.commands;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * @author Anderson Araújo
 * @author Rodrigo Barreiros
 * 
 * @version 1.0.0
 * @since 15.09.2015
 */
@ApiModel("Command que realiza a ação de preautuação de uma remessa originária.")
public class PreautuarOriginarioCommand extends PreautuarCommand {

    public PreautuarOriginarioCommand() {
        // Construtor default.
    }

}
