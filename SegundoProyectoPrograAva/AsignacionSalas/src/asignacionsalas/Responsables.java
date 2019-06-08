package asignacionsalas;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/***
 * Clase abstracta que da el modelo de estudiante, profesor y admin
 */

public abstract class Responsables implements Serializable {

    private String nombreCompleto;

    private boolean isAdmin;

    private Perfil perfilUser;

    /***
     * Metodo que se encarga de realizar un reserva de una sala o un laboratorio
     * @param reservador Es el objeto de la persona que realiza la reserva
     * @param universidad Es la universidad en la que se trabaja
     * @param iSemestre Inicio semestre
     * @param fSemestre Fin semestre
     * @throws ParseException Por si hay error de parseo
     */
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
                            System.out.println("Elegiste Lab. Selecciona una de las siguientes:");
                            edificio.printLabs();
                            System.out.println();
                            entrada.nextLine();
                            System.out.println("Ingrese el nombre completo del Lab");
                            String labEle = entrada.nextLine();
                            for (Laboratorio lab: edificio.getLaboratorios()) {
                                if (lab.getNombreSala().equals(labEle)) {
                                    lab.reservarPropuesta(reservador, iSemestre, fSemestre);
                                }
                            }
                        }
                        break;
                    default:
                        throw new IllegalStateException("Valor inesperado: " + op);
                }
            }
        }


    }

    /***
     * Metodo para realizar un informe de incidencia
     * @param universidad Universidad actual en la que se trabaja
     */
    public void reportarIncidencia(Universidad universidad) {
        System.out.println("Se inicio el proceso de reporte de incidencia");
        Scanner entrada = new Scanner(System.in);
        System.out.print("Edificios disponibles: ");
        universidad.getAllEdificios();
        System.out.println();
        System.out.println("Ingrese edificio de la sala que tiene la incidencia");
        String edificioEle = entrada.nextLine();
        for (Edificio edificio : universidad.getEdificios()) {
            if (edificio.getColor().equals(edificioEle)) {
                System.out.println("\nEdificio encontrado!");
                System.out.println("En este edificio hay " + edificio.getCantSalas() + " salas y " + edificio.getCantLabs() + " laboratorios");
                System.out.print("Salas disponibles: ");
                edificio.printSalas();
                System.out.println();
                System.out.println("Laboratorios disponibles: ");
                edificio.printLabs();
                System.out.println();
                System.out.println("Desea reportar un problema en una sala[1] o laboratorio[2]?");
                int op = entrada.nextInt();
                if (op == 1){
                    System.out.println("Elegiste Sala. Selecciona una de las siguientes:");
                    edificio.printSalas();
                    System.out.println();
                    entrada.nextLine();
                    System.out.println("Ingrese el nombre completo de la Sala, por ejemplo 'Sala 23' ");
                    String salaEle = entrada.nextLine();
                    int exito = 0;
                    for (SaladeClase sala: edificio.getSalas()) {
                        if (sala.getNombreSala().equals(salaEle)) {
                            sala.registrarIncidenciaS(universidad);
                            exito = 1;
                        }
                    }
                    if (exito == 0){
                        System.out.println("No se encontro esa sala :/");
                    }
                }
                else if (op == 2){
                        System.out.println("Elegiste Lab. Selecciona una de las siguientes:");
                        edificio.printLabs();
                        System.out.println();
                        entrada.nextLine();
                        System.out.println("Ingrese el nombre completo del Lab");
                        String labEle = entrada.nextLine();
                        int exito = 0;
                        for (Laboratorio lab: edificio.getLaboratorios()) {
                            if (lab.getNombreSala().equals(labEle)) {
                                lab.registrarIncidenciaS(universidad);
                                exito = 1;
                            }
                        }
                        if (exito == 0){
                            System.out.println("No se encontro esa sala :/");
                        }
                }
                else{
                    System.out.println("Tipo de sala incorrecto");
                }
            }
        }
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /***
     * Metodo que crea el perfil para X usuario
     * @param user Nombre de usuario
     * @param pass Contrasenya
     */
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
