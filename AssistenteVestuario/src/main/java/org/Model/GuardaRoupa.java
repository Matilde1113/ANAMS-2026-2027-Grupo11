package org.Model;

import org.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda-roupa de um utilizador: conjunto das suas peças de vestuário.
 * Cria, valida globalmente, guarda, atualiza e remove as peças (UC7 a UC10).
 */
public class GuardaRoupa {

    private final String dataCriacao;
    private final List<PecaVestuario> listaPecas;
    private int proximoCodigo;

    public GuardaRoupa() {
        this.dataCriacao = Utils.getDataHoje();
        this.listaPecas = new ArrayList<>();
        this.proximoCodigo = 1;
    }

    public String getDataCriacao() { return dataCriacao; }

    /**
     * Cria uma nova peça da categoria indicada (Creator).
     */
    public PecaVestuario novaPeca(CategoriaPeca categoria) {
        return new PecaVestuario(categoria);
    }

    /**
     * Regista a peça se for válida, atribuindo-lhe um código único.
     */
    public boolean registaPeca(PecaVestuario peca) {
        if (!validaPeca(peca)) return false;
        peca.setCodigo(proximoCodigo++);
        return addPeca(peca);
    }

    private boolean validaPeca(PecaVestuario peca) {
        return peca != null && peca.valida() && !listaPecas.contains(peca);
    }

    private boolean addPeca(PecaVestuario peca) {
        return listaPecas.add(peca);
    }

    /**
     * Validação global: verifica se já existe uma peça com os mesmos dados (UC7 12c).
     */
    public boolean existePecaIgual(PecaVestuario peca) {
        for (PecaVestuario p : listaPecas) {
            if (p != peca && p.temMesmosDados(peca)) return true;
        }
        return false;
    }

    public List<PecaVestuario> getListaPecas() {
        return new ArrayList<>(listaPecas);
    }

    public boolean isVazio() {
        return listaPecas.isEmpty();
    }

    /**
     * @param categoria categoria pretendida (null = todas)
     */
    public List<PecaVestuario> getPecasPorCategoria(CategoriaPeca categoria) {
        if (categoria == null) return getListaPecas();

        List<PecaVestuario> res = new ArrayList<>();
        for (PecaVestuario p : listaPecas) {
            if (p.getCategoria() == categoria) res.add(p);
        }
        return res;
    }

    /**
     * @return apenas as peças cujo estado indica que estão disponíveis
     */
    public List<PecaVestuario> getPecasDisponiveis() {
        List<PecaVestuario> res = new ArrayList<>();
        for (PecaVestuario p : listaPecas) {
            if (p.isDisponivel()) res.add(p);
        }
        return res;
    }

    public PecaVestuario getPeca(int codigo) {
        for (PecaVestuario p : listaPecas) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    /**
     * Valida globalmente a cópia editada e aplica as alterações à peça original (UC9).
     */
    public boolean atualizaPeca(PecaVestuario original, PecaVestuario copia) {
        if (original == null || copia == null) return false;
        if (!listaPecas.contains(original)) return false;
        if (!copia.valida()) return false;

        original.atualizarCom(copia);
        return true;
    }

    /**
     * Remove a peça do guarda-roupa (UC10).
     */
    public boolean removePeca(PecaVestuario peca) {
        return listaPecas.remove(peca);
    }
}
