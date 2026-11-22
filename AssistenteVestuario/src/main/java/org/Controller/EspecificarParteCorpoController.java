package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.ParteCorpo;
import org.Model.RegistoPartesCorpo;

/**
 * Controller do UC16 - Especificar Parte do Corpo.
 * Coordena a UI com o RegistoPartesCorpo (padrão GRASP Controller).
 */
public class EspecificarParteCorpoController {

    private final RegistoPartesCorpo registo;
    private ParteCorpo parteCorpo;

    public EspecificarParteCorpoController(AssistenteVestuario av) {
        this.registo = av.getRegistoPartesCorpo();
    }

    /**
     * Cria uma nova parte do corpo com os dados introduzidos.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novaParteCorpo(String designacao, String descricao) {
        this.parteCorpo = registo.novaParteCorpo(designacao, descricao);
        return registo.validaParteCorpo(this.parteCorpo);
    }

    public boolean existeParteCorpo(String designacao) {
        return registo.getParteCorpo(designacao) != null;
    }

    public String getParteCorpoAsString() {
        return parteCorpo != null ? parteCorpo.toString() : "";
    }

    public boolean registaParteCorpo() {
        return registo.registaParteCorpo(this.parteCorpo);
    }
}
