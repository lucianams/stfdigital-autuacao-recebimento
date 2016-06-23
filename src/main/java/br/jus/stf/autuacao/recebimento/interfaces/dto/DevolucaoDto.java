package br.jus.stf.autuacao.recebimento.interfaces.dto;

public class DevolucaoDto {

	private Long remessaProtocoloId;
	
	private Long remessaNumero;
	private Integer remessaAno;

	private ModeloDevolucaoDto modeloDevolucao;

	private Long textoId;

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

	public void setRemessaProtocoloId(Long remessaProtocoloId) {
		this.remessaProtocoloId = remessaProtocoloId;
	}

	public Long getRemessaNumero() {
		return remessaNumero;
	}

	public void setRemessaNumero(Long remessaNumero) {
		this.remessaNumero = remessaNumero;
	}

	public Integer getRemessaAno() {
		return remessaAno;
	}

	public void setRemessaAno(Integer remessaAno) {
		this.remessaAno = remessaAno;
	}

	public ModeloDevolucaoDto getModeloDevolucao() {
		return modeloDevolucao;
	}

	public void setModeloDevolucao(ModeloDevolucaoDto modeloDevolucao) {
		this.modeloDevolucao = modeloDevolucao;
	}

	public Long getTextoId() {
		return textoId;
	}

	public void setTextoId(Long textoId) {
		this.textoId = textoId;
	}

}
