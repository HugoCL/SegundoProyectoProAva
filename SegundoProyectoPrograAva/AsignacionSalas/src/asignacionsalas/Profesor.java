package asignacionsalas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Profesor extends Responsables {

    private Perfil perfilAdmin;


    public void crearPerfilAdmin(String user, String pass) {
        Perfil perfil = new Perfil();
        perfil.setNombreUsuario(user);
        perfil.setPassword(pass);
        setAdmin(true);
        setPerfilAdmin(perfil);
    }

    public void menu(Profesor profesor, Universidad universidad, Date iSemestre, Date fSemestre) throws ParseException {
        int op = 0;
        System.out.println("¡Hola "+getNombreCompleto()+"!");
        do{
            Scanner entrada = new Scanner(System.in);
            System.out.println("[1] Reportar incidencia");
            System.out.println("[2] Proponer reserva de Sala o Laboratorio");
            System.out.println("[2] Cerrar sesion");
            op = entrada.nextInt();
            if (op == 1){
                reportarIncidencia();
                System.out.println("Desea realizar otra operacion? 1 para si, 0 para no");

            }
            else if (op == 2){
                realizarReserva(profesor, universidad, iSemestre, fSemestre);
            }
            else if (op == 3){
                System.out.println("Cerrando sesion, gracias");
            }
            else{
                System.out.println("Opcion no valida!");
            }
        }while(op != 3);
    }

    public void menuAdmin(Universidad universidad){
        Scanner entrada = new Scanner(System.in);
        int op = 0;
        System.out.println("MENU DE ADMIN");
        System.out.println("[1] Mostrar todas las reservas hechas (Propuestas)");
        System.out.println("[2] Mostrar todas las reservas hechas (Confirmadas)");
        System.out.println("[2] Ver todos los incidentes");
        System.out.println("[3] Crear un nuevo estudiante o profesor");
        System.out.println("[4] Cerrar sesion");
        System.out.println("Ingrese su opcion -> ");
        op = entrada.nextInt();
        switch(op){
            case 1:
                System.out.println("A continuacion se mostraran todas las reservas hechas");
                ArrayList<Edificio> edificios = universidad.getEdificios();
                // se inicia con los Estudiantes (0) y luego los profesores(1)
                int flagEP = 0;
                while (flagEP != 2){
                    for (Edificio edificio: edificios) {
                        ArrayList<SaladeClase> salas = edificio.getSalas();
                        ArrayList<Laboratorio> laboratorios = edificio.getLaboratorios();
                        for (SaladeClase sala: salas) {
                            //SaladeClase reservacion = sala.get
                            for (Laboratorio laboratorio: laboratorios){

                            }
                        }
                    }
                }
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
