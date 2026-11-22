package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.CategoriaPeca;
import org.Model.RegistoCategorias;

/**
 * Controller do UC5 - Especificar Categoria de Peça.
 * Coordena a UI com o RegistoCategorias (padrão GRASP Controller).
 */
public class EspecificarCategoriaPecaController {

    private final RegistoCategorias registo;
    private CategoriaPeca categoriaPeca;

    public EspecificarCategoriaPecaController(AssistenteVestuario av) {
        this.registo = av.getRegistoCategorias();
    }

    /**
     * Cria uma nova categoria de peça com os dados introduzidos.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novaCategoriaPeca(String designacao, String descricao) {
        this.categoriaPeca = registo.novaCategoriaPeca(designacao, descricao);
        return registo.validaCategoriaPeca(this.categoriaPeca);
    }

    public boolean existeCategoriaPeca(String designacao) {
        return registo.getCategoriaPeca(designacao) != null;
    }

    public String getCategoriaPecaAsString() {
        return categoriaPeca != null ? categoriaPeca.toString() : "";
    }

    public boolean registaCategoriaPeca() {
        return registo.registaCategoriaPeca(this.categoriaPeca);
    }
}
