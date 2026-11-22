package org.Model;

/**
 * Situação do quotidiano para a qual o utilizador se quer vestir (UC6).
 * O nível de formalidade varia entre 1 (muito informal) e 5 (cerimónia).
 */
public class Ocasiao {

    private String designacao;
    private String descricao;
    private int nivelFormalidade;

    public static final int NIVEL_MIN = 1;
    public static final int NIVEL_MAX = 5;

    private static final String STRING_POR_OMISSAO = "a definir";
    private static final int INT_POR_OMISSAO = 0;

    public Ocasiao() {
        this.designacao = STRING_POR_OMISSAO;
        this.descricao = STRING_POR_OMISSAO;
        this.nivelFormalidade = INT_POR_OMISSAO;
    }

    public Ocasiao(String designacao, String descricao, int nivelFormalidade) {
        this.designacao = designacao;
        this.descricao = descricao;
        this.nivelFormalidade = nivelFormalidade;
    }

    public String getDesignacao() { return designacao; }
    public String getDescricao() { return descricao; }
    public int getNivelFormalidade() { return nivelFormalidade; }

    public void setDesignacao(String designacao) { this.designacao = designacao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setNivelFormalidade(int nivelFormalidade) { this.nivelFormalidade = nivelFormalidade; }

    /**
     * Validação local: dados obrigatórios e nível de formalidade entre 1 e 5.
     */
    public boolean valida() {
        if (designacao == null || designacao.isBlank() || designacao.equals(STRING_POR_OMISSAO)) return false;
        if (descricao == null || descricao.isBlank() || descricao.equals(STRING_POR_OMISSAO)) return false;
        return nivelFormalidade >= NIVEL_MIN && nivelFormalidade <= NIVEL_MAX;
    }

    @Override
    public String toString() {
        return designacao + " - " + descricao + " (formalidade " + nivelFormalidade + ")";
    }
}
