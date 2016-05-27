package br.jus.stf.autuacao.recebimento.interfaces.dto;

public class FormaRecebimentoDto {
	
	private String sigla;
	private String descricao;
	
	private boolean exigeNumeracao;
	
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
