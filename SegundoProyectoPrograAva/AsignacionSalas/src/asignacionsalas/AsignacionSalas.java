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

        universidad.registrarResponsable("Hugo Castro", 1);
        universidad.registrarResponsable("Cesar", 1);
        universidad.registrarResponsable("Ariel Rojas", 1);
        universidad.registrarResponsable("Rodrigo Pavez", 3);
        universidad.registrarResponsable("Ruth Garrido", 2);

        // Creacion del perfil de admin
        Profesor admin = (Profesor) universidad.getResponsable(3, "Rodrigo Pavez");
        admin.crearPerfilAdmin("utalca", "utalca123");
        // Fin creacion
        int rolM = 0;
        do{
            System.out.println("Selecciona que clase de persona eres dentro de la universidad para ver tu menú");
            System.out.println("[1] Estudiante");
            System.out.println("[2] Administrativo");
            System.out.println("[3] Profesor");
            System.out.println("[4] Administrador");
            System.out.println("[5] Cerrar sistema");
            rolM = entrada.nextInt();
            switch (rolM){
                case 1:
                    System.out.println("Ingrese su nombre completo para iniciar sesión (Sensible a mayusculas y minisculas");
                    entrada.nextLine();
                    String nombre = entrada.nextLine();
                    Estudiante estudiante = (Estudiante) universidad.getResponsable(1, nombre);
                    if (estudiante != null){
                        estudiante.menu(estudiante, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
                    }
                    break;
                case 2:
                    System.out.println("Ingrese su nombre completo para iniciar sesión (Sensible a mayusculas y minisculas");
                    entrada.nextLine();
                    String nombreAd = entrada.nextLine();
                    Administrativo administrativo = (Administrativo) universidad.getResponsable(2, nombreAd);
                    if (administrativo != null){
                        administrativo.menu(administrativo, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
                    }
                    break;
                case 3:
                    System.out.println("Ingrese su nombre completo para iniciar sesión (Sensible a mayusculas y minisculas");
                    entrada.nextLine();
                    String nombrePro = entrada.nextLine();
                    Profesor profesor = (Profesor) universidad.getResponsable(3, nombrePro);
                    if (profesor != null){
                        profesor.menu(profesor, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
                    }
                    break;
                case 4:
                    System.out.println("¡Bienvenido Admin! ¿Que clase de persona eres?");
                    System.out.println("[1] Estudiante");
                    System.out.println("[2] Profesor");
                    int roladmin = entrada.nextInt();
                    System.out.println("Introduce tu nombre completo a continuacion (Sensible a mayusculas y minisculas)");
                    entrada.nextLine();
                    String nombreadmin = entrada.nextLine();
                    if (roladmin == 1){
                        Estudiante estudianteAdmin = (Estudiante) universidad.getResponsable(1, nombreadmin);
                        if (estudianteAdmin != null){
                            if (estudianteAdmin.isAdmin()){
                                System.out.println("¡Usuario administrador encontrado!");
                                System.out.println("Ingresa tu nombre de usuario");
                                String user = entrada.nextLine();
                                System.out.println("Ingresa tu password");
                                String pass = entrada.nextLine();
                                String userC = estudianteAdmin.getPerfilAdmin().getNombreUsuario();
                                String passC = estudianteAdmin.getPerfilAdmin().getPassword();
                                if (user.equals(userC) && pass.equals(passC)){
                                    System.out.println("Has ingresado correctamente");
                                    // Aqui va el menu de admin
                                }
                                else{
                                    System.out.println("Tu nombre y/o password son incorrectos");
                                }
                            }
                            else{
                                System.out.println("Tu perfil no es Admin");
                            }
                        }
                    }
                    else if (roladmin == 2){
                        Profesor profesorAdmin = (Profesor) universidad.getResponsable(3, nombreadmin);
                        if (profesorAdmin != null){
                            if (profesorAdmin.isAdmin()){
                                System.out.println("¡Usuario administrador encontrado!");
                                System.out.println("Ingresa tu nombre de usuario");
                                String user = entrada.nextLine();
                                System.out.println("Ingresa tu password");
                                String pass = entrada.nextLine();
                                String userC = profesorAdmin.getPerfilAdmin().getNombreUsuario();
                                String passC = profesorAdmin.getPerfilAdmin().getPassword();
                                if (user.equals(userC) && pass.equals(passC)){
                                    System.out.println("Has ingresado correctamente");
                                    // Aqui va el menu de admin
                                }
                                else{
                                    System.out.println("Tu nombre y/o password son incorrectos");
                                }
                            }
                            else{
                                System.out.println("Tu perfil no es Admin");
                            }
                        }
                    }
                    else{
                        System.out.println("Rol incorrecto");
                    }
              }
        }while(rolM != 5);
        //Responsables yo = universidad.getResponsable(1, "Hugo Castro");
        //yo.realizarReserva(yo, universidad, universidad.getInicioSemestre(), universidad.getFinalSemestre());
        //yo.getReservas(universidad);
        //responsables.verResponsable();
        //universidad.Serializar();

    }
    
}
