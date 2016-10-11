package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Tomas Godoi
 * 
 * @since 22.06.2016
 */
@ApiModel(description = "DTO que representa uma devolução.")
public class DevolucaoDto {

    @ApiModelProperty(value = "O id da remessa.")
    private Long remessaProtocoloId;

    @ApiModelProperty(value = "Número da remessa.")
    private Long remessaNumero;

    @ApiModelProperty(value = "Ano da remessa.")
    private Integer remessaAno;

    @ApiModelProperty(value = "Modelo de devolução.")
    private ModeloDevolucaoDto modeloDevolucao;

    @ApiModelProperty(value = "O id do texto.")
    private Long textoId;

    /**
     * @param remessaProtocoloId O id da remessa.
     * @param remessaNumero Número da remessa.
     * @param remessaAno Ano da remessa.
     * @param modeloDevolucao odelo de devolução.
     * @param textoId O id do texto.
     */
    public DevolucaoDto(Long remessaProtocoloId, Long remessaNumero, Integer remessaAno,
            ModeloDevolucaoDto modeloDevolucao, Long textoId) {
        this.remessaProtocoloId = remessaProtocoloId;
        this.remessaNumero = remessaNumero;
        this.remessaAno = remessaAno;
        this.modeloDevolucao = modeloDevolucao;
        this.textoId = textoId;
    }

    public Long getRemessaProtocoloId() {
        return remessaProtocoloId;
    }

    public Long getRemessaNumero() {
        return remessaNumero;
    }

    public Integer getRemessaAno() {
        return remessaAno;
    }

    public ModeloDevolucaoDto getModeloDevolucao() {
        return modeloDevolucao;
    }

    public Long getTextoId() {
        return textoId;
    }

}
