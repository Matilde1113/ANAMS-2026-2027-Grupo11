package org.Model;

/**
 * Tamanho de uma peça de vestuário (UC17).
 * Classe abstrata: um tamanho é numérico ou alfabético (herança/polimorfismo).
 */
public abstract class Tamanho {

    private String designacao;

    private static final String STRING_POR_OMISSAO = "a definir";

    public Tamanho() {
        this.designacao = STRING_POR_OMISSAO;
    }

    public Tamanho(String designacao) {
        this.designacao = designacao;
    }

    public String getDesignacao() { return designacao; }

    public void setDesignacao(String designacao) { this.designacao = designacao; }

    /**
     * Cada tipo de tamanho tem as suas regras de validação.
     */
    public abstract boolean valida();

    /**
     * @return "Numérico" ou "Alfabético"
     */
    public abstract String getTipo();

    @Override
    public String toString() {
        return designacao + " (" + getTipo() + ")";
    }
}
