package asignacionsalas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Administrador extends Responsables {


    private Perfil perfilAdmin;

    public void crearPerfilAdmin(String user, String pass) {
        Perfil perfil = new Perfil();
        perfil.setNombreUsuario(user);
        perfil.setPassword(pass);
        setAdmin(true);
        setPerfilAdmin(perfil);
    }

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

    public void menuAdmin(Administrador admin, Universidad universidad, Date iSemestre, Date fSemestre) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        int op = 0;
        System.out.println("MENU DE ADMIN");
        System.out.println("[1] Mostrar todas las reservas hechas por orden(Propuestas)");
        System.out.println("[2] Mostrar todas las reservas hechas (Confirmadas)");
        System.out.println("[2] Ver todos los incidentes");
        System.out.println("[3] Crear un nuevo estudiante o profesor");
        System.out.println("[4] Confirmar una reserva propuesta");
        System.out.println("[4] Cerrar sesion");
        System.out.print("Ingrese su opcion -> ");
        op = entrada.nextInt();
        switch (op) {
            case 1:
                System.out.println("A continuacion se mostraran todas las reservas hechas");
                ArrayList<Edificio> edificios = universidad.getEdificios();
                for(Edificio edificio: edificios){
                    ArrayList<SaladeClase> salas = edificio.getSalas();
                    for(SaladeClase sala: salas){
                        sala.getPropuestasOrdenadas();
                    }
                }
                break;
            case 4:
                System.out.println("Cerrando sesion...");
                break;
            default:
                throw new IllegalStateException("Valor inseperado: " + op);
        }
    }
    public Perfil getPerfilAdmin() {
        return perfilAdmin;
    }

    public void setPerfilAdmin(Perfil perfilAdmin) {
        this.perfilAdmin = perfilAdmin;
    }

}