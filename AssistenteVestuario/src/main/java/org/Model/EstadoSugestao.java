package org.Model;

/**
 * Estados possíveis de uma sugestão (recomendação de outfit).
 */
public enum EstadoSugestao {
    PENDENTE("Pendente"),
    ACEITE("Aceite"),
    REJEITADA("Rejeitada");

    private final String descricao;

    EstadoSugestao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
