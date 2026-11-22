package org.UI;

import org.Controller.EspecificarOcasiaoController;
import org.Model.AssistenteVestuario;
import org.Model.Ocasiao;
import org.Utils.Utils;

/**
 * UI do UC6 - Especificar Ocasião.
 */
public class EspecificarOcasiaoUI {

    private final EspecificarOcasiaoController controller;

    public EspecificarOcasiaoUI(AssistenteVestuario av) {
        this.controller = new EspecificarOcasiaoController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Ocasião ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getOcasiaoAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaOcasiao()) {
                System.out.println("Ocasião registada com sucesso.");
            } else {
                System.out.println("Não foi possível registar a ocasião.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Designação (ex.: Jantar): ");
            String descricao = Utils.readLineFromConsole("Descrição: ");
            int nivel = Utils.IntFromConsole("Nível de formalidade (" + Ocasiao.NIVEL_MIN
                    + " - muito informal a " + Ocasiao.NIVEL_MAX + " - cerimónia): ");

            if (designacao.isBlank() || descricao.isBlank()) {
                System.out.println("Dados em falta: todos os dados são obrigatórios.");
            } else if (nivel < Ocasiao.NIVEL_MIN || nivel > Ocasiao.NIVEL_MAX) {
                System.out.println("Nível de formalidade inválido.");
            } else if (controller.existeOcasiao(designacao)) {
                System.out.println("Já existe uma ocasião com essa designação.");
            } else if (controller.novaOcasiao(designacao, descricao, nivel)) {
                return true;
            } else {
                System.out.println("Dados inválidos.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
