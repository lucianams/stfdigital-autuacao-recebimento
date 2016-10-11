package br.jus.stf.autuacao.recebimento.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Tomas.Godoi
 * 
 * @since 21.06.2016
 *
 */
@ApiModel(description = "DTO que representa uma remessa.")
public class RemessaDto {

    @ApiModelProperty(value = "O id do protocolo.")
    private Long protocolo;

    @ApiModelProperty(value = "Classe da remessa.")
    private String classe;

    @ApiModelProperty(value = "Quantidade de volumes.")
    private int qtdVolumes;

    @ApiModelProperty(value = "Quantidade de apensos.")
    private int qtdApensos;

    @ApiModelProperty(value = "Forma de recebimento.")
    private String formaRecebimento;

    @ApiModelProperty(value = "Número do sedex.")
    private String numeroSedex;

    @ApiModelProperty(value = "Grau de sigilo da remessa.")
    private String sigilo;

    @ApiModelProperty(value = "Número da remessa.")
    private String numero;

    /**
     * @param protocolo O id do protocolo.
     * @param classe Classe da remessa.
     * @param qtdVolumes Quantidade de volumes.
     * @param qtdApensos Quantidade de apensos.
     * @param formaRecebimento Forma de recebimento.
     * @param numeroSedex Número do sedex.
     * @param sigilo Grau de sigilo da remessa.
     * @param numero Número da remessa.
     */
    public RemessaDto(Long protocolo, String classe, int qtdVolumes, int qtdApensos, String formaRecebimento,
            String numeroSedex, String sigilo, String numero) {
        this(classe, qtdVolumes, qtdApensos, formaRecebimento, numeroSedex, sigilo, numero);
        this.protocolo = protocolo;
    }

    /**
     * @param classe Classe da remessa.
     * @param qtdVolumes Quantidade de volumes.
     * @param qtdApensos Quantidade de apensos.
     * @param formaRecebimento Forma de recebimento.
     * @param numeroSedex Número do sedex.
     * @param sigilo Grau de sigilo da remessa.
     * @param numero Número da remessa.
     */
    public RemessaDto(String classe, int qtdVolumes, int qtdApensos, String formaRecebimento, String numeroSedex,
            String sigilo, String numero) {
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
