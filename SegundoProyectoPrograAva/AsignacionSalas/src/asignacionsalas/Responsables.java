package asignacionsalas;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Responsables implements Serializable {

    private String nombreCompleto;

    private boolean isAdmin;

    private Perfil perfilUser;

    public void realizarReserva(Responsables reservador, Universidad universidad, Date iSemestre, Date fSemestre)
            throws ParseException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Reservando como: "+reservador.getNombreCompleto());
        System.out.print("Edificios disponibles: ");
        universidad.getAllEdificios();
        System.out.println();
        System.out.println("Ingrese edificio de la sala a reservar");
        String edificioEle = entrada.nextLine();
        for (Edificio edificio: universidad.getEdificios()) {
            if(edificio.getColor().equals(edificioEle)){
                System.out.println("\nEdificio encontrado!");
                System.out.println("En este edificio hay "+edificio.getCantSalas()+" salas y "+edificio.getCantLabs()+" laboratorios");
                System.out.print("Salas disponibles: ");
                edificio.printSalas();
                System.out.println();
                System.out.println("Laboratorios disponibles: ");
                edificio.printLabs();
                System.out.println();
                System.out.println("Desea reservar sala[1] o laboratorio[2] (Solo para profesores)?");
                int op = entrada.nextInt();
                switch (op){
                    case 1:
                        System.out.println("Elegiste Sala. Selecciona una de las siguientes:");
                        edificio.printSalas();
                        System.out.println();
                        entrada.nextLine();
                        System.out.println("Ingrese el nombre completo de la Sala, por ejemplo 'Sala 23' ");
                        String salaEle = entrada.nextLine();
                        for (SaladeClase sala: edificio.getSalas()) {
                            if (sala.getNombreSala().equals(salaEle)){
                                System.out.println("Ingrese cantidad de alumnos: ");
                                int cantAlumnos = entrada.nextInt();
                                if (cantAlumnos > sala.getCapacidad()){
                                    System.out.println("La cantidad de alumnos excede la capacidad de la sala. Reserva terminada");
                                }
                                else if (sala.isConfirmada()){
                                    System.out.println("Esta sala ya se encuentra con una reserva confirmada. Reserva terminada");
                                }
                                else {
                                    sala.reservarPropuesta(reservador, iSemestre, fSemestre);
                                }
                            }
                        }
                        break;
                    case 2:
                        if (reservador instanceof Estudiante && !reservador.isAdmin()){
                            System.out.println("No tienes el permiso para realizar esta acción");
                        }
                        else{
                        }
                        break;
                    default:
                        throw new IllegalStateException("Valor inesperado: " + op);
                }
            }
        }


    }
    //ELIMINAR ESTE METODO ¡SOLO PRUEBAS!
    public void getReservas(Universidad universidad){
        ArrayList<Edificio> edificios = universidad.getEdificios();
        Edificio edificio = edificios.get(0);
        ArrayList<SaladeClase> salas = edificio.getSalas();
        SaladeClase sala = salas.get(0);
        sala.printPropuestas();
    }

    public void reportarIncidencia() {
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void configPerfil(String user, String pass) {
        Perfil perfil = new Perfil();
        perfil.setAdmin(false);
        perfil.setNombreUsuario(user);
        perfil.setPassword(pass);
        this.perfilUser = perfil;
    }

    public Perfil getPerfil(){
        return perfilUser;
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
