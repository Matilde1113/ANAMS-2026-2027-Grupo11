package org.Model;

import org.Utils.Utils;

/**
 * Administrador: define os dados de referência do sistema
 * (estilos, cores, materiais, categorias, ocasiões, partes do corpo, tamanhos e estados).
 */
public class Administrador {

    private String nome;
    private String email;
    private String password;   // cifrada

    public Administrador(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = Utils.hash(password);
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public boolean verificaPassword(String password) {
        return password != null && this.password.equals(Utils.hash(password));
    }

    @Override
    public String toString() {
        return "Administrador: " + nome + " (" + email + ")";
    }
}
