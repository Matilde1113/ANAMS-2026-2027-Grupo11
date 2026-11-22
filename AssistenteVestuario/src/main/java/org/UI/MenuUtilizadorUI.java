package org.UI;

import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * Menu do Utilizador Autenticado: guarda-roupa, combinações e sugestões.
 */
public class MenuUtilizadorUI {

    private final AssistenteVestuario av;
    private String opcao;

    public MenuUtilizadorUI(AssistenteVestuario av) {
        this.av = av;
    }

    public void run() {
        do {
            System.out.println("\n###### MENU UTILIZADOR (" + av.getUtilizadorAtual().getNome() + ") ######");
            System.out.println("1. Adicionar peça de vestuário (UC7)");
            System.out.println("2. Listar guarda-roupa (UC8)");
            System.out.println("3. Editar peça de vestuário (UC9)");
            System.out.println("4. Remover peça de vestuário (UC10)");
            System.out.println("5. Pedir sugestão de outfit (UC11)");
            System.out.println("6. Avaliar sugestão (UC12)");
            System.out.println("7. Criar combinação manualmente (UC13)");
            System.out.println("8. Aceitar/rejeitar sugestão (UC14)");
            System.out.println("9. Consultar histórico de sugestões e avaliações (UC15)");
            System.out.println("0. Terminar sessão");

            opcao = Utils.readLineFromConsole("Escolha uma opção:");

            switch (opcao) {
                case "1" -> new AdicionarPecaUI(av).run();
                case "2" -> new ListarGuardaRoupaUI(av).run();
                case "3" -> new EditarPecaUI(av).run();
                case "4" -> new RemoverPecaUI(av).run();
                case "5" -> new PedirSugestaoUI(av).run();
                case "6" -> new AvaliarSugestaoUI(av).run();
                case "7" -> new CriarCombinacaoUI(av).run();
                case "8" -> new AceitarRejeitarSugestaoUI(av).run();
                case "9" -> new ConsultarHistoricoUI(av).run();
                case "0" -> { }
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }
}
