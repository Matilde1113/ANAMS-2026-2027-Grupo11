package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda as cores.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoCores {

    private final List<Cor> listaCores;

    public RegistoCores() {
        this.listaCores = new ArrayList<>();
    }

    /**
     * Cria uma nova cor (Creator).
     */
    public Cor novaCor(String designacao, String codigoHex) {
        return new Cor(designacao, codigoHex);
    }

    public boolean registaCor(Cor c) {
        if (!validaCor(c)) return false;
        return addCor(c);
    }

    /**
     * Validação global: designação e código hexadecimal únicos.
     */
    public boolean validaCor(Cor c) {
        if (c == null || !c.valida()) return false;
        for (Cor existente : listaCores) {
            if (existente.getDesignacao().equalsIgnoreCase(c.getDesignacao())) return false;
            if (existente.getCodigoHex().equalsIgnoreCase(c.getCodigoHex())) return false;
        }
        return true;
    }

    private boolean addCor(Cor c) {
        return listaCores.add(c);
    }

    public List<Cor> getListaCores() {
        return new ArrayList<>(listaCores);
    }

    public Cor getCor(String designacao) {
        for (Cor c : listaCores) {
            if (c.getDesignacao().equalsIgnoreCase(designacao)) return c;
        }
        return null;
    }
}
