package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Laboratorio extends Sala implements Reserva, Serializable {

    private ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();

    @Override
    public void reservarPropuesta(Responsables reservador, Date inicioSemestre, Date finalSemestre) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Que clase de reservacion desea realizar?");
        System.out.println("[1] Reservacion de una o varias fechas");
        System.out.println("[2] Reservacion por un semestre");
        Propuesta propuesta = new Propuesta();
        int op = entrada.nextInt();
        switch (op){
            case 1:
                System.out.println("Ha seleccionado reservacion de una o varias fechas");
                System.out.println("Cuantas fechas de laboratorios desea agregar?");
                int nClases = entrada.nextInt();

                for (int i = 0; i <nClases; i++) {

                    System.out.println("Ingrese su fecha propuesta numero "+i+" junto a la hora de inicio para" +
                            " este laboratorio FORMATO: dd-mm-aaaa hh-mm");
                    System.out.println("Ejemplo -> 05-06-2020 13:00 (Laboratorio el dia 5 de junio del 2000 desde las 1 " +
                            "de la tarde" + "Si no se sigue el formato, se lanzará una " + "Exception y " + "el programa fallara");
                    entrada.nextLine();
                    String fecha = entrada.nextLine();
                    SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    System.out.println("Por cuantas horas reservará el laboratorio?");
                    Date fechaInicio = fechaForm.parse(fecha);
                    int horas = entrada.nextInt();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fechaInicio);
                    calendar.add(Calendar.HOUR_OF_DAY, horas);
                    Date fechaFinal = calendar.getTime();
                    propuesta.addFecha(fechaInicio, fechaFinal);
                    System.out.println("Propuesta de laboratorio guardada con exito!(Desde "+fechaInicio+" hasta "+fechaFinal+"\n");
                }
                propuestas.add(propuesta);
                break;

            case 2:
                System.out.println("Ha seleccionado reservacion por un semestre");
                System.out.println("Ingrese la fecha de inicio de sus laboratorios y hora de inicio para que su"  +
                        "propuesta se replique semanalmente hasta el fin de semestre FORMATO: dd-mm-aaaa hh-mm");
                System.out.println("Ejemplo -> 05-06-2020 13:00 (Laboratorio el dia 5 de junio del 2000 desde las 1 " +
                        "de la tarde" + "Si no se sigue el formato, se lanzará una " + "Exception y " + "el programa fallara");
                entrada.nextLine();
                String fecha = entrada.nextLine();
                SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                System.out.println("Por cuantas horas reservará el laboratorio?");
                int horas = entrada.nextInt();
                Date fechaInicio = fechaForm.parse(fecha);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicio);
                calendar.add(Calendar.HOUR_OF_DAY, horas);
                Date fechaFinal = calendar.getTime();

                propuesta.addFecha(fechaInicio, fechaFinal);

                int semanas = 1;
                Date fechaInicioAux = fechaInicio, fechaFinalAux = fechaFinal;
                while(fechaInicioAux.compareTo(inicioSemestre) > 0 && fechaFinalAux.compareTo(finalSemestre) < 0){
                    calendar = Calendar.getInstance();
                    calendar.setTime(fechaInicio);
                    calendar.add(Calendar.WEEK_OF_YEAR, semanas);
                    fechaInicioAux = calendar.getTime();
                    calendar = Calendar.getInstance();
                    calendar.setTime(fechaFinal);
                    calendar.add(Calendar.WEEK_OF_YEAR, semanas);
                    fechaFinalAux = calendar.getTime();
                    propuesta.addFecha(fechaInicioAux, fechaFinalAux);
                    semanas++;
                    propuesta.setReservador(reservador);
                }
                System.out.println("Todas sus fechas de laboratorio han sido registradas con exito!\n");
                propuestas.add(propuesta);
                break;

            default:
                throw new IllegalStateException("Valor inesperado: " + op);
        }

        setPropuesta(true);
    }

    @Override
    public void reservarConfirmada(Responsables reservador, Date inicioSemestre, Date finalSemestre){

    }

    public void crearLaboratorio(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                                 int pizarras, int telones, int sillas){

        setNombreSala(nombre);
        setActividad(actividad);
        setCapacidad(capacidad);
        for (int i = 1; i <= computadores; i++) {
            registrarInsumoT(1, i);
        }
        for (int i = 1; i <= datas; i++){
            registrarInsumoT(2, i);
        }
        setPizarras(pizarras);
        //Aqui falta el set profesor
        setTelones(telones);
        setSillas(sillas);
        setMesas(mesas);

    }
}