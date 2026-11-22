package org.UI;

import org.Controller.CriarCombinacaoController;
import org.Model.AssistenteVestuario;
import org.Model.Ocasiao;
import org.Model.PecaVestuario;
import org.Utils.Utils;

/**
 * UI do UC13 - Criar Combinação Manualmente.
 */
public class CriarCombinacaoUI {

    private final CriarCombinacaoController controller;

    public CriarCombinacaoUI(AssistenteVestuario av) {
        this.controller = new CriarCombinacaoController(av);
    }

    public void run() {
        System.out.println("\n--- Criar Combinação Manualmente ---");

        if (!controller.temPecasSuficientes()) {
            System.out.println("O guarda-roupa tem menos de duas peças.");
            return;
        }

        Ocasiao ocasiao = (Ocasiao) Utils.apresentaESeleciona(controller.getListaOcasioes(), "\nSelecione a ocasião:");
        if (ocasiao == null) {
            System.out.println("Operação cancelada.");
            return;
        }
        controller.novaCombinacao(ocasiao);

        String designacao;
        do {
            designacao = Utils.readLineFromConsole("Designação da combinação: ");
            if (designacao.isBlank()) System.out.println("A designação é obrigatória.");
        } while (designacao.isBlank());
        controller.setDesignacao(designacao);

        if (!selecionarPecas()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\n" + controller.getCombinacaoAsString());

        if (Utils.confirma("Confirma o registo da combinação? (S/N)")) {
            if (controller.registaCombinacao()) {
                System.out.println("Combinação registada com sucesso.");
            } else {
                System.out.println("Não foi possível registar a combinação.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Passos 6 a 9: seleção das peças, uma a uma, até o utilizador terminar.
     *
     * @return false se o utilizador desistir
     */
    private boolean selecionarPecas() {
        do {
            controller.limparPecas();

            do {
                PecaVestuario p = (PecaVestuario) Utils.apresentaESeleciona(controller.getListaPecas(),
                        "\nSelecione uma peça:");
                if (p == null) break;
                if (!controller.addPeca(p)) System.out.println("Essa peça já foi selecionada.");

                System.out.println("\nPeças selecionadas:");
                for (PecaVestuario sel : controller.getPecasSelecionadas()) {
                    System.out.println("  - " + sel);
                }
            } while (Utils.confirma("Adicionar outra peça? (S/N)"));

            if (!controller.cumpreRegrasComposicao()) {
                System.out.println("A combinação tem de ter pelo menos duas peças e não pode ter duas peças da mesma categoria.");
            } else if (controller.existeCombinacaoIgual()) {
                System.out.println("Já existe uma combinação com as mesmas peças.");
            } else {
                return true;
            }
        } while (Utils.confirma("Pretende alterar a seleção? (S/N)"));

        return false;
    }
}
