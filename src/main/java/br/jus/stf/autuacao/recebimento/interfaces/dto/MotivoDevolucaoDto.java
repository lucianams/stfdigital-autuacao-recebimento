package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Objeto usado para transportar os dados de motivos de devolução de petição. 
 * 
 * @author Anderson.Araujo
 * @since 15.03.2016
 *
 */
public class MotivoDevolucaoDto {
	@ApiModelProperty(value = "Id do motivo.")
	private Long id;
	
	@ApiModelProperty(value = "Descrição do motivo")
	private String descricao;
	
	public MotivoDevolucaoDto(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
