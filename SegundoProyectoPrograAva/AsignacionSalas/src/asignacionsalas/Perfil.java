package asignacionsalas;

import java.io.Serializable;

/***
 * Clase que almacena el perfil de un usuario o admin
 */

public class Perfil implements Serializable {

    private String nombreUsuario;

    private String password;

    private boolean isAdmin;

    public void verReservas() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
