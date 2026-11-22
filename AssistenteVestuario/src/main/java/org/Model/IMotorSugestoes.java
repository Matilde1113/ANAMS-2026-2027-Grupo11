package org.Model;

/**
 * Interface do motor de sugestões (Protected Variations).
 * O algoritmo de recomendação pode ser trocado sem alterar os controllers.
 */
public interface IMotorSugestoes {

    /**
     * Gera uma sugestão de combinação para o utilizador no contexto indicado.
     *
     * @return a sugestão gerada, ou null se não for possível formar uma combinação
     */
    Sugestao gerarSugestao(Utilizador u, Contexto ctx);
}
