package org.Model;

/**
 * Tamanho alfabético (XS, S, M, L, XL, XXL).
 */
public class TamanhoAlfabetico extends Tamanho {

    public static final String[] VALORES = {"XS", "S", "M", "L", "XL", "XXL"};

    public TamanhoAlfabetico(String designacao) {
        super(designacao == null ? null : designacao.toUpperCase());
    }

    @Override
    public boolean valida() {
        for (String v : VALORES) {
            if (v.equals(getDesignacao())) return true;
        }
        return false;
    }

    @Override
    public String getTipo() {
        return "Alfabético";
    }
}
