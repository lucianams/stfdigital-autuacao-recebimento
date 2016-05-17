package br.jus.stf.autuacao.recebimento.interfaces.dto;

/**
 * @author viniciusk
 * Dto da das preferências que o preautuador poderá selecionar
 */
public class PreferenciaDto {
	
	private String id;
	private String nome;
	
	public PreferenciaDto(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}

}
