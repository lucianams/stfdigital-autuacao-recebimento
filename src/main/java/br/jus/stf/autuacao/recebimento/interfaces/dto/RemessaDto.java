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

	public RemessaDto(Long protocolo, String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex) {
		this(classe, qtdVolumes, qtdApensos, formaRecebimento, numeroSedex);
		this.protocolo = protocolo;
	}
	
	public RemessaDto(String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex) {
		this.classe = classe;
		this.qtdVolumes = qtdVolumes;
		this.qtdApensos = qtdApensos;
		this.formaRecebimento = formaRecebimento;
		this.numeroSedex = numeroSedex;
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

}
