package org.Model;

import org.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Histórico de todas as sugestões apresentadas a um utilizador.
 * Funciona como registo das sugestões do utilizador (UC11, UC12, UC14, UC15).
 */
public class HistoricoSugestoes {

    private final List<Sugestao> listaSugestoes;

    public HistoricoSugestoes() {
        this.listaSugestoes = new ArrayList<>();
    }

    /**
     * Cria o contexto de um novo pedido de sugestão (Creator - UC11).
     */
    public Contexto novoContexto(Ocasiao ocasiao) {
        return new Contexto(ocasiao);
    }

    public boolean registaSugestao(Sugestao s) {
        if (!validaSugestao(s)) return false;
        return addSugestao(s);
    }

    private boolean validaSugestao(Sugestao s) {
        return s != null && s.getCombinacao() != null && s.getContexto() != null
                && !listaSugestoes.contains(s);
    }

    private boolean addSugestao(Sugestao s) {
        return listaSugestoes.add(s);
    }

    public List<Sugestao> getListaSugestoes() {
        return new ArrayList<>(listaSugestoes);
    }

    /**
     * @return sugestões ainda pendentes (UC14)
     */
    public List<Sugestao> getSugestoesPendentes() {
        List<Sugestao> res = new ArrayList<>();
        for (Sugestao s : listaSugestoes) {
            if (s.isPendente()) res.add(s);
        }
        return res;
    }

    /**
     * @return sugestões aceites ainda sem avaliação (UC12)
     */
    public List<Sugestao> getSugestoesPorAvaliar() {
        List<Sugestao> res = new ArrayList<>();
        for (Sugestao s : listaSugestoes) {
            if (s.isPorAvaliar()) res.add(s);
        }
        return res;
    }

    /**
     * @return lista das avaliações já feitas (usadas pelo motor de sugestões)
     */
    public List<Avaliacao> getAvaliacoes() {
        List<Avaliacao> res = new ArrayList<>();
        for (Sugestao s : listaSugestoes) {
            if (s.getAvaliacao() != null) res.add(s.getAvaliacao());
        }
        return res;
    }

    /**
     * Filtra as sugestões pelos critérios indicados (UC15). Critérios a null são ignorados.
     * A lista é devolvida da sugestão mais recente para a mais antiga.
     *
     * @param dataInicio início do período (dd-MM-yyyy) ou null
     * @param dataFim    fim do período (dd-MM-yyyy) ou null
     * @param ocasiao    ocasião ou null
     * @param estado     estado ou null
     */
    public List<Sugestao> filtrar(String dataInicio, String dataFim, Ocasiao ocasiao, EstadoSugestao estado) {
        List<Sugestao> res = new ArrayList<>();

        for (int i = listaSugestoes.size() - 1; i >= 0; i--) {   // mais recente primeiro
            Sugestao s = listaSugestoes.get(i);
            String dataContexto = s.getContexto().getData();

            if (dataInicio != null && !Utils.dataDentroDoIntervalo(dataContexto, dataInicio, "31-12-9999"))
                continue;
            if (dataFim != null && !Utils.dataDentroDoIntervalo(dataContexto, "01-01-0001", dataFim))
                continue;
            if (ocasiao != null && s.getContexto().getOcasiao() != ocasiao)
                continue;
            if (estado != null && s.getEstado() != estado)
                continue;

            res.add(s);
        }
        return res;
    }
}
