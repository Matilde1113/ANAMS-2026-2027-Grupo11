package org.Controller;

import org.Model.*;

import java.util.List;

/**
 * Controller do UC13 - Criar Combinação Manualmente.
 */
public class CriarCombinacaoController {

    private final AssistenteVestuario av;
    private final Utilizador utilizador;
    private Combinacao combinacao;

    public CriarCombinacaoController(AssistenteVestuario av) {
        this.av = av;
        this.utilizador = av.getUtilizadorAtual();
    }

    /**
     * Extensão 2a: o guarda-roupa tem de ter pelo menos duas peças.
     */
    public boolean temPecasSuficientes() {
        return utilizador.getGuardaRoupa().getListaPecas().size() >= Combinacao.MIN_PECAS;
    }

    public List<Ocasiao> getListaOcasioes() {
        return av.getRegistoOcasioes().getListaOcasioes();
    }

    /**
     * Cria a combinação (o Utilizador é o Creator).
     */
    public void novaCombinacao(Ocasiao ocasiao) {
        this.combinacao = utilizador.novaCombinacao(ocasiao);
    }

    public void setDesignacao(String designacao) {
        combinacao.setDesignacao(designacao);
    }

    public List<PecaVestuario> getListaPecas() {
        return utilizador.getGuardaRoupa().getListaPecas();
    }

    public boolean addPeca(PecaVestuario p) {
        return combinacao.addPeca(p);
    }

    public List<PecaVestuario> getPecasSelecionadas() {
        return combinacao.getPecas();
    }

    public void limparPecas() {
        combinacao.limparPecas();
    }

    /**
     * Extensão 10a: regras mínimas de composição.
     */
    public boolean cumpreRegrasComposicao() {
        return combinacao.cumpreRegrasComposicao();
    }

    /**
     * Extensão 10b: já existe uma combinação com as mesmas peças?
     */
    public boolean existeCombinacaoIgual() {
        return utilizador.existeCombinacaoIgual(combinacao);
    }

    public String getCombinacaoAsString() {
        return combinacao != null ? combinacao.toString() : "";
    }

    public boolean registaCombinacao() {
        return utilizador.registaCombinacao(combinacao);
    }
}
