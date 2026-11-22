package org.UI;

import org.Controller.AceitarRejeitarSugestaoController;
import org.Model.AssistenteVestuario;
import org.Model.Sugestao;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC14 - Aceitar/Rejeitar Sugestão.
 */
public class AceitarRejeitarSugestaoUI {

    private final AceitarRejeitarSugestaoController controller;

    public AceitarRejeitarSugestaoUI(AssistenteVestuario av) {
        this.controller = new AceitarRejeitarSugestaoController(av);
    }

    public void run() {
        System.out.println("\n--- Aceitar/Rejeitar Sugestão ---");

        List<Sugestao> pendentes = controller.getSugestoesPendentes();
        if (pendentes.isEmpty()) {
            System.out.println("Não existem sugestões pendentes.");
            return;
        }

        Sugestao s = (Sugestao) Utils.apresentaESeleciona(pendentes, "\nSelecione a sugestão:");
        if (s == null) {
            System.out.println("Operação cancelada. A sugestão mantém-se pendente.");
            return;
        }

        System.out.println("\n" + controller.selecionaSugestao(s));

        System.out.println("Decisão:");
        System.out.println("1. Aceitar");
        System.out.println("2. Rejeitar");
        String opcao = Utils.readLineFromConsole("Opção:");

        if (opcao.equals("1")) {
            controller.setDecisao(true, null);
        } else if (opcao.equals("2")) {
            String motivo = (String) Utils.apresentaESeleciona(controller.getMotivosRejeicao(),
                    "\nMotivo da rejeição (obrigatório):");
            if (!controller.setDecisao(false, motivo)) {
                System.out.println("O motivo de rejeição é obrigatório. A sugestão mantém-se pendente.");
                return;
            }
        } else {
            System.out.println("Operação cancelada. A sugestão mantém-se pendente.");
            return;
        }

        if (Utils.confirma("Confirma a decisão? (S/N)")) {
            if (controller.registaDecisao()) {
                System.out.println("Estado da sugestão atualizado com sucesso.");
            } else {
                System.out.println("Não foi possível atualizar a sugestão.");
            }
        } else {
            System.out.println("Operação cancelada. A sugestão mantém-se pendente.");
        }
    }
}
