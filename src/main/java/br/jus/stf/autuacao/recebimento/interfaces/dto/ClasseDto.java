package br.jus.stf.autuacao.recebimento.interfaces.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author viniciusk
 * Dto da classe peticionável
 */
public class ClasseDto {
	
	private String sigla;
	private String nome;
	private Set<PreferenciaDto> preferencias = new HashSet<>();
	
	/**
	 * @param sigla
	 * @param nome
	 * @param preferencias
	 */
	public ClasseDto(String sigla, String nome, Set<PreferenciaDto> preferencias) {
		this.sigla = sigla;
		this.nome = nome;
		this.preferencias = preferencias;
	}

	public String getSigla() {
		return sigla;
	}


	public String getNome() {
		return nome;
	}

	public Set<PreferenciaDto> getPreferencias() {
		return preferencias;
	}
	
	

}
