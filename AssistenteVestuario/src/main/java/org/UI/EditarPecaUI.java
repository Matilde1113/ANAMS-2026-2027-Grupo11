package org.UI;

import org.Controller.EditarPecaController;
import org.Model.*;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC9 - Editar Peça de Vestuário.
 */
public class EditarPecaUI {

    private final EditarPecaController controller;

    public EditarPecaUI(AssistenteVestuario av) {
        this.controller = new EditarPecaController(av);
    }

    public void run() {
        System.out.println("\n--- Editar Peça de Vestuário ---");

        List<PecaVestuario> pecas = controller.getListaPecas();
        if (pecas.isEmpty()) {
            System.out.println("O guarda-roupa não tem peças.");
            return;
        }

        PecaVestuario peca = (PecaVestuario) Utils.apresentaESeleciona(pecas, "\nSelecione a peça a editar:");
        if (peca == null) {
            System.out.println("Operação cancelada. A peça não foi alterada.");
            return;
        }

        System.out.println("\nDados atuais:");
        System.out.println(controller.selecionaPeca(peca));

        if (!introduzirDados()) {
            System.out.println("Operação cancelada. A peça não foi alterada.");
            return;
        }

        System.out.println("\nNovos dados da peça:");
        System.out.println(controller.getPecaEditadaAsString());

        if (Utils.confirma("Confirma as alterações? (S/N)")) {
            if (controller.atualizaPeca()) {
                System.out.println("Peça atualizada com sucesso.");
            } else {
                System.out.println("Não foi possível atualizar a peça.");
            }
        } else {
            System.out.println("Operação cancelada. A peça não foi alterada.");
        }
    }

    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Nova designação: ");

            String estacao = (String) Utils.apresentaESeleciona(controller.getListaEstacoes(), "\nEstação do ano:");
            Tamanho tamanho = (Tamanho) Utils.apresentaESeleciona(controller.getListaTamanhos(), "\nTamanho:");
            Material material = (Material) Utils.apresentaESeleciona(controller.getListaMateriais(), "\nMaterial:");
            Estilo estilo = (Estilo) Utils.apresentaESeleciona(controller.getListaEstilos(), "\nEstilo:");
            EstadoPeca estado = (EstadoPeca) Utils.apresentaESeleciona(controller.getListaEstados(),
                    "\nEstado (ex.: altere para 'Em lavagem' quando a peça for lavar):");

            controller.setDados(designacao, estacao, tamanho, material, estilo, estado);

            System.out.println("\nAs cores, partes do corpo e ocasiões selecionadas substituem as anteriores.");
            selecionarCores();
            selecionarPartesCorpo();
            selecionarOcasioes();

            List<String> falta = controller.getDadosEmFalta();
            if (falta.isEmpty()) return true;

            System.out.println("\nDados em falta ou inválidos: " + String.join(", ", falta));
        } while (Utils.confirma("Pretende introduzir novamente os dados? (S/N)"));

        return false;
    }

    private void selecionarCores() {
        do {
            Cor c = (Cor) Utils.apresentaESeleciona(controller.getListaCores(), "\nSelecione uma cor:");
            if (c == null) break;
            if (!controller.addCor(c)) System.out.println("Essa cor já foi selecionada.");
        } while (Utils.confirma("Adicionar outra cor? (S/N)"));
    }

    private void selecionarPartesCorpo() {
        do {
            ParteCorpo p = (ParteCorpo) Utils.apresentaESeleciona(controller.getListaPartesCorpo(),
                    "\nSelecione uma parte do corpo:");
            if (p == null) break;
            if (!controller.addParteCorpo(p)) System.out.println("Essa parte do corpo já foi selecionada.");
        } while (Utils.confirma("Adicionar outra parte do corpo? (S/N)"));
    }

    private void selecionarOcasioes() {
        do {
            Ocasiao o = (Ocasiao) Utils.apresentaESeleciona(controller.getListaOcasioes(),
                    "\nSelecione uma ocasião adequada:");
            if (o == null) break;
            if (!controller.addOcasiao(o)) System.out.println("Essa ocasião já foi selecionada.");
        } while (Utils.confirma("Adicionar outra ocasião? (S/N)"));
    }
}
