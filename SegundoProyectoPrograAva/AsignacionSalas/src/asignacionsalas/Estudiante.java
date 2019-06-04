package asignacionsalas;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Estudiante extends Responsables {

    public void crearPerfilAdmin(String user, String pass) {
        PerfilAdmin perfil = new PerfilAdmin();
        perfil.setNombreUsuario(user);
        perfil.setPassword(pass);
        setAdmin(true);
        setPerfilAdmin(perfil);
    }

    public void menu(Estudiante estudiante, Universidad universidad, Date iSemestre, Date fSemestre) throws ParseException {
        int op = 0;
        System.out.println("¡Hola "+getNombreCompleto()+"!");
        do{
            Scanner entrada = new Scanner(System.in);
            System.out.println("[1] Reportar incidencia");
            System.out.println("[2] Reservar sala");
            System.out.println("[3] Cerrar sesion");
            op = entrada.nextInt();
            if (op == 1){
                reportarIncidencia();
                System.out.println("Desea realizar otra operacion? 1 para si, 0 para no");

            }
            else if (op == 2){
                System.out.println("Recuerda que solo puedes reservar salas");
                realizarReserva(estudiante, universidad, iSemestre, fSemestre);
            }
            else if (op == 3){
                System.out.println("Cerrando sesion, gracias");
            }
            else{
                System.out.println("Opcion no valida!");
            }
        }while(op != 3);
    }
}
