package br.jus.stf.autuacao.recebimento.domain.model;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 11.04.2016
 */
public enum FormaRecebimento {

    BALCAO("Balcão", false),
    E_MAIL("E-mail", false),
    FAX("Fax", false),
    MALOTE("Malote", false),
    SEDEX("Sedex", true);

    private String descricao;

    private boolean exigeNumeracao;

    private FormaRecebimento(String descricao, boolean exigeNumeracao) {
        this.descricao = descricao;
        this.exigeNumeracao = exigeNumeracao;
    }

    /**
     * @return Descrição da forma de recebimento.
     */
    public String descricao() {
        return descricao;
    }

    /**
     * @return Indica se a numeração é ou não exigida para forma de recebimento.
     */
    public boolean exigeNumeracao() {
        return exigeNumeracao;
    }

}
