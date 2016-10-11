package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Dto da das preferências que o preautuador poderá selecionar
 * 
 * @author viniciusk
 * 
 * @since 17.05.2016
 */
@ApiModel(description = "DTO que representa uma preferência.")
public class PreferenciaDto {

    @ApiModelProperty(value = "Id da preferência.")
    private Long id;

    @ApiModelProperty(value = "Nome da preferência.")
    private String nome;

    /**
     * @param id Id da preferência.
     * @param nome Nome da preferência.
     */
    public PreferenciaDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
