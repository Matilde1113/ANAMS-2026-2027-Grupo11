package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure Fabrication que cria, valida globalmente (email único) e guarda os utilizadores.
 */
public class RegistoUtilizadores {

    private final List<Utilizador> listaUtilizadores;

    public RegistoUtilizadores() {
        this.listaUtilizadores = new ArrayList<>();
    }

    /**
     * Cria um novo utilizador (Creator).
     */
    public Utilizador novoUtilizador() {
        return new Utilizador();
    }

    public boolean registaUtilizador(Utilizador u) {
        if (!validaUtilizador(u)) return false;
        return addUtilizador(u);
    }

    private boolean validaUtilizador(Utilizador u) {
        return u != null && u.valida() && !existeEmail(u.getEmail());
    }

    private boolean addUtilizador(Utilizador u) {
        return listaUtilizadores.add(u);
    }

    public boolean existeEmail(String email) {
        return getUtilizadorByEmail(email) != null;
    }

    public Utilizador getUtilizadorByEmail(String email) {
        for (Utilizador u : listaUtilizadores) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    /**
     * @return o utilizador se o email e a password estiverem corretos, senão null
     */
    public Utilizador autenticar(String email, String password) {
        Utilizador u = getUtilizadorByEmail(email);
        if (u != null && u.verificaPassword(password)) return u;
        return null;
    }

    public List<Utilizador> getListaUtilizadores() {
        return new ArrayList<>(listaUtilizadores);
    }
}
