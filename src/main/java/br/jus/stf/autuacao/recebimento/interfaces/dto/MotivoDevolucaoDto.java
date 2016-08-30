package br.jus.stf.autuacao.recebimento.interfaces.dto;

import java.util.Set;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Objeto usado para transportar os dados de motivos de devolução de petição. 
 * 
 * @author Anderson.Araujo
 * @since 15.03.2016
 *
 */
@ApiModel("Contém os dados de um motivo de devolução")
public class MotivoDevolucaoDto {
	@ApiModelProperty(value = "Id do motivo.")
	private Long id;
	
	@ApiModelProperty(value = "Descrição do motivo")
	private String descricao;
	
	@ApiModelProperty(value = "Os tipos de documento associados ao motivo")
	private Set<Long> tiposDocumento;
	
	/**
	 * @param id
	 * @param descricao
	 * @param tiposDocumento
	 */
	public MotivoDevolucaoDto(Long id, String descricao, Set<Long> tiposDocumento) {
		this.id = id;
		this.descricao = descricao;
		this.tiposDocumento = tiposDocumento;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Set<Long> getTiposDocumento() {
		return tiposDocumento;
	}
}
