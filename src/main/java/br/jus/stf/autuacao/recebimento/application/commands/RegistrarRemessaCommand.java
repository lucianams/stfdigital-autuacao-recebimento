package br.jus.stf.autuacao.recebimento.application.commands;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Representa o comando de registro de remessas.
 * 
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 18.12.2015
 */
public class RegistrarRemessaCommand {
    
    @NotBlank
    private String formaRecebimento;
    
    @Min(1L)
    private int volumes;
    
    @Min(0L)
    private int apensos;
    
    private String numeroSedex;
    
    @NotBlank
    private String tipoProcesso;
    
    public String getFormaRecebimento() {
        return formaRecebimento;
    }

    public int getVolumes() {
        return volumes;
    }

    public int getApensos() {
        return apensos;
    }

    public String getNumeroSedex() {
        return numeroSedex;
    }
    
    public String getTipoProcesso() {
        return tipoProcesso;
    }
    
}
