package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Tomas Godoi
 * 
 * @since 31.05.2016
 *
 */
@ApiModel(description = "DTO que epresenta um modelo.")
public class ModeloDevolucaoDto {

    @ApiModelProperty(value = "O id do modelo.")
    private Long id;

    @ApiModelProperty(value = "O id do tipo do documento.")
    private Long tipoDocumento;

    @ApiModelProperty(value = "O nome do modelo.")
    private String nome;

    @ApiModelProperty(value = "O id do documento do modelo.")
    private Long documento;

    /**
     * @param id O id do modelo.
     * @param tipoDocumento O id do tipo do documento.
     * @param nome O nome do modelo.
     * @param documento O id do documento do modelo.
     */
    public ModeloDevolucaoDto(final Long id, final Long tipoDocumento, final String nome, final Long documento) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.nome = nome;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public Long getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNome() {
        return nome;
    }

    public Long getDocumento() {
        return documento;
    }

}
