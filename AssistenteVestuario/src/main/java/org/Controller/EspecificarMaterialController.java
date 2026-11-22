package org.Controller;

import org.Model.AssistenteVestuario;
import org.Model.Material;
import org.Model.RegistoMateriais;

/**
 * Controller do UC4 - Especificar Material.
 * Coordena a UI com o RegistoMateriais (padrão GRASP Controller).
 */
public class EspecificarMaterialController {

    private final RegistoMateriais registo;
    private Material material;

    public EspecificarMaterialController(AssistenteVestuario av) {
        this.registo = av.getRegistoMateriais();
    }

    /**
     * Cria um novo material com os dados introduzidos.
     *
     * @return true se os dados forem válidos (local e globalmente)
     */
    public boolean novoMaterial(String designacao, String descricao) {
        this.material = registo.novoMaterial(designacao, descricao);
        return registo.validaMaterial(this.material);
    }

    public boolean existeMaterial(String designacao) {
        return registo.getMaterial(designacao) != null;
    }

    public String getMaterialAsString() {
        return material != null ? material.toString() : "";
    }

    public boolean registaMaterial() {
        return registo.registaMaterial(this.material);
    }
}
