package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.EstadoPeca;
import org.Model.RegistoEstados;

/**
 * Controller do UC18 - Especificar Estado da Peça.
 */
public class EspecificarEstadoPecaController {

    private final RegistoEstados registo;
    private EstadoPeca estado;

    public EspecificarEstadoPecaController(AssistenteVestuario av) {
        this.registo = av.getRegistoEstados();
    }

    /**
     * Cria um novo estado de peça.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novoEstado(String designacao, String descricao, boolean disponivel) {
        this.estado = registo.novoEstado(designacao, descricao, disponivel);
        return registo.validaEstado(this.estado);
    }

    public boolean existeEstado(String designacao) {
        return registo.getEstado(designacao) != null;
    }

    public String getEstadoAsString() {
        return estado != null ? estado.toString() : "";
    }

    public boolean registaEstado() {
        return registo.registaEstado(this.estado);
    }
}
