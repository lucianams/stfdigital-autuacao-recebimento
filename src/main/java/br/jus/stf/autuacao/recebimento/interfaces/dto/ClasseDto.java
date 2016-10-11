package br.jus.stf.autuacao.recebimento.interfaces.dto;

import java.util.HashSet;
import java.util.Set;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Dto da classe peticionável
 * 
 * @author viniciusk
 * 
 * @since 17.05.2016
 */
@ApiModel(description = "DTO que representa uma classe peticionável.")
public class ClasseDto {

    @ApiModelProperty(value = "Sigla da classe peticionável.")
    private String sigla;

    @ApiModelProperty(value = "Nome da classe peticionável.")
    private String nome;

    @ApiModelProperty(value = "Conjunto de preferências da classe peticionável.")
    private Set<PreferenciaDto> preferencias = new HashSet<>();

    /**
     * @param sigla Sigla da classe.
     * @param nome Nome da classe.
     * @param preferencias Preferências da classe.
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
