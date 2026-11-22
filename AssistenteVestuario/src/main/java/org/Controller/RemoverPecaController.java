package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.GuardaRoupa;
import org.Model.PecaVestuario;
import org.Model.Utilizador;

import java.util.List;

/**
 * Controller do UC10 - Remover Peça de Vestuário.
 */
public class RemoverPecaController {

    private final Utilizador utilizador;
    private final GuardaRoupa guardaRoupa;
    private PecaVestuario peca;

    public RemoverPecaController(AssistenteVestuario av) {
        this.utilizador = av.getUtilizadorAtual();
        this.guardaRoupa = utilizador.getGuardaRoupa();
    }

    public List<PecaVestuario> getListaPecas() {
        return guardaRoupa.getListaPecas();
    }

    /**
     * @return os dados da peça selecionada
     */
    public String selecionaPeca(PecaVestuario peca) {
        this.peca = peca;
        return peca.toStringDetalhado();
    }

    /**
     * Extensão 4a: número de combinações do utilizador que incluem a peça.
     */
    public int getNumCombinacoesComPeca() {
        return utilizador.getNumCombinacoesComPeca(peca);
    }

    public boolean removePeca() {
        return guardaRoupa.removePeca(peca);
    }
}
