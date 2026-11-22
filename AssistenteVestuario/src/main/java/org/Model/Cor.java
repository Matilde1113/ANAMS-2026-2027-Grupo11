package org.Model;

/**
 * Cor definida pelo Administrador (UC3), com designação e código hexadecimal.
 * Uma peça pode ter uma ou mais cores.
 */
public class Cor {

    private String designacao;
    private String codigoHex;

    private static final String STRING_POR_OMISSAO = "a definir";

    public Cor() {
        this.designacao = STRING_POR_OMISSAO;
        this.codigoHex = STRING_POR_OMISSAO;
    }

    public Cor(String designacao, String codigoHex) {
        this.designacao = designacao;
        this.codigoHex = codigoHex;
    }

    public String getDesignacao() { return designacao; }
    public String getCodigoHex() { return codigoHex; }

    public void setDesignacao(String designacao) { this.designacao = designacao; }
    public void setCodigoHex(String codigoHex) { this.codigoHex = codigoHex; }

    /**
     * Validação local: designação obrigatória e código no formato #RRGGBB.
     */
    public boolean valida() {
        if (designacao == null || designacao.isBlank() || designacao.equals(STRING_POR_OMISSAO)) return false;
        if (codigoHex == null || !codigoHex.matches("#[0-9A-Fa-f]{6}")) return false;
        return true;
    }

    @Override
    public String toString() {
        return designacao + " (" + codigoHex + ")";
    }
}
