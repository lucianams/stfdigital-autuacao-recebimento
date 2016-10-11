package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author viniciusk
 *
 * @since 26.04.2016
 */
@ApiModel(description = "DTO que representa uma forma de recebimento.")
public class FormaRecebimentoDto {

    @ApiModelProperty(value = "Sigla da forma de recebimento.")
    private String sigla;

    @ApiModelProperty(value = "Descrição da forma de recebimento.")
    private String descricao;

    @ApiModelProperty(value = "Indica a obrigatoriedade da numeração para a forma de recebimento.")
    private boolean exigeNumeracao;

    /**
     * @param sigla Sigla da forma de recebimento.
     * @param descricao Descrição da forma de recebimento.
     * @param exigeNumeracao Indica a obrigatoriedade da numeração para a forma de recebimento.
     */
    public FormaRecebimentoDto(String sigla, String descricao, boolean exigeNumeracao) {
        this.sigla = sigla;
        this.descricao = descricao;
        this.exigeNumeracao = exigeNumeracao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isExigeNumeracao() {
        return exigeNumeracao;
    }

}
