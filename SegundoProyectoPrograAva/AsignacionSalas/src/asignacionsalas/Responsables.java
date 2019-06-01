package asignacionsalas;

import java.io.*;
import java.util.ArrayList;

public abstract class Responsables extends Universidad implements Serializable {

    private String nombreCompleto;

    private boolean isAdmin;

    public void realizarReserva(Responsables reservador){
        System.out.println("Ingrese edificio de la sala a reservar");
        System.out.print("Edificios disponibles: ");
        getAllEdificios();
        System.out.println("Reservando como: "+reservador.getNombreCompleto());
    }

    public void reportarIncidencia() {
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }


    /*
    public void verResponsable(){
        Estudiante estudiante = estudiantes.get(1);
        System.out.println(estudiante.getNombreCompleto());
    }*/


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }



    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
