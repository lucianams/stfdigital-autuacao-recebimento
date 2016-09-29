package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author viniciusk
 *
 */
@ApiModel("Representa uma remessa.")
public class RemessaDto {

	@ApiModelProperty("O id do protocolo.")
	private Long protocolo;

	@ApiModelProperty("Classe da remessa.")
	private String classe;

	@ApiModelProperty("Quantidade de volumes.")
	private int qtdVolumes;

	@ApiModelProperty("Quantidade de apensos.")
	private int qtdApensos;

	@ApiModelProperty("Forma de recebimento.")
	private String formaRecebimento;

	@ApiModelProperty("Número do sedex.")
	private String numeroSedex;
	
	@ApiModelProperty("Grau de sigilo da remessa.")
	private String sigilo;
	
	@ApiModelProperty("Número da remessa.")
	private String numero;

	/**
	 * @param protocolo
	 * @param classe
	 * @param qtdVolumes
	 * @param qtdApensos
	 * @param formaRecebimento
	 * @param numeroSedex
	 * @param sigilo
	 * @param numero
	 */
	public RemessaDto(Long protocolo, String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex, String sigilo, String numero) {
		this(classe, qtdVolumes, qtdApensos, formaRecebimento, numeroSedex, sigilo, numero);
		this.protocolo = protocolo;
	}
	
	/**
	 * @param classe
	 * @param qtdVolumes
	 * @param qtdApensos
	 * @param formaRecebimento
	 * @param numeroSedex
	 * @param sigilo
	 * @param numero
	 */
	public RemessaDto(String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex, String sigilo, String numero) {
		this.classe = classe;
		this.qtdVolumes = qtdVolumes;
		this.qtdApensos = qtdApensos;
		this.formaRecebimento = formaRecebimento;
		this.numeroSedex = numeroSedex;
		this.sigilo = sigilo;
		this.numero = numero;
	}

	public Long getProtocolo() {
		return protocolo;
	}

	public String getClasse() {
		return classe;
	}

	public int getQtdVolumes() {
		return qtdVolumes;
	}

	public int getQtdApensos() {
		return qtdApensos;
	}

	public String getFormaRecebimento() {
		return formaRecebimento;
	}

	public String getNumeroSedex() {
		return numeroSedex;
	}

	public String getSigilo() {
		return sigilo;
	}

	public String getNumero() {
		return numero;
	}
	
}
