package org.UI;

import org.Model.AssistenteVestuario;
import org.Utils.Utils;

/**
 * Autenticação por email e password (máximo de 3 tentativas).
 */
public class LoginUI {

    private static final int MAX_TENTATIVAS = 3;

    private final AssistenteVestuario av;

    public LoginUI(AssistenteVestuario av) {
        this.av = av;
    }

    /**
     * @return o Administrador ou Utilizador autenticado, ou null
     */
    public Object run() {
        System.out.println("\n--- Iniciar Sessão ---");

        for (int i = 1; i <= MAX_TENTATIVAS; i++) {
            String email = Utils.readLineFromConsole("Email (0 para cancelar): ");
            if (email.equals("0")) return null;

            String password = Utils.readLineFromConsole("Password: ");

            Object autenticado = av.autenticar(email, password);
            if (autenticado != null) {
                System.out.println("Sessão iniciada com sucesso.");
                return autenticado;
            }
            System.out.println("Credenciais incorretas (" + i + "/" + MAX_TENTATIVAS + ").");
        }
        return null;
    }
}
