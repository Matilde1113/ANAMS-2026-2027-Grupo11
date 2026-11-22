package org.Model;

/**
 * Matéria de que uma peça é feita (ex.: algodão, lã, ganga).
 * Definido pelo Administrador (UC4).
 */
public class Material {

    private String designacao;
    private String descricao;

    private static final String STRING_POR_OMISSAO = "a definir";

    public Material() {
        this.designacao = STRING_POR_OMISSAO;
        this.descricao = STRING_POR_OMISSAO;
    }

    public Material(String designacao, String descricao) {
        this.designacao = designacao;
        this.descricao = descricao;
    }

    public String getDesignacao() { return designacao; }
    public String getDescricao() { return descricao; }

    public void setDesignacao(String designacao) { this.designacao = designacao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    /**
     * Validação local: designação e descrição são obrigatórias.
     */
    public boolean valida() {
        if (designacao == null || designacao.isBlank() || designacao.equals(STRING_POR_OMISSAO)) return false;
        if (descricao == null || descricao.isBlank() || descricao.equals(STRING_POR_OMISSAO)) return false;
        return true;
    }

    @Override
    public String toString() {
        return designacao + " - " + descricao;
    }
}
