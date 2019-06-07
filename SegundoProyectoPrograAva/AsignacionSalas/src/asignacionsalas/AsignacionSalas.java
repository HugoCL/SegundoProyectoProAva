/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asignacionsalas;

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
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
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
        universidad.setCantEdificios(0);
        universidad.crearEdificio("Naranjo");
        universidad.crearEdificio("Verde");
        universidad.crearEdificio("Amarillo");
        universidad.crearEdificio("Cafe");


        universidad.crearSala("Naranjo", 0, "Sala 11", "Sala Comun", 50,
                0, 1, 50, 1, 1, 50);
        universidad.crearSala("Naranjo", 0, "Sala 12", "Sala Comun", 48,
                0, 1, 48, 1,  1, 48);
        universidad.crearSala("Naranjo", 0, "Sala 13", "Sala Comun", 61,
                0, 1, 61, 1,  1, 61);
        universidad.crearSala("Naranjo", 0, "Sala 14", "Sala Comun", 55,
                0, 1, 55, 1,  1, 55);
        universidad.crearSala("Naranjo", 0, "Sala 21", "Sala Comun", 42,
                0, 1, 42, 1,  1, 42);

        universidad.crearSala("Verde", 1, "Lab 1", "Sala de Computadores", 42,
                42, 1, 15, 1,  1, 42);
        universidad.crearSala("Verde", 1, "Lab 2", "Sala de Computadores", 30,
                30, 1, 10, 1,  1, 30);
        universidad.crearSala("Verde", 1, "Lab 3", "Sala de Computadores", 20,
                20, 0, 1, 0,  0, 20);

        universidad.crearSala("Amarillo", 0, "S1", "Sala Comun", 40,
                0, 1, 40, 1,  1, 40);
        universidad.crearSala("Amarillo", 0, "S2", "Sala Comun", 44,
                0, 1, 44, 1,  1, 44);

        universidad.crearSala("Cafe", 0, "103", "Sala Comun", 38,
                0, 1, 38, 1, 1, 38);
        universidad.crearSala("Cafe", 0, "104", "Sala Comun", 45,
                0, 1, 45, 1,  1, 45);
        universidad.crearSala("Cafe", 0, "104", "Sala Comun", 42,
                0, 1, 42, 1,  1, 42);
        universidad.crearSala("Cafe", 0, "105", "Sala Comun", 51,
                0, 1, 51, 1,  1, 51);
        universidad.crearSala("Cafe", 0, "106", "Sala Comun", 56,
                0, 1, 56, 1,  1, 56);

        //universidad.verDatos();

        universidad.registrarResponsable("Hugo Castro", 1, "Hugo Castro", "1234");
        universidad.registrarResponsable("Cesar", 1, "Cesar Alvear", "1234");
        universidad.registrarResponsable("Ariel Rojas", 1, "Ariel Rojas", "1234");
        universidad.registrarResponsable("Ruth Garrido", 3, "Ruth Garrido", "1234");
        universidad.registrarResponsable("Luis Silvestre", 2, "Luis Silvestre", "1234");
        // Creacion del perfil de admin
        universidad.registrarResponsable("Rodrigo Pavez", 2, "utalca", "utalca123");
        // Fin creacion
        int rolM = 0;
        do{
            System.out.println("Selecciona que clase de persona eres dentro de la universidad para ver tu menú");
            System.out.println("[1] Estudiante");
            System.out.println("[2] Administrador");
            System.out.println("[3] Profesor");
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
                            administrador.menuAdmin(administrador, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
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
              }
        }while(rolM != 4);

        System.out.println("Cerrando sistema, ¡muchas gracias por usarlo!");
        //Responsables yo = universidad.getResponsable(1, "Hugo Castro");
        //yo.realizarReserva(yo, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
        //yo.getReservas(universidad);
        //responsables.verResponsable();
        //universidad.Serializar();

    }
    
}
