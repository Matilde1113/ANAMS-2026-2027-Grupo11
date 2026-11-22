package org.UI;

import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * Menu do Administrador: especificação dos dados de referência.
 */
public class MenuAdministradorUI {

    private final AssistenteVestuario av;
    private String opcao;

    public MenuAdministradorUI(AssistenteVestuario av) {
        this.av = av;
    }

    public void run() {
        do {
            System.out.println("\n###### MENU ADMINISTRADOR ######");
            System.out.println("1. Especificar estilo (UC2)");
            System.out.println("2. Especificar cor (UC3)");
            System.out.println("3. Especificar material (UC4)");
            System.out.println("4. Especificar categoria de peça (UC5)");
            System.out.println("5. Especificar ocasião (UC6)");
            System.out.println("6. Especificar parte do corpo (UC16)");
            System.out.println("7. Especificar tamanho (UC17)");
            System.out.println("8. Especificar estado da peça (UC18)");
            System.out.println("0. Terminar sessão");

            opcao = Utils.readLineFromConsole("Escolha uma opção:");

            switch (opcao) {
                case "1" -> new EspecificarEstiloUI(av).run();
                case "2" -> new EspecificarCorUI(av).run();
                case "3" -> new EspecificarMaterialUI(av).run();
                case "4" -> new EspecificarCategoriaPecaUI(av).run();
                case "5" -> new EspecificarOcasiaoUI(av).run();
                case "6" -> new EspecificarParteCorpoUI(av).run();
                case "7" -> new EspecificarTamanhoUI(av).run();
                case "8" -> new EspecificarEstadoPecaUI(av).run();
                case "0" -> { }
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }
}
