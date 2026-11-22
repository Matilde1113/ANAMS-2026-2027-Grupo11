package org.Utils;

import org.Model.*;

/**
 * Carrega dados de teste para demonstrar a aplicação (Especificação Suplementar).
 * Cria dados de referência, um utilizador de teste com peças, uma combinação
 * e algumas sugestões em estados diferentes.
 */
public class DadosTeste {

    public static final String EMAIL_TESTE = "utilizador@av.pt";
    public static final String PASSWORD_TESTE = "teste123";

    public static void carregarDadosDeTeste(AssistenteVestuario av) {

        // ---------- Estilos (UC2) ----------
        RegistoEstilos re = av.getRegistoEstilos();
        re.registaEstilo(re.novoEstilo("Clássico", "Peças intemporais e elegantes"));
        re.registaEstilo(re.novoEstilo("Casual", "Confortável para o dia a dia"));
        re.registaEstilo(re.novoEstilo("Desportivo", "Peças para atividade física"));
        re.registaEstilo(re.novoEstilo("Minimalista", "Linhas simples e cores neutras"));

        // ---------- Cores (UC3) ----------
        RegistoCores rc = av.getRegistoCores();
        rc.registaCor(rc.novaCor("Preto", "#000000"));
        rc.registaCor(rc.novaCor("Branco", "#FFFFFF"));
        rc.registaCor(rc.novaCor("Azul", "#1E3A8A"));
        rc.registaCor(rc.novaCor("Bege", "#D6C6A8"));
        rc.registaCor(rc.novaCor("Vermelho", "#B91C1C"));

        // ---------- Materiais (UC4) ----------
        RegistoMateriais rm = av.getRegistoMateriais();
        rm.registaMaterial(rm.novoMaterial("Algodão", "Fibra natural, fresca"));
        rm.registaMaterial(rm.novoMaterial("Ganga", "Tecido de algodão resistente"));
        rm.registaMaterial(rm.novoMaterial("Lã", "Fibra natural quente"));
        rm.registaMaterial(rm.novoMaterial("Pele", "Couro natural ou sintético"));
        rm.registaMaterial(rm.novoMaterial("Poliéster", "Fibra sintética"));

        // ---------- Categorias (UC5) ----------
        RegistoCategorias rcat = av.getRegistoCategorias();
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("T-shirt", "Peça de cima de manga curta"));
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("Camisa", "Peça de cima com botões"));
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("Calça", "Peça de baixo comprida"));
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("Saia", "Peça de baixo"));
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("Vestido", "Peça única que cobre tronco e pernas"));
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("Casaco", "Peça exterior"));
        rcat.registaCategoriaPeca(rcat.novaCategoriaPeca("Calçado", "Sapatos, sapatilhas, botas"));

        // ---------- Ocasiões (UC6) ----------
        RegistoOcasioes ro = av.getRegistoOcasioes();
        ro.registaOcasiao(ro.novaOcasiao("Trabalho/Aulas", "Dia normal de trabalho ou aulas", 3));
        ro.registaOcasiao(ro.novaOcasiao("Saída informal", "Passeio ou café com amigos", 1));
        ro.registaOcasiao(ro.novaOcasiao("Jantar", "Jantar num restaurante", 4));
        ro.registaOcasiao(ro.novaOcasiao("Casamento", "Ocasião especial / cerimónia", 5));

        // ---------- Partes do corpo (UC16) ----------
        RegistoPartesCorpo rp = av.getRegistoPartesCorpo();
        rp.registaParteCorpo(rp.novaParteCorpo("Pescoço", "Zona do pescoço"));
        rp.registaParteCorpo(rp.novaParteCorpo("Tronco", "Peito e costas"));
        rp.registaParteCorpo(rp.novaParteCorpo("Braços", "Braços"));
        rp.registaParteCorpo(rp.novaParteCorpo("Pernas", "Pernas"));
        rp.registaParteCorpo(rp.novaParteCorpo("Pés", "Pés"));
        rp.registaParteCorpo(rp.novaParteCorpo("Exterior", "Camada exterior (casacos)"));

        // ---------- Tamanhos (UC17) ----------
        RegistoTamanhos rt = av.getRegistoTamanhos();
        for (String t : TamanhoAlfabetico.VALORES) {
            rt.registaTamanho(rt.novoTamanhoAlfabetico(t));
        }
        for (int n = 36; n <= 44; n++) {
            rt.registaTamanho(rt.novoTamanhoNumerico(n));
        }

        // ---------- Estados da peça (UC18) ----------
        RegistoEstados rest = av.getRegistoEstados();
        rest.registaEstado(rest.novoEstado("Disponível", "Pronta a usar", true));
        rest.registaEstado(rest.novoEstado("Em lavagem", "Na lavandaria / a lavar", false));
        rest.registaEstado(rest.novoEstado("Emprestada", "Emprestada a outra pessoa", false));
        rest.registaEstado(rest.novoEstado("Indisponível", "Danificada ou guardada", false));

        // ---------- Utilizador de teste (UC1) ----------
        RegistoUtilizadores ru = av.getRegistoUtilizadores();
        Utilizador u = ru.novoUtilizador();
        u.setNome("Utilizador de Teste");
        u.setEmail(EMAIL_TESTE);
        u.setPassword(PASSWORD_TESTE);
        ru.registaUtilizador(u);

        // ---------- Peças do utilizador (UC7) ----------
        GuardaRoupa gr = u.getGuardaRoupa();
        criarPeca(av, gr, "T-shirt", "T-shirt branca básica", "Todas", "M", "Algodão", "Casual", "Disponível",
                new String[]{"Branco"}, new String[]{"Tronco", "Braços"}, new String[]{"Saída informal", "Trabalho/Aulas"});
        criarPeca(av, gr, "Camisa", "Camisa azul de algodão", "Primavera", "M", "Algodão", "Clássico", "Disponível",
                new String[]{"Azul"}, new String[]{"Tronco", "Braços", "Pescoço"}, new String[]{"Trabalho/Aulas", "Jantar"});
        criarPeca(av, gr, "Calça", "Calças de ganga", "Todas", "38", "Ganga", "Casual", "Disponível",
                new String[]{"Azul"}, new String[]{"Pernas"}, new String[]{"Saída informal", "Trabalho/Aulas"});
        criarPeca(av, gr, "Calça", "Calças pretas de fazenda", "Outono", "38", "Lã", "Clássico", "Disponível",
                new String[]{"Preto"}, new String[]{"Pernas"}, new String[]{"Trabalho/Aulas", "Jantar", "Casamento"});
        criarPeca(av, gr, "Vestido", "Vestido vermelho", "Verão", "S", "Poliéster", "Clássico", "Disponível",
                new String[]{"Vermelho"}, new String[]{"Tronco", "Pernas"}, new String[]{"Jantar", "Casamento"});
        criarPeca(av, gr, "Casaco", "Blazer bege", "Outono", "M", "Lã", "Clássico", "Disponível",
                new String[]{"Bege"}, new String[]{"Exterior"}, new String[]{"Trabalho/Aulas", "Jantar", "Casamento"});
        criarPeca(av, gr, "Casaco", "Casaco de pele preto", "Inverno", "M", "Pele", "Casual", "Em lavagem",
                new String[]{"Preto"}, new String[]{"Exterior"}, new String[]{"Saída informal", "Jantar"});
        criarPeca(av, gr, "Calçado", "Sapatilhas brancas", "Todas", "38", "Pele", "Casual", "Disponível",
                new String[]{"Branco"}, new String[]{"Pés"}, new String[]{"Saída informal", "Trabalho/Aulas"});
        criarPeca(av, gr, "Calçado", "Sapatos pretos", "Todas", "38", "Pele", "Clássico", "Disponível",
                new String[]{"Preto"}, new String[]{"Pés"}, new String[]{"Trabalho/Aulas", "Jantar", "Casamento"});

        // ---------- Combinação manual (UC13) ----------
        Combinacao c = u.novaCombinacao(ro.getOcasiao("Saída informal"));
        c.setDesignacao("Look de fim de semana");
        c.addPeca(gr.getPeca(1));
        c.addPeca(gr.getPeca(3));
        c.addPeca(gr.getPeca(8));
        u.registaCombinacao(c);

        // ---------- Sugestões (UC11, UC14, UC12) ----------
        Sugestao s1 = gerar(av, u, "Trabalho/Aulas", "10-11-2026", "Porto", 16, "Sol");
        if (s1 != null) {
            s1.aceitar();
            s1.setAvaliacao(s1.novaAvaliacao(4, "Gostei, confortável para o dia todo"));
        }

        Sugestao s2 = gerar(av, u, "Saída informal", "14-11-2026", "Porto", 18, "Nublado");
        if (s2 != null) s2.rejeitar("Demasiado informal");

        Sugestao s3 = gerar(av, u, "Jantar", "20-11-2026", "Lisboa", 15, "Chuva");
        if (s3 != null) s3.aceitar();   // aceite, ainda por avaliar

        gerar(av, u, "Casamento", "05-12-2026", "Braga", 12, "Sol");   // fica pendente
    }

    private static void criarPeca(AssistenteVestuario av, GuardaRoupa gr, String categoria, String designacao,
                                  String estacao, String tamanho, String material, String estilo, String estado,
                                  String[] cores, String[] partes, String[] ocasioes) {

        PecaVestuario p = gr.novaPeca(av.getRegistoCategorias().getCategoriaPeca(categoria));
        p.setDados(designacao, estacao,
                av.getRegistoTamanhos().getTamanho(tamanho),
                av.getRegistoMateriais().getMaterial(material),
                av.getRegistoEstilos().getEstilo(estilo),
                av.getRegistoEstados().getEstado(estado));

        for (String cor : cores) p.addCor(av.getRegistoCores().getCor(cor));
        for (String parte : partes) p.addParteCorpo(av.getRegistoPartesCorpo().getParteCorpo(parte));
        for (String oc : ocasioes) p.addOcasiao(av.getRegistoOcasioes().getOcasiao(oc));

        gr.registaPeca(p);
    }

    private static Sugestao gerar(AssistenteVestuario av, Utilizador u, String ocasiao, String data,
                                  String local, double temperatura, String tempo) {

        Contexto ctx = u.getHistorico().novoContexto(av.getRegistoOcasioes().getOcasiao(ocasiao));
        ctx.setDados(data, local, temperatura, tempo);

        Sugestao s = av.getMotorSugestoes().gerarSugestao(u, ctx);
        if (s != null) u.getHistorico().registaSugestao(s);
        return s;
    }
}
