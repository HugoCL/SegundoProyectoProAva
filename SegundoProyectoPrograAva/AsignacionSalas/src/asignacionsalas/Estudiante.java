package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/***
 * Clase que contiene los datos y funciones de un estudiante
 */

public class Estudiante extends Responsables implements Serializable {

    /***
     * Metodo que muestra el menu para el estudiante
     * @param estudiante El objeto que corresponde al estudiante actual
     * @param universidad la universidad en la que estudia
     * @param iSemestre inicio de semestre
     * @param fSemestre fin de semestre
     * @throws ParseException por si hay error de parseo
     */
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
                reportarIncidencia(universidad);
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
