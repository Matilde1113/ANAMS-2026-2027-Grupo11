package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente e guarda as ocasiões.
 * (Baixo Acoplamento / Alta Coesão - IT2)
 */
public class RegistoOcasioes {

    private final List<Ocasiao> listaOcasioes;

    public RegistoOcasioes() {
        this.listaOcasioes = new ArrayList<>();
    }

    /**
     * Cria uma nova ocasião (Creator).
     */
    public Ocasiao novaOcasiao(String designacao, String descricao, int nivelFormalidade) {
        return new Ocasiao(designacao, descricao, nivelFormalidade);
    }

    public boolean registaOcasiao(Ocasiao o) {
        if (!validaOcasiao(o)) return false;
        return addOcasiao(o);
    }

    /**
     * Validação global: dados válidos e designação única.
     */
    public boolean validaOcasiao(Ocasiao o) {
        if (o == null || !o.valida()) return false;
        return getOcasiao(o.getDesignacao()) == null;
    }

    private boolean addOcasiao(Ocasiao o) {
        return listaOcasioes.add(o);
    }

    public List<Ocasiao> getListaOcasioes() {
        return new ArrayList<>(listaOcasioes);
    }

    public Ocasiao getOcasiao(String designacao) {
        for (Ocasiao o : listaOcasioes) {
            if (o.getDesignacao().equalsIgnoreCase(designacao)) return o;
        }
        return null;
    }
}
