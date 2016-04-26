package br.jus.stf.autuacao.recebimento.infra;

public class FormaRecebimentoDto {
	
	private String descricao;
	
	private boolean exigeNumeracao;
	
	public FormaRecebimentoDto(String descricao, boolean exigeNumeracao) {
		this.descricao = descricao;
		this.exigeNumeracao = exigeNumeracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isExigeNumeracao() {
		return exigeNumeracao;
	}
	
	
	

}
