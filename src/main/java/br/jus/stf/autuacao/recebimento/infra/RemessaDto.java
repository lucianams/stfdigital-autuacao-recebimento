package br.jus.stf.autuacao.recebimento.infra;


/**
 * @author viniciusk
 *
 */
public class RemessaDto {
	
	private String classe;
	
	private int qtdVolumes;
	
	private int qtdApensos;
	
	private String formaRecebimento;
	
	private String numeroSedex;
	
	
	public RemessaDto(String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex) {
		this.classe = classe;
		this.qtdVolumes = qtdVolumes;
		this.qtdApensos = qtdApensos;
		this.formaRecebimento = formaRecebimento;
		this.numeroSedex = numeroSedex;
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
	
	

}
