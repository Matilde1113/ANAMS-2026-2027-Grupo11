package org.Model;

import org.Utils.Utils;

/**
 * Sugestão (recomendação de outfit) gerada pelo sistema para um contexto.
 * Começa pendente, pode ser aceite ou rejeitada (UC14) e, se aceite, avaliada (UC12).
 */
public class Sugestao {

    public static final String[] MOTIVOS_REJEICAO = {
            "Não gosto", "Demasiado formal", "Demasiado informal", "Não adequado ao tempo", "Outro"
    };

    private static int contador = 0;

    private final int codigo;
    private final String data;
    private final Combinacao combinacao;
    private final Contexto contexto;
    private EstadoSugestao estado;
    private String motivoRejeicao;
    private Avaliacao avaliacao;

    public Sugestao(Combinacao combinacao, Contexto contexto) {
        this.codigo = ++contador;
        this.data = Utils.getDataHoje();
        this.combinacao = combinacao;
        this.contexto = contexto;
        this.estado = EstadoSugestao.PENDENTE;
        this.motivoRejeicao = null;
        this.avaliacao = null;
    }

    public int getCodigo() { return codigo; }
    public String getData() { return data; }
    public Combinacao getCombinacao() { return combinacao; }
    public Contexto getContexto() { return contexto; }
    public EstadoSugestao getEstado() { return estado; }
    public String getMotivoRejeicao() { return motivoRejeicao; }
    public Avaliacao getAvaliacao() { return avaliacao; }

    public boolean isPendente() {
        return estado == EstadoSugestao.PENDENTE;
    }

    /**
     * @return true se a sugestão foi aceite e ainda não tem avaliação
     */
    public boolean isPorAvaliar() {
        return estado == EstadoSugestao.ACEITE && avaliacao == null;
    }

    /**
     * Aceita a sugestão (só se estiver pendente).
     */
    public boolean aceitar() {
        if (!isPendente()) return false;
        this.estado = EstadoSugestao.ACEITE;
        return true;
    }

    /**
     * Rejeita a sugestão; o motivo é obrigatório (UC14 5a).
     */
    public boolean rejeitar(String motivo) {
        if (!isPendente()) return false;
        if (motivo == null || motivo.isBlank()) return false;
        this.estado = EstadoSugestao.REJEITADA;
        this.motivoRejeicao = motivo;
        return true;
    }

    /**
     * Cria a avaliação desta sugestão (Creator - UC12).
     */
    public Avaliacao novaAvaliacao(int classificacao, String comentario) {
        return new Avaliacao(classificacao, comentario);
    }

    /**
     * Guarda a avaliação se for válida e se a sugestão estiver por avaliar.
     */
    public boolean setAvaliacao(Avaliacao a) {
        if (a == null || !a.valida()) return false;
        if (!isPorAvaliar()) return false;
        this.avaliacao = a;
        return true;
    }

    /**
     * Linha curta para listas: data, ocasião, estado e classificação.
     */
    @Override
    public String toString() {
        return "Sugestão " + codigo + " | " + (contexto != null ? contexto.getData() : data)
                + " | " + (contexto != null && contexto.getOcasiao() != null ? contexto.getOcasiao().getDesignacao() : "-")
                + " | " + estado
                + " | " + (avaliacao != null ? avaliacao.getClassificacao() + "/5" : "sem avaliação");
    }

    /**
     * Detalhes completos: peças, contexto e avaliação (UC15).
     */
    public String toStringDetalhado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sugestão ").append(codigo).append(" (").append(data).append(")\n");
        sb.append("Estado: ").append(estado).append("\n");
        if (estado == EstadoSugestao.REJEITADA) {
            sb.append("Motivo da rejeição: ").append(motivoRejeicao).append("\n");
        }
        sb.append("Contexto: ").append(contexto).append("\n");
        sb.append(combinacao);
        sb.append("Avaliação: ").append(avaliacao != null ? avaliacao : "sem avaliação").append("\n");
        return sb.toString();
    }
}
