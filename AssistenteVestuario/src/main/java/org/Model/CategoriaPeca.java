package org.Model;

/**
 * Tipo de peça de vestuário (ex.: calça, camisa, calçado).
 * Definido pelo Administrador (UC5).
 */
public class CategoriaPeca {

    private String designacao;
    private String descricao;

    private static final String STRING_POR_OMISSAO = "a definir";

    public CategoriaPeca() {
        this.designacao = STRING_POR_OMISSAO;
        this.descricao = STRING_POR_OMISSAO;
    }

    public CategoriaPeca(String designacao, String descricao) {
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
