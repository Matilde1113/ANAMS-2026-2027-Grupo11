package org.UI;

import org.Model.AssistenteVestuario;
import org.Utils.DadosTeste;

/**
 * Ponto de entrada da aplicação Assistente Digital de Vestuário.
 */
public class Main {

    public static void main(String[] args) {
        try {
            AssistenteVestuario av = new AssistenteVestuario();
            DadosTeste.carregarDadosDeTeste(av);

            System.out.println("=== Assistente Digital de Vestuário ===");
            System.out.println("Dados de teste carregados.");
            System.out.println("  Administrador: admin@av.pt / admin123");
            System.out.println("  Utilizador:    " + DadosTeste.EMAIL_TESTE + " / " + DadosTeste.PASSWORD_TESTE + "\n");

            MenuInicialUI menu = new MenuInicialUI(av);
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
