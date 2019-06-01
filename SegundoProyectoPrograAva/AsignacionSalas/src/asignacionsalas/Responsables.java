package asignacionsalas;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Responsables implements Serializable {

    private String nombreCompleto;

    private boolean isAdmin;

    public void realizarReserva(Responsables reservador, Universidad universidad) throws ParseException {
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
                System.out.println("Desea reservar sala[1] o laboratorio[2]?");
                int op = entrada.nextInt();
                switch (op){
                    case 1:
                        System.out.println("Elegiste Sala. Selecciona una de las siguientes.");
                        edificio.printSalas();
                        System.out.println();
                        entrada.nextLine();
                        System.out.println("Ingrese el nombre completo de la Sala, por ejemplo 'Sala 23' ");
                        String salaEle = entrada.nextLine();
                        System.out.println(salaEle);
                        for (SaladeClase sala: edificio.getSalas()) {
                            if (sala.getNombreSala().equals(salaEle)){
                                System.out.println("Yas");
                                sala.reservarPropuesta(reservador);
                            }
                        }
                }
            }
        }


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
