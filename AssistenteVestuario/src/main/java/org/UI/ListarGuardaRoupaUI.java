package org.UI;

import org.Controller.ListarGuardaRoupaController;
import org.Model.AssistenteVestuario;
import org.Model.CategoriaPeca;
import org.Model.PecaVestuario;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC8 - Listar Guarda-Roupa.
 */
public class ListarGuardaRoupaUI {

    private final ListarGuardaRoupaController controller;

    public ListarGuardaRoupaUI(AssistenteVestuario av) {
        this.controller = new ListarGuardaRoupaController(av);
    }

    public void run() {
        System.out.println("\n--- Listar Guarda-Roupa ---");

        do {
            List<CategoriaPeca> categorias = controller.getListaCategorias();

            System.out.println("\nSelecione a categoria:");
            System.out.println("1. Todas as categorias");
            for (int i = 0; i < categorias.size(); i++) {
                System.out.println((i + 2) + ". " + categorias.get(i));
            }
            System.out.println("\n0 - Cancelar");

            int opcao;
            do {
                opcao = Utils.IntFromConsole("Introduza opção: ");
            } while (opcao < 0 || opcao > categorias.size() + 1);

            if (opcao == 0) return;

            CategoriaPeca categoria = (opcao == 1) ? null : categorias.get(opcao - 2);
            List<PecaVestuario> pecas = controller.getListaPecas(categoria);

            if (pecas.isEmpty()) {
                System.out.println("O guarda-roupa não tem peças nesta categoria.");
                continue;
            }

            PecaVestuario peca = (PecaVestuario) Utils.apresentaESeleciona(pecas,
                    "\nPeças (" + pecas.size() + ") - selecione uma para ver os detalhes:");
            if (peca != null) {
                System.out.println("\n" + controller.getDetalhesPeca(peca));
            }
        } while (Utils.confirma("Pretende consultar outra categoria? (S/N)"));
    }
}
