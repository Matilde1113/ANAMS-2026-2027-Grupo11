package org.UI;

import org.Controller.EspecificarTamanhoController;
import org.Model.AssistenteVestuario;
import org.Model.TamanhoAlfabetico;
import org.Model.TamanhoNumerico;
import org.Utils.Utils;

/**
 * UI do UC17 - Especificar Tamanho.
 */
public class EspecificarTamanhoUI {

    private final EspecificarTamanhoController controller;

    public EspecificarTamanhoUI(AssistenteVestuario av) {
        this.controller = new EspecificarTamanhoController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Tamanho ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getTamanhoAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaTamanho()) {
                System.out.println("Tamanho registado com sucesso.");
            } else {
                System.out.println("Não foi possível registar o tamanho.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private boolean introduzirDados() {
        do {
            System.out.println("Tipo de tamanho:");
            System.out.println("1. Numérico (" + TamanhoNumerico.VALOR_MIN + " a " + TamanhoNumerico.VALOR_MAX + ")");
            System.out.println("2. Alfabético (" + String.join(", ", TamanhoAlfabetico.VALORES) + ")");
            String tipo = Utils.readLineFromConsole("Opção:");

            boolean ok;
            if (tipo.equals("1")) {
                ok = controller.novoTamanhoNumerico(Utils.IntFromConsole("Valor: "));
            } else if (tipo.equals("2")) {
                ok = controller.novoTamanhoAlfabetico(Utils.readLineFromConsole("Designação: "));
            } else {
                System.out.println("Tipo inválido.");
                continue;
            }

            if (ok) return true;

            if (!controller.tamanhoValido()) {
                System.out.println("Tamanho fora dos valores admitidos.");
            } else {
                System.out.println("Esse tamanho já está registado.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
