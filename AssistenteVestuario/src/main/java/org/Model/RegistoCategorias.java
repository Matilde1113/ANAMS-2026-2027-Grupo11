package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda as categorias.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoCategorias {

    private final List<CategoriaPeca> listaCategorias;

    public RegistoCategorias() {
        this.listaCategorias = new ArrayList<>();
    }

    /**
     * Cria uma nova CategoriaPeca (Creator).
     */
    public CategoriaPeca novaCategoriaPeca(String designacao, String descricao) {
        return new CategoriaPeca(designacao, descricao);
    }

    /**
     * Regista a CategoriaPeca se for válida local e globalmente.
     */
    public boolean registaCategoriaPeca(CategoriaPeca obj) {
        if (!validaCategoriaPeca(obj)) return false;
        return addCategoriaPeca(obj);
    }

    /**
     * Validação global: dados válidos e designação única.
     */
    public boolean validaCategoriaPeca(CategoriaPeca obj) {
        if (obj == null || !obj.valida()) return false;
        return getCategoriaPeca(obj.getDesignacao()) == null;
    }

    private boolean addCategoriaPeca(CategoriaPeca obj) {
        return listaCategorias.add(obj);
    }

    public List<CategoriaPeca> getListaCategorias() {
        return new ArrayList<>(listaCategorias);
    }

    /**
     * Procura pela designação (ignora maiúsculas/minúsculas).
     */
    public CategoriaPeca getCategoriaPeca(String designacao) {
        for (CategoriaPeca obj : listaCategorias) {
            if (obj.getDesignacao().equalsIgnoreCase(designacao)) return obj;
        }
        return null;
    }
}
