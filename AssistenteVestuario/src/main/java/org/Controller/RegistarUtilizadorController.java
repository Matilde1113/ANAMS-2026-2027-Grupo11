package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.RegistoUtilizadores;
import org.Model.Utilizador;

/**
 * Controller do UC1 - Registar Utilizador.
 */
public class RegistarUtilizadorController {

    private final RegistoUtilizadores registo;
    private Utilizador utilizador;

    public RegistarUtilizadorController(AssistenteVestuario av) {
        this.registo = av.getRegistoUtilizadores();
    }

    public void novoUtilizador() {
        this.utilizador = registo.novoUtilizador();
    }

    public void setDados(String nome, String email, String password) {
        utilizador.setNome(nome);
        utilizador.setEmail(email);
        utilizador.setPassword(password);
    }

    public boolean emailValido(String email) {
        return Utilizador.emailValido(email);
    }

    public boolean passwordValida(String password) {
        return Utilizador.passwordCumpreRequisitos(password);
    }

    public boolean emailJaRegistado(String email) {
        return registo.existeEmail(email);
    }

    public String getUtilizadorAsString() {
        return utilizador != null ? utilizador.toString() : "";
    }

    public boolean registaUtilizador() {
        return registo.registaUtilizador(utilizador);
    }
}
