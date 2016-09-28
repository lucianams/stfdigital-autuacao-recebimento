package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Tomas Godoi
 * 
 * @since 22.06.2016
 */
@ApiModel("Representa uma devolução.")
public class DevolucaoDto {

	@ApiModelProperty("O id da remessa.")
	private Long remessaProtocoloId;
	
	@ApiModelProperty("Número da remessa.")
	private Long remessaNumero;
	
	@ApiModelProperty("Ano da remessa.")
	private Integer remessaAno;

	@ApiModelProperty("Modelo de devolução.")
	private ModeloDevolucaoDto modeloDevolucao;

	@ApiModelProperty("O id do texto.")
	private Long textoId;

	/**
	 * @param remessaProtocoloId
	 * @param remessaNumero
	 * @param remessaAno
	 * @param modeloDevolucao
	 * @param textoId
	 */
	public DevolucaoDto(Long remessaProtocoloId, Long remessaNumero, Integer remessaAno, ModeloDevolucaoDto modeloDevolucao, Long textoId) {
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
