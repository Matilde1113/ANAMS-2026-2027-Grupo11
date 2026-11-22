package org.Controller;

import org.Model.*;

import java.util.Arrays;
import java.util.List;

/**
 * Controller do UC9 - Editar Peça de Vestuário.
 * A edição é feita sobre uma cópia da peça: só depois da confirmação
 * o GuardaRoupa aplica as alterações à peça original.
 */
public class EditarPecaController {

    private final AssistenteVestuario av;
    private final GuardaRoupa guardaRoupa;
    private PecaVestuario original;
    private PecaVestuario copia;

    public EditarPecaController(AssistenteVestuario av) {
        this.av = av;
        this.guardaRoupa = av.getUtilizadorAtual().getGuardaRoupa();
    }

    public List<PecaVestuario> getListaPecas() {
        return guardaRoupa.getListaPecas();
    }

    /**
     * Seleciona a peça a editar e cria uma cópia para edição.
     *
     * @return os dados atuais da peça
     */
    public String selecionaPeca(PecaVestuario peca) {
        this.original = peca;
        this.copia = peca.copia();
        return peca.toStringDetalhado();
    }

    public List<Tamanho> getListaTamanhos() { return av.getRegistoTamanhos().getListaTamanhos(); }
    public List<Cor> getListaCores() { return av.getRegistoCores().getListaCores(); }
    public List<Material> getListaMateriais() { return av.getRegistoMateriais().getListaMateriais(); }
    public List<Estilo> getListaEstilos() { return av.getRegistoEstilos().getListaEstilos(); }
    public List<ParteCorpo> getListaPartesCorpo() { return av.getRegistoPartesCorpo().getListaPartesCorpo(); }
    public List<Ocasiao> getListaOcasioes() { return av.getRegistoOcasioes().getListaOcasioes(); }
    public List<EstadoPeca> getListaEstados() { return av.getRegistoEstados().getListaEstados(); }
    public List<String> getListaEstacoes() { return Arrays.asList(PecaVestuario.ESTACOES); }

    /**
     * Define os novos dados na cópia. As cores, partes do corpo e ocasiões
     * anteriores são removidas, pois as novas seleções substituem-nas.
     */
    public void setDados(String designacao, String estacao, Tamanho tamanho,
                         Material material, Estilo estilo, EstadoPeca estado) {
        copia.setDados(designacao, estacao, tamanho, material, estilo, estado);
        copia.limparListas();
    }

    public boolean addCor(Cor c) { return copia.addCor(c); }
    public boolean addParteCorpo(ParteCorpo p) { return copia.addParteCorpo(p); }
    public boolean addOcasiao(Ocasiao o) { return copia.addOcasiao(o); }

    public List<String> getDadosEmFalta() {
        return copia.getDadosEmFalta();
    }

    public String getPecaEditadaAsString() {
        return copia != null ? copia.toStringDetalhado() : "";
    }

    /**
     * Aplica as alterações à peça original.
     */
    public boolean atualizaPeca() {
        return guardaRoupa.atualizaPeca(original, copia);
    }
}
