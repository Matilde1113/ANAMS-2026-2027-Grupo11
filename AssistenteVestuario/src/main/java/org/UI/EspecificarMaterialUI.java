package org.UI;

import org.Controller.EspecificarMaterialController;
import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * UI do UC4 - Especificar Material.
 */
public class EspecificarMaterialUI {

    private final EspecificarMaterialController controller;

    public EspecificarMaterialUI(AssistenteVestuario av) {
        this.controller = new EspecificarMaterialController(av);
    }

    public void run() {
        System.out.println("\n--- Especificar Material ---");

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getMaterialAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaMaterial()) {
                System.out.println("Material registado com sucesso.");
            } else {
                System.out.println("Não foi possível registar o material.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    /**
     * Pede os dados até serem válidos ou até o Administrador desistir.
     *
     * @return true se os dados ficaram válidos
     */
    private boolean introduzirDados() {
        do {
            String designacao = Utils.readLineFromConsole("Designação (ex.: Algodão): ");
            String descricao = Utils.readLineFromConsole("Descrição: ");

            if (designacao.isBlank() || descricao.isBlank()) {
                System.out.println("Dados em falta: a designação e a descrição são obrigatórias.");
            } else if (controller.existeMaterial(designacao)) {
                System.out.println("Já existe um material com essa designação.");
            } else if (controller.novoMaterial(designacao, descricao)) {
                return true;
            } else {
                System.out.println("Dados inválidos.");
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
