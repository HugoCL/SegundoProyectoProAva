package asignacionsalas;

import java.util.Scanner;

public class Administrativo extends Responsables {

    public void cambioSala(Universidad universidad) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Cambio de salas");
        System.out.println("Ingrese el edificio: ");
        System.out.print("Edificios disponibles: ");
        universidad.getAllEdificios();
        System.out.println();
        System.out.println("Ingrese edificio de la sala a reservar");
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
            }
        }
    }
}