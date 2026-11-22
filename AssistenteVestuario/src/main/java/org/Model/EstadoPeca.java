package org.Model;

/**
 * Estado de uma peça definido pelo Administrador (UC18),
 * ex.: disponível, em lavagem, emprestada, indisponível.
 * Apenas peças num estado "disponivel" entram nas sugestões.
 */
public class EstadoPeca {

    private String designacao;
    private String descricao;
    private boolean disponivel;

    private static final String STRING_POR_OMISSAO = "a definir";

    public EstadoPeca() {
        this.designacao = STRING_POR_OMISSAO;
        this.descricao = STRING_POR_OMISSAO;
        this.disponivel = false;
    }

    public EstadoPeca(String designacao, String descricao, boolean disponivel) {
        this.designacao = designacao;
        this.descricao = descricao;
        this.disponivel = disponivel;
    }

    public String getDesignacao() { return designacao; }
    public String getDescricao() { return descricao; }
    public boolean isDisponivel() { return disponivel; }

    public void setDesignacao(String designacao) { this.designacao = designacao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public boolean valida() {
        if (designacao == null || designacao.isBlank() || designacao.equals(STRING_POR_OMISSAO)) return false;
        if (descricao == null || descricao.isBlank() || descricao.equals(STRING_POR_OMISSAO)) return false;
        return true;
    }

    @Override
    public String toString() {
        return designacao + " - " + descricao + (disponivel ? " [disponível]" : " [não disponível]");
    }
}
