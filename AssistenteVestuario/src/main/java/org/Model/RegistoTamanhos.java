package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda os tamanhos.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoTamanhos {

    private final List<Tamanho> listaTamanhos;

    public RegistoTamanhos() {
        this.listaTamanhos = new ArrayList<>();
    }

    /**
     * Cria um tamanho numérico (Creator).
     */
    public Tamanho novoTamanhoNumerico(int valor) {
        return new TamanhoNumerico(valor);
    }

    /**
     * Cria um tamanho alfabético (Creator).
     */
    public Tamanho novoTamanhoAlfabetico(String designacao) {
        return new TamanhoAlfabetico(designacao);
    }

    public boolean registaTamanho(Tamanho t) {
        if (!validaTamanho(t)) return false;
        return addTamanho(t);
    }

    /**
     * Validação global: dados válidos e par (designação, tipo) único.
     */
    public boolean validaTamanho(Tamanho t) {
        if (t == null || !t.valida()) return false;
        for (Tamanho existente : listaTamanhos) {
            if (existente.getDesignacao().equalsIgnoreCase(t.getDesignacao())
                    && existente.getTipo().equals(t.getTipo())) {
                return false;
            }
        }
        return true;
    }

    private boolean addTamanho(Tamanho t) {
        return listaTamanhos.add(t);
    }

    public List<Tamanho> getListaTamanhos() {
        return new ArrayList<>(listaTamanhos);
    }

    public Tamanho getTamanho(String designacao) {
        for (Tamanho t : listaTamanhos) {
            if (t.getDesignacao().equalsIgnoreCase(designacao)) return t;
        }
        return null;
    }
}
