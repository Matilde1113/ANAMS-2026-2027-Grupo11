package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.Estilo;
import org.Model.RegistoEstilos;

/**
 * Controller do UC2 - Especificar Estilo.
 * Coordena a UI com o RegistoEstilos (padrão GRASP Controller).
 */
public class EspecificarEstiloController {

    private final RegistoEstilos registo;
    private Estilo estilo;

    public EspecificarEstiloController(AssistenteVestuario av) {
        this.registo = av.getRegistoEstilos();
    }

    /**
     * Cria um novo estilo com os dados introduzidos.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novoEstilo(String designacao, String descricao) {
        this.estilo = registo.novoEstilo(designacao, descricao);
        return registo.validaEstilo(this.estilo);
    }

    public boolean existeEstilo(String designacao) {
        return registo.getEstilo(designacao) != null;
    }

    public String getEstiloAsString() {
        return estilo != null ? estilo.toString() : "";
    }

    public boolean registaEstilo() {
        return registo.registaEstilo(this.estilo);
    }
}
