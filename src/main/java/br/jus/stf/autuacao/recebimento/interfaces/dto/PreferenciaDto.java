package br.jus.stf.autuacao.recebimento.interfaces.dto;

/**
 * @author viniciusk
 * Dto da das preferências que o preautuador poderá selecionar
 */
public class PreferenciaDto {
	
	private Long id;
	private String nome;
	
	public PreferenciaDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}

}
