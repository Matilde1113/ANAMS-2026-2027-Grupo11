package org.UI;

import org.Controller.AvaliarSugestaoController;
import org.Model.AssistenteVestuario;
import org.Model.Avaliacao;
import org.Model.Sugestao;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC12 - Avaliar Sugestão.
 */
public class AvaliarSugestaoUI {

    private final AvaliarSugestaoController controller;

    public AvaliarSugestaoUI(AssistenteVestuario av) {
        this.controller = new AvaliarSugestaoController(av);
    }

    public void run() {
        System.out.println("\n--- Avaliar Sugestão ---");

        List<Sugestao> lista = controller.getSugestoesPorAvaliar();
        if (lista.isEmpty()) {
            System.out.println("Não existem sugestões aceites por avaliar.");
            return;
        }

        Sugestao s = (Sugestao) Utils.apresentaESeleciona(lista, "\nSelecione a sugestão a avaliar:");
        if (s == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\n" + controller.selecionaSugestao(s));

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nAvaliação: " + controller.getAvaliacaoAsString());

        if (Utils.confirma("Confirma a avaliação? (S/N)")) {
            if (controller.registaAvaliacao()) {
                System.out.println("Avaliação registada com sucesso.");
            } else {
                System.out.println("Não foi possível registar a avaliação.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private boolean introduzirDados() {
        do {
            int classificacao = Utils.IntFromConsole("Classificação (" + Avaliacao.CLASSIFICACAO_MIN
                    + " a " + Avaliacao.CLASSIFICACAO_MAX + "): ");
            String comentario = Utils.readLineFromConsole("Comentário: ");

            if (controller.novaAvaliacao(classificacao, comentario)) return true;

            System.out.println("Classificação fora do intervalo ou comentário em falta (ambos obrigatórios).");
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
