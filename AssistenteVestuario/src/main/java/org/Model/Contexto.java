package org.Model;

import org.Utils.Utils;

/**
 * Dados que caracterizam um pedido de sugestão: ocasião, data, local
 * e condições atmosféricas (temperatura e condição do tempo).
 */
public class Contexto {

    public static final double TEMP_MIN = -30;
    public static final double TEMP_MAX = 50;

    private Ocasiao ocasiao;
    private String data;
    private String local;
    private double temperatura;
    private String condicaoTempo;

    private static final String STRING_POR_OMISSAO = "a definir";

    public Contexto(Ocasiao ocasiao) {
        this.ocasiao = ocasiao;
        this.data = STRING_POR_OMISSAO;
        this.local = STRING_POR_OMISSAO;
        this.temperatura = 0;
        this.condicaoTempo = STRING_POR_OMISSAO;
    }

    public void setDados(String data, String local, double temperatura, String condicaoTempo) {
        this.data = data;
        this.local = local;
        this.temperatura = temperatura;
        this.condicaoTempo = condicaoTempo;
    }

    public Ocasiao getOcasiao() { return ocasiao; }
    public String getData() { return data; }
    public String getLocal() { return local; }
    public double getTemperatura() { return temperatura; }
    public String getCondicaoTempo() { return condicaoTempo; }

    /**
     * Validação local: todos os dados do contexto são obrigatórios.
     */
    public boolean valida() {
        if (ocasiao == null) return false;
        if (!Utils.dataValida(data)) return false;
        if (local == null || local.isBlank() || local.equals(STRING_POR_OMISSAO)) return false;
        if (temperatura < TEMP_MIN || temperatura > TEMP_MAX) return false;
        if (condicaoTempo == null || condicaoTempo.isBlank() || condicaoTempo.equals(STRING_POR_OMISSAO)) return false;
        return true;
    }

    @Override
    public String toString() {
        return (ocasiao != null ? ocasiao.getDesignacao() : "-") + " | " + data + " | " + local
                + " | " + temperatura + "ºC, " + condicaoTempo;
    }
}
