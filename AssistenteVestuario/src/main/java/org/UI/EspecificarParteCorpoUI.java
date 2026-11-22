package org.UI;

import org.Controller.EspecificarParteCorpoController;
import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * UI do UC16 - Especificar Parte do Corpo.
 */
public class EspecificarParteCorpoUI {

    private final EspecificarParteCorpoController controller;

    public EspecificarParteCorpoUI(AssistenteVestuario av) {
        this.controller = new EspecificarParteCorpoController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Parte do Corpo ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getParteCorpoAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaParteCorpo()) {
                System.out.println("Parte do corpo registada com sucesso.");
            } else {
                System.out.println("Não foi possível registar a parte do corpo.");
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
            String designacao = Utils.readLineFromConsole("Designação (ex.: Tronco): ");
            String descricao = Utils.readLineFromConsole("Descrição: ");

            if (designacao.isBlank() || descricao.isBlank()) {
                System.out.println("Dados em falta: a designação e a descrição são obrigatórias.");
            } else if (controller.existeParteCorpo(designacao)) {
                System.out.println("Já existe uma parte do corpo com essa designação.");
            } else if (controller.novaParteCorpo(designacao, descricao)) {
                return true;
            } else {
                System.out.println("Dados inválidos.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
