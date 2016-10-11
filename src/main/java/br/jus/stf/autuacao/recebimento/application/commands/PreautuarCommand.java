package br.jus.stf.autuacao.recebimento.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rafael Alencar
 * 
 * @version 1.0.0
 * @since 29.09.2015
 */
@ApiModel("Command que realiza a ação de preautuação de uma remessa.")
public abstract class PreautuarCommand {

    @NotNull
    @ApiModelProperty(value = "Protocolo da remessa.", required = true)
    private Long protocoloId;

    @NotBlank
    @ApiModelProperty(value = "Classe da remessa.", required = true)
    private String classeId;

    @NotBlank
    @ApiModelProperty(value = "Grau de sigilo da remessa.", required = true)
    private String sigilo;

    @ApiModelProperty(value = "Lista de preferências da remessa.")
    private Set<Long> preferencias;

    public Long getProtocoloId() {
        return protocoloId;
    }

    public String getClasseId() {
        return classeId;
    }

    public Set<Long> getPreferencias() {
        return preferencias;
    }

    public String getSigilo() {
        return sigilo;
    }

}
