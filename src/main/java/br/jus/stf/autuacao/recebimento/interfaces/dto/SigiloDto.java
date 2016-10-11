package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author viniciusk
 * 
 * @since 15.07.2016
 */
@ApiModel(description = "DTO que representa um sigilo.")
public class SigiloDto {

    @ApiModelProperty(value = "Nome do sigilo.")
    private String nome;

    @ApiModelProperty(value = "Descrição do sigilo.")
    private String descricao;

    /**
     * @param nome Nome do sigilo.
     * @param descricao Descrição do sigilo.
     */
    public SigiloDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
