package org.Model;

import org.Utils.Utils;

/**
 * Avaliação de uma sugestão: classificação de 1 a 5 e comentário (ambos obrigatórios).
 */
public class Avaliacao {

    public static final int CLASSIFICACAO_MIN = 1;
    public static final int CLASSIFICACAO_MAX = 5;

    private int classificacao;
    private String comentario;
    private final String data;

    public Avaliacao(int classificacao, String comentario) {
        this.classificacao = classificacao;
        this.comentario = comentario;
        this.data = Utils.getDataHoje();
    }

    public int getClassificacao() { return classificacao; }
    public String getComentario() { return comentario; }
    public String getData() { return data; }

    /**
     * Validação local: classificação entre 1 e 5 e comentário obrigatório.
     */
    public boolean valida() {
        if (classificacao < CLASSIFICACAO_MIN || classificacao > CLASSIFICACAO_MAX) return false;
        return comentario != null && !comentario.isBlank();
    }

    @Override
    public String toString() {
        return classificacao + "/5 - \"" + comentario + "\" (" + data + ")";
    }
}
