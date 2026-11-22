package org.UI;

import org.Controller.ConsultarHistoricoController;
import org.Model.AssistenteVestuario;
import org.Model.EstadoSugestao;
import org.Model.Ocasiao;
import org.Model.Sugestao;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC15 - Consultar Histórico de Sugestões e Avaliações.
 */
public class ConsultarHistoricoUI {

    private final ConsultarHistoricoController controller;

    public ConsultarHistoricoUI(AssistenteVestuario av) {
        this.controller = new ConsultarHistoricoController(av);
    }

    public void run() {
        System.out.println("\n--- Histórico de Sugestões e Avaliações ---");

        do {
            System.out.println("Critérios de filtragem (todos opcionais - carregue Enter para ignorar).");

            String dataInicio = lerDataOpcional("Data inicial (dd-mm-aaaa): ");
            String dataFim = lerDataOpcional("Data final (dd-mm-aaaa): ");

            Ocasiao ocasiao = null;
            if (Utils.confirma("Filtrar por ocasião? (S/N)")) {
                ocasiao = (Ocasiao) Utils.apresentaESeleciona(controller.getListaOcasioes(), "\nOcasião:");
            }

            EstadoSugestao estado = null;
            if (Utils.confirma("Filtrar por estado? (S/N)")) {
                estado = (EstadoSugestao) Utils.apresentaESeleciona(controller.getListaEstados(), "\nEstado:");
            }

            List<Sugestao> lista = controller.filtrar(dataInicio, dataFim, ocasiao, estado);

            if (lista.isEmpty()) {
                System.out.println("Não existem sugestões que satisfaçam os critérios.");
                continue;
            }

            Sugestao s = (Sugestao) Utils.apresentaESeleciona(lista,
                    "\nSugestões (mais recente primeiro) - selecione uma para ver os detalhes:");
            if (s != null) {
                System.out.println("\n" + controller.getDetalhes(s));
            }
        } while (Utils.confirma("Pretende fazer outra consulta? (S/N)"));
    }

    private String lerDataOpcional(String prompt) {
        do {
            String data = Utils.readLineFromConsole(prompt);
            if (data.isBlank()) return null;
            if (Utils.dataValida(data)) return data;
            System.out.println("Data inválida.");
        } while (true);
    }
}
