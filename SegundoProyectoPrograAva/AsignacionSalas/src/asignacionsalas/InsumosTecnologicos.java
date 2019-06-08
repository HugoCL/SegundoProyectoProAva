package asignacionsalas;

import java.io.Serializable;

/***
 * Clase que contiene los datos de los insumos tecnologicos
 */

public class InsumosTecnologicos implements Serializable {
    private String estado;
    private int ID;
    private int Insumo;
    private static final long serialVersionUID = 1L;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getInsumo() {
        return Insumo;
    }

    public void setInsumo(int insumo) {
        Insumo = insumo;
    }
}
