package org.UI;

import org.Controller.AdicionarPecaController;
import org.Model.*;
import org.Utils.Utils;

import java.util.List;

/**
 * UI do UC7 - Adicionar Peça de Vestuário.
 */
public class AdicionarPecaUI {

    private final AdicionarPecaController controller;

    public AdicionarPecaUI(AssistenteVestuario av) {
        this.controller = new AdicionarPecaController(av);
    }

    public void run() {
        System.out.println("\n--- Adicionar Peça de Vestuário ---");

        if (!controller.listasDeReferenciaPreenchidas()) {
            System.out.println("Ainda não existem categorias, estilos, materiais, cores, partes do corpo,"
                    + " ocasiões, tamanhos ou estados definidos pelo Administrador.");
            return;
        }

        CategoriaPeca categoria = (CategoriaPeca) Utils.apresentaESeleciona(
                controller.getListaCategorias(), "\nSelecione a categoria:");
        if (categoria == null) {
            System.out.println("Operação cancelada.");
            return;
        }
        controller.novaPeca(categoria);

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (controller.existePecaIgual()
                && !Utils.confirma("Já existe no guarda-roupa uma peça com os mesmos dados. Pretende continuar? (S/N)")) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados da peça:");
        System.out.println(controller.getPecaAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaPeca()) {
                System.out.println("Peça adicionada ao guarda-roupa com sucesso.");
            } else {
                System.out.println("Não foi possível adicionar a peça.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Pede os dados e as seleções até a peça ficar válida.
     *
     * @return false se o utilizador cancelar
     */
    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Designação (ex.: Calças de ganga): ");

            String estacao = (String) Utils.apresentaESeleciona(controller.getListaEstacoes(), "\nEstação do ano:");
            Tamanho tamanho = (Tamanho) Utils.apresentaESeleciona(controller.getListaTamanhos(), "\nTamanho:");
            Material material = (Material) Utils.apresentaESeleciona(controller.getListaMateriais(), "\nMaterial:");
            Estilo estilo = (Estilo) Utils.apresentaESeleciona(controller.getListaEstilos(), "\nEstilo:");
            EstadoPeca estado = (EstadoPeca) Utils.apresentaESeleciona(controller.getListaEstados(), "\nEstado:");

            controller.setDados(designacao, estacao, tamanho, material, estilo, estado);

            selecionarCores();
            selecionarPartesCorpo();
            selecionarOcasioes();

            List<String> falta = controller.getDadosEmFalta();
            if (falta.isEmpty()) return true;

            System.out.println("\nDados em falta ou inválidos: " + String.join(", ", falta));
        } while (Utils.confirma("Pretende introduzir novamente os dados? (S/N)"));

        return false;
    }

    // Passos 6 a 8: uma ou mais cores
    private void selecionarCores() {
        do {
            Cor c = (Cor) Utils.apresentaESeleciona(controller.getListaCores(), "\nSelecione uma cor:");
            if (c == null) break;
            if (!controller.addCor(c)) System.out.println("Essa cor já foi selecionada.");
        } while (Utils.confirma("Adicionar outra cor? (S/N)"));
    }

    // Passos 9 a 11: uma ou mais partes do corpo
    private void selecionarPartesCorpo() {
        do {
            ParteCorpo p = (ParteCorpo) Utils.apresentaESeleciona(controller.getListaPartesCorpo(),
                    "\nSelecione uma parte do corpo:");
            if (p == null) break;
            if (!controller.addParteCorpo(p)) System.out.println("Essa parte do corpo já foi selecionada.");
        } while (Utils.confirma("Adicionar outra parte do corpo? (S/N)"));
    }

    // Uma ou mais ocasiões
    private void selecionarOcasioes() {
        do {
            Ocasiao o = (Ocasiao) Utils.apresentaESeleciona(controller.getListaOcasioes(),
                    "\nSelecione uma ocasião adequada:");
            if (o == null) break;
            if (!controller.addOcasiao(o)) System.out.println("Essa ocasião já foi selecionada.");
        } while (Utils.confirma("Adicionar outra ocasião? (S/N)"));
    }
}
