package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda os materiais.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoMateriais {

    private final List<Material> listaMateriais;

    public RegistoMateriais() {
        this.listaMateriais = new ArrayList<>();
    }

    /**
     * Cria um novo Material (Creator).
     */
    public Material novoMaterial(String designacao, String descricao) {
        return new Material(designacao, descricao);
    }

    /**
     * Regista o Material se for válido local e globalmente.
     */
    public boolean registaMaterial(Material obj) {
        if (!validaMaterial(obj)) return false;
        return addMaterial(obj);
    }

    /**
     * Validação global: dados válidos e designação única.
     */
    public boolean validaMaterial(Material obj) {
        if (obj == null || !obj.valida()) return false;
        return getMaterial(obj.getDesignacao()) == null;
    }

    private boolean addMaterial(Material obj) {
        return listaMateriais.add(obj);
    }

    public List<Material> getListaMateriais() {
        return new ArrayList<>(listaMateriais);
    }

    /**
     * Procura pela designação (ignora maiúsculas/minúsculas).
     */
    public Material getMaterial(String designacao) {
        for (Material obj : listaMateriais) {
            if (obj.getDesignacao().equalsIgnoreCase(designacao)) return obj;
        }
        return null;
    }
}
