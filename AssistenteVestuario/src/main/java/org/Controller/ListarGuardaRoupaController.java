package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.CategoriaPeca;
import org.Model.GuardaRoupa;
import org.Model.PecaVestuario;

import java.util.List;

/**
 * Controller do UC8 - Listar Guarda-Roupa.
 */
public class ListarGuardaRoupaController {

    private final AssistenteVestuario av;
    private final GuardaRoupa guardaRoupa;

    public ListarGuardaRoupaController(AssistenteVestuario av) {
        this.av = av;
        this.guardaRoupa = av.getUtilizadorAtual().getGuardaRoupa();
    }

    public List<CategoriaPeca> getListaCategorias() {
        return av.getRegistoCategorias().getListaCategorias();
    }

    /**
     * @param categoria categoria escolhida (null = todas)
     */
    public List<PecaVestuario> getListaPecas(CategoriaPeca categoria) {
        return guardaRoupa.getPecasPorCategoria(categoria);
    }

    public String getDetalhesPeca(PecaVestuario peca) {
        return peca.toStringDetalhado();
    }
}
