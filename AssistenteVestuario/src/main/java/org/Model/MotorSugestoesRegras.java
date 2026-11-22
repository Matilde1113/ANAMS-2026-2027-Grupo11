package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do motor de sugestões baseada em regras simples (Pure Fabrication).
 *
 * Regras:
 * 1. Só usa peças disponíveis e adequadas à ocasião.
 * 2. Dá preferência a peças adequadas à estação (segundo a temperatura).
 * 3. Pontua cada peça com base no histórico: peças de sugestões bem avaliadas sobem,
 *    peças de sugestões rejeitadas ou mal avaliadas descem.
 * 4. Penaliza peças já sugeridas para o mesmo pedido (para gerar alternativas - UC11 6c).
 * 5. Escolhe no máximo uma peça por categoria e sem partes do corpo repetidas.
 */
public class MotorSugestoesRegras implements IMotorSugestoes {

    @Override
    public Sugestao gerarSugestao(Utilizador u, Contexto ctx) {
        if (u == null || ctx == null || !ctx.valida()) return null;

        List<Sugestao> anteriores = u.getHistorico().getListaSugestoes();
        List<PecaVestuario> selecionadas = selecionaPecas(u.getGuardaRoupa(), ctx, anteriores);

        if (selecionadas.size() < Combinacao.MIN_PECAS) return null;

        Combinacao comb = new Combinacao(selecionadas, ctx.getOcasiao());
        return new Sugestao(comb, ctx);
    }

    /**
     * Seleciona as peças da combinação.
     */
    private List<PecaVestuario> selecionaPecas(GuardaRoupa gr, Contexto ctx, List<Sugestao> anteriores) {
        List<PecaVestuario> candidatas = new ArrayList<>();
        for (PecaVestuario p : gr.getPecasDisponiveis()) {
            if (p.isAdequadaA(ctx.getOcasiao())) candidatas.add(p);
        }

        // ordenar por pontuação (maior primeiro) - ordenação por seleção
        List<PecaVestuario> ordenadas = new ArrayList<>();
        List<Double> pontos = new ArrayList<>();
        for (PecaVestuario p : candidatas) {
            double pont = pontuacao(p, ctx, anteriores);
            int pos = 0;
            while (pos < pontos.size() && pontos.get(pos) >= pont) pos++;
            ordenadas.add(pos, p);
            pontos.add(pos, pont);
        }

        List<PecaVestuario> escolhidas = new ArrayList<>();
        for (PecaVestuario p : ordenadas) {
            if (podeJuntar(p, escolhidas)) escolhidas.add(p);
        }
        return escolhidas;
    }

    private boolean podeJuntar(PecaVestuario p, List<PecaVestuario> escolhidas) {
        for (PecaVestuario e : escolhidas) {
            if (e.getCategoria() == p.getCategoria()) return false;
            if (e.partilhaParteCorpoCom(p)) return false;
        }
        return true;
    }

    private double pontuacao(PecaVestuario p, Contexto ctx, List<Sugestao> anteriores) {
        double pont = 0;

        if (estacaoAdequada(p.getEstacao(), ctx.getTemperatura())) pont += 2;

        for (Sugestao s : anteriores) {
            if (!s.getCombinacao().contemPeca(p)) continue;

            if (s.getAvaliacao() != null) {
                pont += s.getAvaliacao().getClassificacao() - 3;   // 1..5 -> -2..+2
            }
            if (s.getEstado() == EstadoSugestao.REJEITADA) {
                pont -= 1;
            }
            if (mesmoPedido(s.getContexto(), ctx)) {
                pont -= 3;   // variar em relação a sugestões anteriores para o mesmo pedido
            }
        }
        return pont;
    }

    private boolean mesmoPedido(Contexto a, Contexto b) {
        return a.getOcasiao() == b.getOcasiao() && a.getData().equals(b.getData());
    }

    private boolean estacaoAdequada(String estacao, double temperatura) {
        if (estacao.equalsIgnoreCase("Todas")) return true;
        if (temperatura < 12) return estacao.equalsIgnoreCase("Inverno") || estacao.equalsIgnoreCase("Outono");
        if (temperatura > 22) return estacao.equalsIgnoreCase("Verão") || estacao.equalsIgnoreCase("Primavera");
        return estacao.equalsIgnoreCase("Primavera") || estacao.equalsIgnoreCase("Outono");
    }
}
