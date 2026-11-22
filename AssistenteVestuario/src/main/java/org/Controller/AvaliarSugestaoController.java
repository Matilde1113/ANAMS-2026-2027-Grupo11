package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.Avaliacao;
import org.Model.HistoricoSugestoes;
import org.Model.Sugestao;

import java.util.List;

/**
 * Controller do UC12 - Avaliar Sugestão.
 */
public class AvaliarSugestaoController {

    private final HistoricoSugestoes historico;
    private Sugestao sugestao;
    private Avaliacao avaliacao;

    public AvaliarSugestaoController(AssistenteVestuario av) {
        this.historico = av.getUtilizadorAtual().getHistorico();
    }

    public List<Sugestao> getSugestoesPorAvaliar() {
        return historico.getSugestoesPorAvaliar();
    }

    public String selecionaSugestao(Sugestao s) {
        this.sugestao = s;
        return s.toStringDetalhado();
    }

    /**
     * Cria a avaliação (a Sugestao é o Creator).
     *
     * @return true se a avaliação for válida
     */
    public boolean novaAvaliacao(int classificacao, String comentario) {
        this.avaliacao = sugestao.novaAvaliacao(classificacao, comentario);
        return avaliacao.valida();
    }

    public String getAvaliacaoAsString() {
        return avaliacao != null ? avaliacao.toString() : "";
    }

    public boolean registaAvaliacao() {
        return sugestao.setAvaliacao(avaliacao);
    }
}
