package org.Controller;

import org.Model.*;

import java.util.List;

/**
 * Controller do UC11 - Pedir Sugestão de Combinação para uma Ocasião.
 * Depende apenas da interface IMotorSugestoes (Protected Variations).
 */
public class PedirSugestaoController {

    private final AssistenteVestuario av;
    private final Utilizador utilizador;
    private final HistoricoSugestoes historico;
    private final IMotorSugestoes motor;
    private Contexto contexto;
    private Sugestao sugestao;

    public PedirSugestaoController(AssistenteVestuario av) {
        this.av = av;
        this.utilizador = av.getUtilizadorAtual();
        this.historico = utilizador.getHistorico();
        this.motor = av.getMotorSugestoes();
    }

    /**
     * Extensão 2a: são precisas pelo menos duas peças disponíveis.
     */
    public boolean temPecasDisponiveis() {
        return utilizador.getGuardaRoupa().getPecasDisponiveis().size() >= Combinacao.MIN_PECAS;
    }

    public List<Ocasiao> getListaOcasioes() {
        return av.getRegistoOcasioes().getListaOcasioes();
    }

    public void novoPedido(Ocasiao ocasiao) {
        this.contexto = historico.novoContexto(ocasiao);
    }

    /**
     * @return true se os dados do contexto forem válidos
     */
    public boolean setDadosContexto(String data, String local, double temperatura, String condicaoTempo) {
        contexto.setDados(data, local, temperatura, condicaoTempo);
        return contexto.valida();
    }

    /**
     * Gera a sugestão e regista-a no histórico com o estado pendente.
     * Pode ser chamado novamente para pedir uma alternativa (extensão 6c).
     *
     * @return true se foi possível gerar uma sugestão
     */
    public boolean gerarSugestao() {
        this.sugestao = motor.gerarSugestao(utilizador, contexto);
        if (sugestao == null) return false;
        return historico.registaSugestao(sugestao);
    }

    public String getSugestaoAsString() {
        return sugestao != null ? sugestao.toStringDetalhado() : "";
    }
}
