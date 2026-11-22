package org.UI;

import org.Controller.EspecificarCategoriaPecaController;
import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * UI do UC5 - Especificar Categoria de Peça.
 */
public class EspecificarCategoriaPecaUI {

    private final EspecificarCategoriaPecaController controller;

    public EspecificarCategoriaPecaUI(AssistenteVestuario av) {
        this.controller = new EspecificarCategoriaPecaController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Categoria de Peça ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getCategoriaPecaAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaCategoriaPeca()) {
                System.out.println("Categoria de peça registada com sucesso.");
            } else {
                System.out.println("Não foi possível registar a categoria de peça.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Pede os dados até serem válidos ou até o Administrador desistir.
     *
     * @return true se os dados ficaram válidos
     */
    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Designação (ex.: Calça): ");
            String descricao = Utils.readLineFromConsole("Descrição: ");

            if (designacao.isBlank() || descricao.isBlank()) {
                System.out.println("Dados em falta: a designação e a descrição são obrigatórias.");
            } else if (controller.existeCategoriaPeca(designacao)) {
                System.out.println("Já existe uma categoria de peça com essa designação.");
            } else if (controller.novaCategoriaPeca(designacao, descricao)) {
                return true;
            } else {
                System.out.println("Dados inválidos.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
