package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda os estilos.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoEstilos {

    private final List<Estilo> listaEstilos;

    public RegistoEstilos() {
        this.listaEstilos = new ArrayList<>();
    }

    /**
     * Cria um novo Estilo (Creator).
     */
    public Estilo novoEstilo(String designacao, String descricao) {
        return new Estilo(designacao, descricao);
    }

    /**
     * Regista o Estilo se for válido local e globalmente.
     */
    public boolean registaEstilo(Estilo obj) {
        if (!validaEstilo(obj)) return false;
        return addEstilo(obj);
    }

    /**
     * Validação global: dados válidos e designação única.
     */
    public boolean validaEstilo(Estilo obj) {
        if (obj == null || !obj.valida()) return false;
        return getEstilo(obj.getDesignacao()) == null;
    }

    private boolean addEstilo(Estilo obj) {
        return listaEstilos.add(obj);
    }

    public List<Estilo> getListaEstilos() {
        return new ArrayList<>(listaEstilos);
    }

    /**
     * Procura pela designação (ignora maiúsculas/minúsculas).
     */
    public Estilo getEstilo(String designacao) {
        for (Estilo obj : listaEstilos) {
            if (obj.getDesignacao().equalsIgnoreCase(designacao)) return obj;
        }
        return null;
    }
}
