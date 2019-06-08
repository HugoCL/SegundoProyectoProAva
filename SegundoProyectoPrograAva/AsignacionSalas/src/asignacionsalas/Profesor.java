package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/***
 * Clase que contiene la info y los metodos para un profesor
 */

public class Profesor extends Responsables implements Serializable {


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
                reportarIncidencia(universidad);
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
}
