package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.Cor;
import org.Model.RegistoCores;

/**
 * Controller do UC3 - Especificar Cor.
 */
public class EspecificarCorController {

    private final RegistoCores registo;
    private Cor cor;

    public EspecificarCorController(AssistenteVestuario av) {
        this.registo = av.getRegistoCores();
    }

    /**
     * Cria uma nova cor.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novaCor(String designacao, String codigoHex) {
        this.cor = registo.novaCor(designacao, codigoHex);
        return registo.validaCor(this.cor);
    }

    /**
     * @return true se o formato dos dados estiver correto (validação local)
     */
    public boolean dadosValidos() {
        return cor != null && cor.valida();
    }

    public String getCorAsString() {
        return cor != null ? cor.toString() : "";
    }

    public boolean registaCor() {
        return registo.registaCor(this.cor);
    }
}
