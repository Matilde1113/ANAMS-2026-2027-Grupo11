package org.Model;

/**
 * Tamanho numérico (ex.: 36, 38, 40; no calçado 35 a 46).
 */
public class TamanhoNumerico extends Tamanho {

    public static final int VALOR_MIN = 34;
    public static final int VALOR_MAX = 48;

    private int valor;

    public TamanhoNumerico(int valor) {
        super(String.valueOf(valor));
        this.valor = valor;
    }

    public int getValor() { return valor; }

    @Override
    public boolean valida() {
        return valor >= VALOR_MIN && valor <= VALOR_MAX;
    }

    @Override
    public String getTipo() {
        return "Numérico";
    }
}
