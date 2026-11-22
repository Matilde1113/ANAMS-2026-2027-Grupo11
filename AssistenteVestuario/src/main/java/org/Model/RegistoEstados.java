package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda os estados de peça.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoEstados {

    private final List<EstadoPeca> listaEstados;

    public RegistoEstados() {
        this.listaEstados = new ArrayList<>();
    }

    /**
     * Cria um novo estado de peça (Creator).
     */
    public EstadoPeca novoEstado(String designacao, String descricao, boolean disponivel) {
        return new EstadoPeca(designacao, descricao, disponivel);
    }

    public boolean registaEstado(EstadoPeca e) {
        if (!validaEstado(e)) return false;
        return addEstado(e);
    }

    /**
     * Validação global: dados válidos e designação única.
     */
    public boolean validaEstado(EstadoPeca e) {
        if (e == null || !e.valida()) return false;
        return getEstado(e.getDesignacao()) == null;
    }

    private boolean addEstado(EstadoPeca e) {
        return listaEstados.add(e);
    }

    public List<EstadoPeca> getListaEstados() {
        return new ArrayList<>(listaEstados);
    }

    public EstadoPeca getEstado(String designacao) {
        for (EstadoPeca e : listaEstados) {
            if (e.getDesignacao().equalsIgnoreCase(designacao)) return e;
        }
        return null;
    }
}
