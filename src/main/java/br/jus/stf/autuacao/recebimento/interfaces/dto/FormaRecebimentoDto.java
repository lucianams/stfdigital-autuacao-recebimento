package br.jus.stf.autuacao.recebimento.interfaces.dto;

/**
 * @author viniciusk
 *
 * @since 26.04.2016
 */
public class FormaRecebimentoDto {
	
	private String sigla;
	private String descricao;
	
	private boolean exigeNumeracao;
	
	/**
	 * @param sigla
	 * @param descricao
	 * @param exigeNumeracao
	 */
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
