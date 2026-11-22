package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda as partes do corpo.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoPartesCorpo {

    private final List<ParteCorpo> listaPartesCorpo;

    public RegistoPartesCorpo() {
        this.listaPartesCorpo = new ArrayList<>();
    }

    /**
     * Cria uma nova ParteCorpo (Creator).
     */
    public ParteCorpo novaParteCorpo(String designacao, String descricao) {
        return new ParteCorpo(designacao, descricao);
    }

    /**
     * Regista a ParteCorpo se for válida local e globalmente.
     */
    public boolean registaParteCorpo(ParteCorpo obj) {
        if (!validaParteCorpo(obj)) return false;
        return addParteCorpo(obj);
    }

    /**
     * Validação global: dados válidos e designação única.
     */
    public boolean validaParteCorpo(ParteCorpo obj) {
        if (obj == null || !obj.valida()) return false;
        return getParteCorpo(obj.getDesignacao()) == null;
    }

    private boolean addParteCorpo(ParteCorpo obj) {
        return listaPartesCorpo.add(obj);
    }

    public List<ParteCorpo> getListaPartesCorpo() {
        return new ArrayList<>(listaPartesCorpo);
    }

    /**
     * Procura pela designação (ignora maiúsculas/minúsculas).
     */
    public ParteCorpo getParteCorpo(String designacao) {
        for (ParteCorpo obj : listaPartesCorpo) {
            if (obj.getDesignacao().equalsIgnoreCase(designacao)) return obj;
        }
        return null;
    }
}
