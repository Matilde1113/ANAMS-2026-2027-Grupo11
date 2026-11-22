package org.UI;

import org.Controller.EspecificarCorController;
import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * UI do UC3 - Especificar Cor.
 */
public class EspecificarCorUI {

    private final EspecificarCorController controller;

    public EspecificarCorUI(AssistenteVestuario av) {
        this.controller = new EspecificarCorController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Cor ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getCorAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaCor()) {
                System.out.println("Cor registada com sucesso.");
            } else {
                System.out.println("Não foi possível registar a cor.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Designação (ex.: Azul): ");
            String codigo = Utils.readLineFromConsole("Código hexadecimal (ex.: #1E3A8A): ");

            if (designacao.isBlank() || codigo.isBlank()) {
                System.out.println("Dados em falta: a designação e o código são obrigatórios.");
            } else if (controller.novaCor(designacao, codigo)) {
                return true;
            } else if (!controller.dadosValidos()) {
                System.out.println("Código inválido: use o formato #RRGGBB.");
            } else {
                System.out.println("Já existe uma cor com essa designação ou código.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
