package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Peça de vestuário do guarda-roupa de um utilizador (UC7 a UC10).
 * Conhece o seu tamanho, estado, cores (1..*), material, estilo,
 * partes do corpo (1..*) e ocasiões (1..*) e valida localmente os seus dados.
 */
public class PecaVestuario {

    public static final String[] ESTACOES = {"Primavera", "Verão", "Outono", "Inverno", "Todas"};

    private int codigo;
    private String designacao;
    private String estacao;
    private CategoriaPeca categoria;
    private Tamanho tamanho;
    private Material material;
    private Estilo estilo;
    private EstadoPeca estado;
    private List<Cor> cores;
    private List<ParteCorpo> partesCorpo;
    private List<Ocasiao> ocasioes;

    private static final String STRING_POR_OMISSAO = "a definir";

    /**
     * Cria uma peça de uma dada categoria (a categoria não é alterada no UC9).
     */
    public PecaVestuario(CategoriaPeca categoria) {
        this.codigo = 0;
        this.designacao = STRING_POR_OMISSAO;
        this.estacao = STRING_POR_OMISSAO;
        this.categoria = categoria;
        this.tamanho = null;
        this.material = null;
        this.estilo = null;
        this.estado = null;
        this.cores = new ArrayList<>();
        this.partesCorpo = new ArrayList<>();
        this.ocasioes = new ArrayList<>();
    }

    /**
     * Construtor de cópia (usado na edição: o UC9 trabalha sobre uma cópia).
     */
    public PecaVestuario(PecaVestuario outra) {
        this.codigo = outra.codigo;
        this.designacao = outra.designacao;
        this.estacao = outra.estacao;
        this.categoria = outra.categoria;
        this.tamanho = outra.tamanho;
        this.material = outra.material;
        this.estilo = outra.estilo;
        this.estado = outra.estado;
        this.cores = new ArrayList<>(outra.cores);
        this.partesCorpo = new ArrayList<>(outra.partesCorpo);
        this.ocasioes = new ArrayList<>(outra.ocasioes);
    }

    /**
     * Cria uma cópia de si própria para edição (Creator / IE - UC9).
     */
    public PecaVestuario copia() {
        return new PecaVestuario(this);
    }

    // Getters
    public int getCodigo() { return codigo; }
    public String getDesignacao() { return designacao; }
    public String getEstacao() { return estacao; }
    public CategoriaPeca getCategoria() { return categoria; }
    public Tamanho getTamanho() { return tamanho; }
    public Material getMaterial() { return material; }
    public Estilo getEstilo() { return estilo; }
    public EstadoPeca getEstado() { return estado; }
    public List<Cor> getCores() { return new ArrayList<>(cores); }
    public List<ParteCorpo> getPartesCorpo() { return new ArrayList<>(partesCorpo); }
    public List<Ocasiao> getOcasioes() { return new ArrayList<>(ocasioes); }

    // Setters
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setDesignacao(String designacao) { this.designacao = designacao; }
    public void setEstacao(String estacao) { this.estacao = estacao; }
    public void setTamanho(Tamanho tamanho) { this.tamanho = tamanho; }
    public void setMaterial(Material material) { this.material = material; }
    public void setEstilo(Estilo estilo) { this.estilo = estilo; }
    public void setEstado(EstadoPeca estado) { this.estado = estado; }

    /**
     * Define os dados simples da peça.
     */
    public void setDados(String designacao, String estacao, Tamanho tamanho,
                         Material material, Estilo estilo, EstadoPeca estado) {
        this.designacao = designacao;
        this.estacao = estacao;
        this.tamanho = tamanho;
        this.material = material;
        this.estilo = estilo;
        this.estado = estado;
    }

    public boolean addCor(Cor c) {
        if (c == null || cores.contains(c)) return false;
        return cores.add(c);
    }

    public boolean addParteCorpo(ParteCorpo p) {
        if (p == null || partesCorpo.contains(p)) return false;
        return partesCorpo.add(p);
    }

    public boolean addOcasiao(Ocasiao o) {
        if (o == null || ocasioes.contains(o)) return false;
        return ocasioes.add(o);
    }

    /**
     * Remove as cores, partes do corpo e ocasiões
     * (no UC9 as novas seleções substituem as anteriores).
     */
    public void limparListas() {
        cores.clear();
        partesCorpo.clear();
        ocasioes.clear();
    }

    /**
     * Copia para esta peça os dados de outra (usado pelo GuardaRoupa
     * para aplicar as alterações feitas na cópia).
     */
    public void atualizarCom(PecaVestuario copia) {
        this.designacao = copia.designacao;
        this.estacao = copia.estacao;
        this.tamanho = copia.tamanho;
        this.material = copia.material;
        this.estilo = copia.estilo;
        this.estado = copia.estado;
        this.cores = new ArrayList<>(copia.cores);
        this.partesCorpo = new ArrayList<>(copia.partesCorpo);
        this.ocasioes = new ArrayList<>(copia.ocasioes);
    }

    public static boolean estacaoValida(String estacao) {
        for (String e : ESTACOES) {
            if (e.equalsIgnoreCase(estacao)) return true;
        }
        return false;
    }

    /**
     * @return true se o estado da peça indicar que está disponível
     */
    public boolean isDisponivel() {
        return estado != null && estado.isDisponivel();
    }

    public boolean isAdequadaA(Ocasiao ocasiao) {
        return ocasioes.contains(ocasiao);
    }

    public boolean partilhaParteCorpoCom(PecaVestuario outra) {
        for (ParteCorpo p : partesCorpo) {
            if (outra.partesCorpo.contains(p)) return true;
        }
        return false;
    }

    /**
     * Validação local: todos os dados são obrigatórios, com pelo menos
     * uma cor, uma parte do corpo e uma ocasião.
     */
    public boolean valida() {
        return getDadosEmFalta().isEmpty();
    }

    /**
     * @return lista com a descrição dos dados em falta ou inválidos (vazia se a peça for válida)
     */
    public List<String> getDadosEmFalta() {
        List<String> falta = new ArrayList<>();
        if (designacao == null || designacao.isBlank() || designacao.equals(STRING_POR_OMISSAO))
            falta.add("designação");
        if (!estacaoValida(estacao)) falta.add("estação do ano");
        if (categoria == null) falta.add("categoria");
        if (tamanho == null || !tamanho.valida()) falta.add("tamanho");
        if (material == null) falta.add("material");
        if (estilo == null) falta.add("estilo");
        if (estado == null) falta.add("estado");
        if (cores.isEmpty()) falta.add("pelo menos uma cor");
        if (partesCorpo.isEmpty()) falta.add("pelo menos uma parte do corpo");
        if (ocasioes.isEmpty()) falta.add("pelo menos uma ocasião");
        return falta;
    }

    /**
     * Verifica se outra peça tem os mesmos dados (para detetar duplicados - UC7 12c).
     */
    public boolean temMesmosDados(PecaVestuario outra) {
        if (outra == null) return false;
        return designacao.equalsIgnoreCase(outra.designacao)
                && categoria == outra.categoria
                && tamanho == outra.tamanho
                && material == outra.material
                && estilo == outra.estilo
                && cores.containsAll(outra.cores) && outra.cores.containsAll(cores);
    }

    /**
     * Linha curta, usada nas listas.
     */
    @Override
    public String toString() {
        return "[" + codigo + "] " + designacao
                + " (" + (categoria != null ? categoria.getDesignacao() : "-") + ")"
                + (isDisponivel() ? "" : " - " + (estado != null ? estado.getDesignacao() : "sem estado"));
    }

    /**
     * Todos os detalhes da peça (UC8).
     */
    public String toStringDetalhado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Peça de vestuário:\n");
        sb.append("Código: ").append(codigo).append("\n");
        sb.append("Designação: ").append(designacao).append("\n");
        sb.append("Categoria: ").append(categoria != null ? categoria.getDesignacao() : "-").append("\n");
        sb.append("Estação: ").append(estacao).append("\n");
        sb.append("Tamanho: ").append(tamanho != null ? tamanho : "-").append("\n");
        sb.append("Material: ").append(material != null ? material.getDesignacao() : "-").append("\n");
        sb.append("Estilo: ").append(estilo != null ? estilo.getDesignacao() : "-").append("\n");
        sb.append("Estado: ").append(estado != null ? estado.getDesignacao() : "-").append("\n");

        List<String> nomesCores = new ArrayList<>();
        for (Cor c : cores) nomesCores.add(c.getDesignacao());
        sb.append("Cores: ").append(String.join(", ", nomesCores)).append("\n");

        List<String> nomesPartes = new ArrayList<>();
        for (ParteCorpo p : partesCorpo) nomesPartes.add(p.getDesignacao());
        sb.append("Partes do corpo: ").append(String.join(", ", nomesPartes)).append("\n");

        List<String> nomesOcasioes = new ArrayList<>();
        for (Ocasiao o : ocasioes) nomesOcasioes.add(o.getDesignacao());
        sb.append("Ocasiões: ").append(String.join(", ", nomesOcasioes)).append("\n");

        return sb.toString();
    }
}
