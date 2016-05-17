package br.jus.stf.autuacao.recebimento.interfaces.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author viniciusk
 * Dto da classe peticionável
 */
public class ClasseDto {
	
	private String id;
	private String nome;
	private Set<PreferenciaDto> preferencias = new HashSet<>();
	
	public ClasseDto(String id, String nome, Set<PreferenciaDto> preferencias) {
		this.id = id;
		this.nome = nome;
		this.preferencias = preferencias;
	}

	public String getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}

	public Set<PreferenciaDto> getPreferencias() {
		return preferencias;
	}
	
	

}
