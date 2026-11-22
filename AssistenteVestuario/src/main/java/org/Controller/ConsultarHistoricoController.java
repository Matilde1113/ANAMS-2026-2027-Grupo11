package org.Controller;

import org.Model.*;

import java.util.Arrays;
import java.util.List;

/**
 * Controller do UC15 - Consultar Histórico de Sugestões e Avaliações.
 */
public class ConsultarHistoricoController {

    private final AssistenteVestuario av;
    private final HistoricoSugestoes historico;

    public ConsultarHistoricoController(AssistenteVestuario av) {
        this.av = av;
        this.historico = av.getUtilizadorAtual().getHistorico();
    }

    public List<Ocasiao> getListaOcasioes() {
        return av.getRegistoOcasioes().getListaOcasioes();
    }

    public List<EstadoSugestao> getListaEstados() {
        return Arrays.asList(EstadoSugestao.values());
    }

    /**
     * Filtra o histórico. Os critérios a null são ignorados (extensão 3a).
     */
    public List<Sugestao> filtrar(String dataInicio, String dataFim, Ocasiao ocasiao, EstadoSugestao estado) {
        return historico.filtrar(dataInicio, dataFim, ocasiao, estado);
    }

    public String getDetalhes(Sugestao s) {
        return s.toStringDetalhado();
    }
}
