package br.jus.stf.autuacao.recebimento.interfaces.dto;

/**
 * @author viniciusk
 * 
 * @since 15.07.2016
 *
 */
public class SigiloDto {
	
	private String nome;
	private String descricao;
	
	/**
	 * @param nome
	 * @param descricao
	 */
	public SigiloDto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
