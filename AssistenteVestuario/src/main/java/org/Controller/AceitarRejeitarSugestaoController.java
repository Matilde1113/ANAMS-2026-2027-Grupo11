package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.HistoricoSugestoes;
import org.Model.Sugestao;

import java.util.Arrays;
import java.util.List;

/**
 * Controller do UC14 - Aceitar/Rejeitar Sugestão.
 */
public class AceitarRejeitarSugestaoController {

    private final HistoricoSugestoes historico;
    private Sugestao sugestao;
    private boolean aceitar;
    private String motivo;

    public AceitarRejeitarSugestaoController(AssistenteVestuario av) {
        this.historico = av.getUtilizadorAtual().getHistorico();
    }

    public List<Sugestao> getSugestoesPendentes() {
        return historico.getSugestoesPendentes();
    }

    public String selecionaSugestao(Sugestao s) {
        this.sugestao = s;
        return s.toStringDetalhado();
    }

    public List<String> getMotivosRejeicao() {
        return Arrays.asList(Sugestao.MOTIVOS_REJEICAO);
    }

    /**
     * Guarda a decisão (só é aplicada depois da confirmação).
     *
     * @param aceitar true para aceitar, false para rejeitar
     * @param motivo  motivo de rejeição (obrigatório se rejeitar)
     * @return true se a decisão for válida
     */
    public boolean setDecisao(boolean aceitar, String motivo) {
        this.aceitar = aceitar;
        this.motivo = motivo;
        return aceitar || (motivo != null && !motivo.isBlank());
    }

    /**
     * Atualiza o estado da sugestão (a própria Sugestao altera o seu estado - IE).
     */
    public boolean registaDecisao() {
        if (sugestao == null) return false;
        return aceitar ? sugestao.aceitar() : sugestao.rejeitar(motivo);
    }
}
