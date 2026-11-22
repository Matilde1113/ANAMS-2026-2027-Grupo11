package org.UI;

import org.Controller.PedirSugestaoController;
import org.Model.AssistenteVestuario;
import org.Model.Ocasiao;
import org.Utils.Utils;

/**
 * UI do UC11 - Pedir Sugestão de Combinação para uma Ocasião.
 */
public class PedirSugestaoUI {

    private final PedirSugestaoController controller;

    public PedirSugestaoUI(AssistenteVestuario av) {
        this.controller = new PedirSugestaoController(av);
    }

    public void run() {
        System.out.println("\n--- Pedir Sugestão de Outfit ---");

        if (!controller.temPecasDisponiveis()) {
            System.out.println("O guarda-roupa não tem peças disponíveis suficientes.");
            System.out.println("Adicione peças (UC7) ou altere o seu estado de disponibilidade (UC9).");
            return;
        }

        Ocasiao ocasiao = (Ocasiao) Utils.apresentaESeleciona(controller.getListaOcasioes(), "\nSelecione a ocasião:");
        if (ocasiao == null) {
            System.out.println("Operação cancelada.");
            return;
        }
        controller.novoPedido(ocasiao);

        if (!introduzirContexto()) {
            System.out.println("Operação cancelada.");
            return;
        }

        do {
            if (!controller.gerarSugestao()) {
                System.out.println("Não existe nenhuma combinação adequada a este contexto.");
                System.out.println("Adicione mais peças de categorias diferentes adequadas à ocasião.");
                return;
            }

            System.out.println("\n=== Sugestão de outfit ===");
            System.out.println(controller.getSugestaoAsString());
            System.out.println("A sugestão ficou registada no histórico como pendente.");
        } while (Utils.confirma("Pretende uma sugestão alternativa? (S/N)"));
    }

    private boolean introduzirContexto() {
        do {
            String data = Utils.readLineFromConsole("Data (dd-mm-aaaa): ");
            String local = Utils.readLineFromConsole("Local: ");
            double temperatura = Utils.DoubleFromConsole("Temperatura prevista (ºC): ");
            String tempo = Utils.readLineFromConsole("Condição do tempo (ex.: sol, chuva, vento): ");

            if (controller.setDadosContexto(data, local, temperatura, tempo)) return true;

            System.out.println("Dados do contexto em falta ou inválidos (todos são obrigatórios).");
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
