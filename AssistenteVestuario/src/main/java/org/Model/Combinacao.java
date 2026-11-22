package org.Model;

import org.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Conjunto de duas ou mais peças do guarda-roupa que forma um outfit para uma ocasião.
 * Pode ser criada manualmente (UC13) ou proposta pelo sistema numa sugestão (UC11).
 */
public class Combinacao {

    public static final String ORIGEM_MANUAL = "Manual";
    public static final String ORIGEM_SUGERIDA = "Sugerida";
    public static final int MIN_PECAS = 2;

    private static int contador = 0;

    private final int codigo;
    private String origem;
    private String designacao;
    private final String dataCriacao;
    private Ocasiao ocasiao;
    private final List<PecaVestuario> pecas;

    private static final String STRING_POR_OMISSAO = "a definir";

    /**
     * Combinação criada manualmente pelo utilizador (UC13).
     */
    public Combinacao(Ocasiao ocasiao) {
        this.codigo = ++contador;
        this.origem = ORIGEM_MANUAL;
        this.designacao = STRING_POR_OMISSAO;
        this.dataCriacao = Utils.getDataHoje();
        this.ocasiao = ocasiao;
        this.pecas = new ArrayList<>();
    }

    /**
     * Combinação proposta pelo motor de sugestões (UC11).
     */
    public Combinacao(List<PecaVestuario> lstPecas, Ocasiao ocasiao) {
        this(ocasiao);
        this.origem = ORIGEM_SUGERIDA;
        this.designacao = "Sugestão para " + (ocasiao != null ? ocasiao.getDesignacao() : "-");
        if (lstPecas != null) {
            for (PecaVestuario p : lstPecas) addPeca(p);
        }
    }

    public int getCodigo() { return codigo; }
    public String getOrigem() { return origem; }
    public String getDesignacao() { return designacao; }
    public String getDataCriacao() { return dataCriacao; }
    public Ocasiao getOcasiao() { return ocasiao; }
    public List<PecaVestuario> getPecas() { return new ArrayList<>(pecas); }

    public void setDesignacao(String designacao) { this.designacao = designacao; }
    public void setOcasiao(Ocasiao ocasiao) { this.ocasiao = ocasiao; }

    /**
     * Adiciona uma peça (não permite a mesma peça duas vezes).
     */
    public boolean addPeca(PecaVestuario p) {
        if (p == null || pecas.contains(p)) return false;
        return pecas.add(p);
    }

    /**
     * Remove todas as peças (para o utilizador refazer a seleção - UC13 10a).
     */
    public void limparPecas() {
        pecas.clear();
    }

    public boolean contemPeca(PecaVestuario p) {
        return pecas.contains(p);
    }

    /**
     * Verifica se outra combinação tem exatamente as mesmas peças (UC13 10b).
     */
    public boolean temMesmasPecas(Combinacao outra) {
        return outra != null && pecas.size() == outra.pecas.size() && pecas.containsAll(outra.pecas);
    }

    /**
     * Regras de composição: pelo menos duas peças e nenhuma categoria repetida
     * (ex.: não pode ter duas calças).
     */
    public boolean cumpreRegrasComposicao() {
        if (pecas.size() < MIN_PECAS) return false;
        for (int i = 0; i < pecas.size(); i++) {
            for (int j = i + 1; j < pecas.size(); j++) {
                if (pecas.get(i).getCategoria() == pecas.get(j).getCategoria()) return false;
            }
        }
        return true;
    }

    /**
     * Validação local: designação e ocasião obrigatórias e regras de composição.
     */
    public boolean valida() {
        if (designacao == null || designacao.isBlank() || designacao.equals(STRING_POR_OMISSAO)) return false;
        if (ocasiao == null) return false;
        return cumpreRegrasComposicao();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Combinação ").append(codigo).append(" - ").append(designacao)
                .append(" (").append(origem).append(")\n");
        sb.append("Ocasião: ").append(ocasiao != null ? ocasiao.getDesignacao() : "-").append("\n");
        sb.append("Peças:\n");
        for (PecaVestuario p : pecas) {
            sb.append("  - ").append(p).append("\n");
        }
        return sb.toString();
    }
}
