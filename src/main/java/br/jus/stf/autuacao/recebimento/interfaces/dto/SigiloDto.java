package br.jus.stf.autuacao.recebimento.interfaces.dto;

public class SigiloDto {
	
	private String nome;
	private String descricao;
	
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
