package asignacionsalas;

/***
 * Clase que maneja a los empleados de DTI
 */
public class DTI {

    private String nombreCompleto;

    private boolean isOcupado;

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public boolean isOcupado() {
        return isOcupado;
    }

    public void setOcupado(boolean ocupado) {
        isOcupado = ocupado;
    }

}
