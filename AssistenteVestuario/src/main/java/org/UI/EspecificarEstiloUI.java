package org.UI;

import org.Controller.EspecificarEstiloController;
import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * UI do UC2 - Especificar Estilo.
 */
public class EspecificarEstiloUI {

    private final EspecificarEstiloController controller;

    public EspecificarEstiloUI(AssistenteVestuario av) {
        this.controller = new EspecificarEstiloController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Estilo ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getEstiloAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaEstilo()) {
                System.out.println("Estilo registado com sucesso.");
            } else {
                System.out.println("Não foi possível registar o estilo.");
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
            String designacao = Utils.readLineFromConsole("Designação (ex.: Clássico): ");
            String descricao = Utils.readLineFromConsole("Descrição: ");

            if (designacao.isBlank() || descricao.isBlank()) {
                System.out.println("Dados em falta: a designação e a descrição são obrigatórias.");
            } else if (controller.existeEstilo(designacao)) {
                System.out.println("Já existe um estilo com essa designação.");
            } else if (controller.novoEstilo(designacao, descricao)) {
                return true;
            } else {
                System.out.println("Dados inválidos.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
