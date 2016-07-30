package br.jus.stf.autuacao.recebimento.interfaces.dto;

/**
 * @author viniciusk
 *
 */
public class RemessaDto {

	private Long protocolo;

	private String classe;

	private int qtdVolumes;

	private int qtdApensos;

	private String formaRecebimento;

	private String numeroSedex;
	
	private String sigilo;
	
	private String numero;

	public RemessaDto(Long protocolo, String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex, String sigilo, String numero) {
		this(classe, qtdVolumes, qtdApensos, formaRecebimento, numeroSedex, sigilo, numero);
		this.protocolo = protocolo;
	}
	
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

	public void setProtocolo(Long protocolo) {
		this.protocolo = protocolo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getQtdVolumes() {
		return qtdVolumes;
	}

	public void setQtdVolumes(int qtdVolumes) {
		this.qtdVolumes = qtdVolumes;
	}

	public int getQtdApensos() {
		return qtdApensos;
	}

	public void setQtdApensos(int qtdApensos) {
		this.qtdApensos = qtdApensos;
	}

	public String getFormaRecebimento() {
		return formaRecebimento;
	}

	public void setFormaRecebimento(String formaRecebimento) {
		this.formaRecebimento = formaRecebimento;
	}

	public String getNumeroSedex() {
		return numeroSedex;
	}

	public void setNumeroSedex(String numeroSedex) {
		this.numeroSedex = numeroSedex;
	}

	public String getSigilo() {
		return sigilo;
	}

	public void setSigilo(String sigilo) {
		this.sigilo = sigilo;
	}

	public String getNumero() {
		return numero;
	}
	
}
