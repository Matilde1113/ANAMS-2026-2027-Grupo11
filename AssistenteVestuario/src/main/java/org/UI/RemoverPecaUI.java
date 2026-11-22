package org.UI;

import org.Controller.RemoverPecaController;
import org.Model.AssistenteVestuario;
import org.Model.PecaVestuario;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC10 - Remover Peça de Vestuário.
 */
public class RemoverPecaUI {

    private final RemoverPecaController controller;

    public RemoverPecaUI(AssistenteVestuario av) {
        this.controller = new RemoverPecaController(av);
    }

    public void run() {
        System.out.println("\n--- Remover Peça de Vestuário ---");

        List<PecaVestuario> pecas = controller.getListaPecas();
        if (pecas.isEmpty()) {
            System.out.println("O guarda-roupa não tem peças.");
            return;
        }

        PecaVestuario peca = (PecaVestuario) Utils.apresentaESeleciona(pecas, "\nSelecione a peça a remover:");
        if (peca == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\n" + controller.selecionaPeca(peca));

        int n = controller.getNumCombinacoesComPeca();
        if (n > 0) {
            System.out.println("ATENÇÃO: esta peça faz parte de " + n + " combinação(ões) suas.");
        }
        System.out.println("Dica: em vez de remover, pode alterar o estado da peça (UC9).");

        if (Utils.confirma("Confirma a remoção? (S/N)")) {
            if (controller.removePeca()) {
                System.out.println("Peça removida com sucesso.");
            } else {
                System.out.println("Não foi possível remover a peça.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
