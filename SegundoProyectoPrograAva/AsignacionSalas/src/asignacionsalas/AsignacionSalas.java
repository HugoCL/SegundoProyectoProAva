/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asignacionsalas;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class AsignacionSalas {

    /**
     * Clase main
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        Scanner entrada = new Scanner(System.in);
        Universidad universidad = new Universidad();
        System.out.println("Ingrese inicio del semestre: FORMATO dd-mm-aaaa (No usar este formato lanzará una excepcion)");
        String fechaI = entrada.nextLine();
        SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy");
        Date inicioSemestre = fechaForm.parse(fechaI);
        System.out.println("Ingrese final del semestre: FORMATO dd-mm-aaaa (No usar este formato lanzará una excepcion)");
        String fechaF = entrada.nextLine();
        Date finalSemestre = fechaForm.parse(fechaF);
        universidad.setInicioSemestre(inicioSemestre);
        universidad.setFinalSemestre(finalSemestre);
        System.out.println("Estos datos pueden ser cambiados luegos por el administrador");
        universidad.setNombre("Universidad de Talca");
        System.out.println("Bienvenid@ a la "+universidad.getNombre()+"");
        System.out.println("Cargando datos...");
        universidad.DesSerializar();
        int rolM = 0;
        do{
            System.out.println("Selecciona que clase de persona eres dentro de la universidad para ver tu menú");
            System.out.println("[1] Estudiante");
            System.out.println("[2] Administrador");
            System.out.println("[3] Profesor");
            System.out.println("[4] SERIALIZAR");
            System.out.println("[4] Cerrar sistema");
            rolM = entrada.nextInt();
            switch (rolM){
                case 1:
                    System.out.println("Ingrese su nombre de usuario, que es su nombre completo por defecto" +
                            "(Sensible a mayusculas y minisculas)");
                    entrada.nextLine();
                    String nombre = entrada.nextLine();
                    Estudiante estudiante = (Estudiante) universidad.getResponsable(1, nombre);
                    if (estudiante != null ){
                        System.out.println("Ingrese su password");
                        String pass = entrada.nextLine();
                        if (pass.equals(estudiante.getPerfil().getPassword())){
                            estudiante.menu(estudiante, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
                        }
                        else{
                            System.out.println("Contraseña incorrecta!");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Ingrese su nombre de usuario(Sensible a mayusculas y minisculas)");
                    entrada.nextLine();
                    String nombreAd = entrada.nextLine();
                    Administrador administrador = (Administrador) universidad.getResponsable(2, nombreAd);
                    if (administrador != null){
                        System.out.println("Ingrese su password");
                        String pass = entrada.nextLine();
                        if (pass.equals(administrador.getPerfilAdmin().getPassword())){
                            administrador.menuAdmin(universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
                        }
                        else{
                            System.out.println("La contraseña es incorrecta!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese su nombre completo para iniciar sesión, que por defecto es su nombre completo " +
                            "(Sensible a mayusculas y minisculas)");
                    entrada.nextLine();
                    String nombrePro = entrada.nextLine();
                    Profesor profesor = (Profesor) universidad.getResponsable(3, nombrePro);
                    if (profesor != null){
                        System.out.println("Ingrese su password");
                        String pass = entrada.nextLine();
                        if (pass.equals(profesor.getPerfil().getPassword())){
                            profesor.menu(profesor, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
                        }
                        else{
                            System.out.println("Contraseña incorrecta!");
                        }
                    }
                    break;
                case 4:
                    System.out.println("SE ESTA SERIALIZANDO...");
                    universidad.Serializar();
                    System.out.println("Listo!");
                    break;
              }
        }while(rolM != 4);

        System.out.println("Cerrando sistema, ¡muchas gracias por usarlo!");
    }
    
}
