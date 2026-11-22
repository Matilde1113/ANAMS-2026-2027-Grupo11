package org.UI;

import org.Controller.EspecificarEstadoPecaController;
import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * UI do UC18 - Especificar Estado da Peça.
 */
public class EspecificarEstadoPecaUI {

    private final EspecificarEstadoPecaController controller;

    public EspecificarEstadoPecaUI(AssistenteVestuario av) {
        this.controller = new EspecificarEstadoPecaController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Estado da Peça ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getEstadoAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaEstado()) {
                System.out.println("Estado registado com sucesso.");
            } else {
                System.out.println("Não foi possível registar o estado.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Designação (ex.: Em lavagem): ");
            String descricao = Utils.readLineFromConsole("Descrição: ");
            boolean disponivel = Utils.confirma("Neste estado a peça está disponível para usar? (S/N)");

            if (designacao.isBlank() || descricao.isBlank()) {
                System.out.println("Dados em falta: a designação e a descrição são obrigatórias.");
            } else if (controller.existeEstado(designacao)) {
                System.out.println("Já existe um estado com essa designação.");
            } else if (controller.novoEstado(designacao, descricao, disponivel)) {
                return true;
            } else {
                System.out.println("Dados inválidos.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
