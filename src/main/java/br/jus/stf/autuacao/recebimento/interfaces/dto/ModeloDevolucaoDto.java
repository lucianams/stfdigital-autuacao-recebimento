package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Tomas Godoi
 * 
 * @since 31.05.2016
 *
 */
@ApiModel("Representa um Modelo")
public class ModeloDevolucaoDto {

	@ApiModelProperty("O id do modelo")
	private Long id;

	@ApiModelProperty("O id do tipo do documento")
	private Long tipoDocumento;

	@ApiModelProperty("O nome do modelo")
	private String nome;

	@ApiModelProperty("O id do documento do modelo")
	private Long documento;

	/**
	 * @param id
	 * @param tipoDocumento
	 * @param nome
	 * @param documento
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

}
