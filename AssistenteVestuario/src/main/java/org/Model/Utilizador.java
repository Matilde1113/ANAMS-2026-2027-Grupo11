package org.Model;

import org.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilizador final (UA/UtF). Ao ser criado fica com um guarda-roupa vazio
 * e um histórico de sugestões (Creator). Cria e guarda as suas combinações (UC13).
 */
public class Utilizador {

    public static final int PASSWORD_MIN = 6;

    private String nome;
    private String email;
    private String password;   // guardada cifrada (hash SHA-256)
    private final String dataRegisto;
    private final GuardaRoupa guardaRoupa;
    private final HistoricoSugestoes historico;
    private final List<Combinacao> listaCombinacoes;

    private static final String STRING_POR_OMISSAO = "a definir";

    public Utilizador() {
        this.nome = STRING_POR_OMISSAO;
        this.email = STRING_POR_OMISSAO;
        this.password = null;
        this.dataRegisto = Utils.getDataHoje();
        this.guardaRoupa = new GuardaRoupa();
        this.historico = new HistoricoSugestoes();
        this.listaCombinacoes = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getDataRegisto() { return dataRegisto; }
    public GuardaRoupa getGuardaRoupa() { return guardaRoupa; }
    public HistoricoSugestoes getHistorico() { return historico; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Guarda a password cifrada. Se não cumprir os requisitos mínimos fica sem password
     * (e o utilizador deixa de ser válido).
     */
    public void setPassword(String password) {
        this.password = passwordCumpreRequisitos(password) ? Utils.hash(password) : null;
    }

    /**
     * Requisitos mínimos: pelo menos 6 caracteres, com letras e números.
     */
    public static boolean passwordCumpreRequisitos(String password) {
        return password != null && password.length() >= PASSWORD_MIN
                && password.matches(".*[A-Za-z].*") && password.matches(".*[0-9].*");
    }

    public static boolean emailValido(String email) {
        return email != null && email.matches("[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+");
    }

    public boolean verificaPassword(String password) {
        return this.password != null && password != null && this.password.equals(Utils.hash(password));
    }

    /**
     * Validação local: nome, email e password obrigatórios e válidos.
     */
    public boolean valida() {
        if (nome == null || nome.isBlank() || nome.equals(STRING_POR_OMISSAO)) return false;
        if (!emailValido(email)) return false;
        return password != null;
    }

    // ---------------- Combinações (UC13 e UC10) ----------------

    /**
     * Cria uma combinação manual para uma ocasião (Creator).
     */
    public Combinacao novaCombinacao(Ocasiao ocasiao) {
        return new Combinacao(ocasiao);
    }

    /**
     * Valida globalmente (sem duplicados) e guarda a combinação.
     */
    public boolean registaCombinacao(Combinacao c) {
        if (c == null || !c.valida()) return false;
        if (existeCombinacaoIgual(c)) return false;
        return listaCombinacoes.add(c);
    }

    public boolean existeCombinacaoIgual(Combinacao c) {
        for (Combinacao existente : listaCombinacoes) {
            if (existente.temMesmasPecas(c)) return true;
        }
        return false;
    }

    public List<Combinacao> getListaCombinacoes() {
        return new ArrayList<>(listaCombinacoes);
    }

    /**
     * Conta as combinações do utilizador que incluem a peça (UC10 4a).
     */
    public int getNumCombinacoesComPeca(PecaVestuario peca) {
        int n = 0;
        for (Combinacao c : listaCombinacoes) {
            if (c.contemPeca(peca)) n++;
        }
        return n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizador:\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Password: ******\n");
        return sb.toString();
    }
}
