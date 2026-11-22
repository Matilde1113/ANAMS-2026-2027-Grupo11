package org.Controller;

import org.Model.*;

import java.util.Arrays;
import java.util.List;

/**
 * Controller do UC7 - Adicionar Peça de Vestuário.
 * Obtém as listas de referência através dos registos e delega no GuardaRoupa
 * a criação, validação e registo da peça.
 */
public class AdicionarPecaController {

    private final AssistenteVestuario av;
    private final GuardaRoupa guardaRoupa;
    private PecaVestuario peca;

    public AdicionarPecaController(AssistenteVestuario av) {
        this.av = av;
        this.guardaRoupa = av.getUtilizadorAtual().getGuardaRoupa();
    }

    /**
     * Pré-condição do UC7: as listas de referência não podem estar vazias (extensão 2a).
     */
    public boolean listasDeReferenciaPreenchidas() {
        return !getListaCategorias().isEmpty() && !getListaTamanhos().isEmpty()
                && !getListaCores().isEmpty() && !getListaMateriais().isEmpty()
                && !getListaEstilos().isEmpty() && !getListaPartesCorpo().isEmpty()
                && !getListaOcasioes().isEmpty() && !getListaEstados().isEmpty();
    }

    public List<CategoriaPeca> getListaCategorias() { return av.getRegistoCategorias().getListaCategorias(); }
    public List<Tamanho> getListaTamanhos() { return av.getRegistoTamanhos().getListaTamanhos(); }
    public List<Cor> getListaCores() { return av.getRegistoCores().getListaCores(); }
    public List<Material> getListaMateriais() { return av.getRegistoMateriais().getListaMateriais(); }
    public List<Estilo> getListaEstilos() { return av.getRegistoEstilos().getListaEstilos(); }
    public List<ParteCorpo> getListaPartesCorpo() { return av.getRegistoPartesCorpo().getListaPartesCorpo(); }
    public List<Ocasiao> getListaOcasioes() { return av.getRegistoOcasioes().getListaOcasioes(); }
    public List<EstadoPeca> getListaEstados() { return av.getRegistoEstados().getListaEstados(); }
    public List<String> getListaEstacoes() { return Arrays.asList(PecaVestuario.ESTACOES); }

    public void novaPeca(CategoriaPeca categoria) {
        this.peca = guardaRoupa.novaPeca(categoria);
    }

    public void setDados(String designacao, String estacao, Tamanho tamanho,
                         Material material, Estilo estilo, EstadoPeca estado) {
        peca.setDados(designacao, estacao, tamanho, material, estilo, estado);
    }

    public boolean addCor(Cor c) { return peca.addCor(c); }
    public boolean addParteCorpo(ParteCorpo p) { return peca.addParteCorpo(p); }
    public boolean addOcasiao(Ocasiao o) { return peca.addOcasiao(o); }

    /**
     * @return lista dos dados em falta/inválidos (vazia se a peça for válida)
     */
    public List<String> getDadosEmFalta() {
        return peca.getDadosEmFalta();
    }

    /**
     * Extensão 12c: já existe uma peça com os mesmos dados?
     */
    public boolean existePecaIgual() {
        return guardaRoupa.existePecaIgual(peca);
    }

    public String getPecaAsString() {
        return peca != null ? peca.toStringDetalhado() : "";
    }

    public boolean registaPeca() {
        return guardaRoupa.registaPeca(peca);
    }
}
