package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.Ocasiao;
import org.Model.RegistoOcasioes;

/**
 * Controller do UC6 - Especificar Ocasião.
 */
public class EspecificarOcasiaoController {

    private final RegistoOcasioes registo;
    private Ocasiao ocasiao;

    public EspecificarOcasiaoController(AssistenteVestuario av) {
        this.registo = av.getRegistoOcasioes();
    }

    /**
     * Cria uma nova ocasião.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novaOcasiao(String designacao, String descricao, int nivelFormalidade) {
        this.ocasiao = registo.novaOcasiao(designacao, descricao, nivelFormalidade);
        return registo.validaOcasiao(this.ocasiao);
    }

    public boolean existeOcasiao(String designacao) {
        return registo.getOcasiao(designacao) != null;
    }

    public String getOcasiaoAsString() {
        return ocasiao != null ? ocasiao.toString() : "";
    }

    public boolean registaOcasiao() {
        return registo.registaOcasiao(this.ocasiao);
    }
}
