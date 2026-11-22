package org.UI;

import org.Model.Administrador;
import org.Model.AssistenteVestuario;
import org.Model.Utilizador;
import org.Utils.Utils;

/**
 * Menu inicial: registo de utilizador (UC1) e autenticação.
 */
public class MenuInicialUI {

    private final AssistenteVestuario av;
    private String opcao;

    public MenuInicialUI(AssistenteVestuario av) {
        this.av = av;
    }

    public void run() {
        do {
            System.out.println("\n###### MENU INICIAL ######");
            System.out.println("1. Registar utilizador");
            System.out.println("2. Iniciar sessão");
            System.out.println("0. Sair");

            opcao = Utils.readLineFromConsole("Escolha uma opção:");

            switch (opcao) {
                case "1" -> new RegistarUtilizadorUI(av).run();
                case "2" -> iniciarSessao();
                case "0" -> System.out.println("Até breve!");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private void iniciarSessao() {
        Object autenticado = new LoginUI(av).run();

        if (autenticado instanceof Administrador) {
            new MenuAdministradorUI(av).run();
        } else if (autenticado instanceof Utilizador) {
            new MenuUtilizadorUI(av).run();
        }
        av.logout();
    }
}
