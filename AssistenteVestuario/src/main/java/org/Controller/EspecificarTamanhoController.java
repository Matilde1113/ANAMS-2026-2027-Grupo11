package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.RegistoTamanhos;
import org.Model.Tamanho;

/**
 * Controller do UC17 - Especificar Tamanho.
 */
public class EspecificarTamanhoController {

    private final RegistoTamanhos registo;
    private Tamanho tamanho;

    public EspecificarTamanhoController(AssistenteVestuario av) {
        this.registo = av.getRegistoTamanhos();
    }

    /**
     * Cria um tamanho numérico.
     *
     * @return true se for válido (local e globalmente)
     */
    public boolean novoTamanhoNumerico(int valor) {
        this.tamanho = registo.novoTamanhoNumerico(valor);
        return registo.validaTamanho(this.tamanho);
    }

    /**
     * Cria um tamanho alfabético.
     *
     * @return true se for válido (local e globalmente)
     */
    public boolean novoTamanhoAlfabetico(String designacao) {
        this.tamanho = registo.novoTamanhoAlfabetico(designacao);
        return registo.validaTamanho(this.tamanho);
    }

    /**
     * @return true se o tamanho criado respeita os valores admitidos (validação local)
     */
    public boolean tamanhoValido() {
        return tamanho != null && tamanho.valida();
    }

    public String getTamanhoAsString() {
        return tamanho != null ? tamanho.toString() : "";
    }

    public boolean registaTamanho() {
        return registo.registaTamanho(this.tamanho);
    }
}
