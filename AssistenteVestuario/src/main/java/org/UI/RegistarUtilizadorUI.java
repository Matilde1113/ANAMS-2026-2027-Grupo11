package org.UI;

import org.Controller.RegistarUtilizadorController;
import org.Model.AssistenteVestuario;
import org.Model.Utilizador;
import org.Utils.Utils;

/**
 * UI do UC1 - Registar Utilizador.
 */
public class RegistarUtilizadorUI {

    private final RegistarUtilizadorController controller;

    public RegistarUtilizadorUI(AssistenteVestuario av) {
        this.controller = new RegistarUtilizadorController(av);
    }

    public void run() {
        System.out.println("\n--- Registar Utilizador ---");

        controller.novoUtilizador();

        if (!introduzirDados()) {
            System.out.println("Operação cancelada.");
            return;
        }

        System.out.println("\nDados introduzidos:");
        System.out.println(controller.getUtilizadorAsString());

        if (Utils.confirma("Confirma o registo? (S/N)")) {
            if (controller.registaUtilizador()) {
                System.out.println("Utilizador registado com sucesso. Já pode iniciar sessão.");
            } else {
                System.out.println("Não foi possível registar o utilizador.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private boolean introduzirDados() {
        do {
            String nome = Utils.readLineFromConsole("Nome: ");
            String email = Utils.readLineFromConsole("Email: ");
            String password = Utils.readLineFromConsole("Password (mín. " + Utilizador.PASSWORD_MIN
                    + " caracteres, com letras e números): ");

            if (nome.isBlank() || email.isBlank() || password.isBlank()) {
                System.out.println("Dados em falta: nome, email e password são obrigatórios.");
            } else if (!controller.emailValido(email)) {
                System.out.println("Formato de email inválido.");
            } else if (!controller.passwordValida(password)) {
                System.out.println("A password não cumpre os requisitos mínimos.");
            } else if (controller.emailJaRegistado(email)) {
                System.out.println("Já existe um utilizador registado com esse email.");
            } else {
                controller.setDados(nome, email, password);
                return true;
            }
        } while (Utils.confirma("Pretende alterar os dados? (S/N)"));

        return false;
    }
}
