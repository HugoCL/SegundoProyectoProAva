package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/***
 * Clase que contiene toda la información de un laboratorio. Es heredada de Sala
 */
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
                int errorS = 0;
                for (int i = 0; i <nClases; i++) {

                    System.out.println("Ingrese su fecha propuesta numero "+(i+1)+" junto a la hora de inicio para" +
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
                    System.out.println("Se comprobará si la sala no tiene reservas confirmadas en el horario propuesto");
                    for (Propuesta propuestaHechas : propuestas){
                        int resultado = propuestaHechas.confirmChecker(fechaInicio, fechaFinal);
                        if (resultado == -1){
                            System.out.println("Esta sala ya tiene una reserva confirmada en ese horario :(");
                            errorS = 1;
                            break;
                        }
                        else{
                            System.out.println("La sala se encuentra disponible, la reserva puede continuar normalmente");
                        }
                    }
                    if (errorS == 0){
                        propuesta.addFecha(fechaInicio, fechaFinal);
                        propuesta.setForAllSem(true);
                        propuestas.add(propuesta);
                        System.out.println("Propuesta de laboratorio guardada con exito!(Desde "+fechaInicio+" hasta "+fechaFinal+"\n");
                    }
                    else{
                        System.out.println("Hubo un error con la reserva. La reserva se realizo sobre una reserva confirmada");
                        break;
                    }

                }
                System.out.println("Todas sus fechas de laboratorio han sido registradas con exito!\n");
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
                int error = 0;
                while(fechaInicioAux.compareTo(inicioSemestre) > 0 && fechaFinalAux.compareTo(finalSemestre) < 0){
                    calendar = Calendar.getInstance();
                    calendar.setTime(fechaInicio);
                    calendar.add(Calendar.WEEK_OF_YEAR, semanas);
                    fechaInicioAux = calendar.getTime();
                    calendar = Calendar.getInstance();
                    calendar.setTime(fechaFinal);
                    calendar.add(Calendar.WEEK_OF_YEAR, semanas);
                    fechaFinalAux = calendar.getTime();
                    for (Propuesta propuestaHechas : propuestas){
                        int resultado = propuestaHechas.confirmChecker(fechaInicio, fechaFinal);
                        if (resultado == -1){
                            System.out.println("Esta sala ya tiene una reserva confirmada en ese horario :(");
                            error = 1;
                            break;
                        }
                        else{
                            System.out.println("La sala se encuentra disponible, la reserva puede continuar normalmente");
                        }
                    }
                    propuesta.addFecha(fechaInicioAux, fechaFinalAux);
                    semanas++;
                    propuesta.setReservador(reservador);
                }
                if (error == 0){
                    System.out.println("Todas sus fechas de laboratorio han sido registradas con exito!\n");
                    propuesta.setForAllSem(true);
                    propuestas.add(propuesta);
                }
                else{
                    System.out.println("Hubo un error con la reserva. La reserva se realizo sobre una reserva confirmada");
                }
                break;

            default:
                throw new IllegalStateException("Valor inesperado: " + op);
        }

        setPropuesta(true);
    }

    @Override
    public void reservarConfirmada(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("A continuacion se mostraran las propuestas de laboratorio para poder confirmarlas");
        int i = 0;
        for (Propuesta propuesta: propuestas){
            System.out.println("Propuesta numero["+(i+1)+"]:");
            propuesta.getFechas();
            i++;
        }
        System.out.println("Ingresa el numero de la propuesta a confirmar");
        int op = entrada.nextInt();
        System.out.println("Se confirmará la reservacion numero "+op);
        System.out.println("Las fechas de esta reserva son: ");
        Propuesta propuestaEle = propuestas.get(op-1);
        propuestaEle.getFechas();
        System.out.println("Si desea confirmar esta accion, presione 1. Para cancelar, presiona 2");
        int opAccion = entrada.nextInt();
        if (opAccion == 1){
            propuestaEle.setConfirmada(true);
        }
        else{
            System.out.println("Confirmacion cancelada");
        }
    }

    /***
     * Metodo encargado de mostrar las propuestas ordenadas para labs
     * @param confirm confirm es si se muestran las confirmadas o las propuestas. 0 para propuestas, 1 para confirmadas
     * @throws ParseException
     */
    public void getPropuestasOrdenadas(int confirm) throws ParseException{
        System.out.println("\nSe imprimirán las propuestas del lab "+getNombreSala()+":");
        if (propuestas.size() == 0){
            System.out.println("No hay reservas para esta lab");
        }
        else{
            for (Propuesta propuestaF : propuestas) {
                String nombreReservador = propuestaF.getReservador().getNombreCompleto();
                if (confirm == 0){
                    if (propuestaF.isForAllSem()) {
                        System.out.println("Propuesta por todo el semestre, parte el "+propuestaF.getFechaPuntualI(0)+" hasta "+
                                propuestaF.getFechaPuntualF(0)+". La reserva termina el "+
                                propuestaF.getFechaPuntualI(propuestaF.getFechasPropuestasInicio().size()-1)+" hasta "+
                                propuestaF.getFechaPuntualF(propuestaF.getFechasPropuestasFinal().size()-1));
                        System.out.println("Esta reserva fue hecha por:");
                        if (propuestaF.getReservador() instanceof Profesor){
                            System.out.println("Profesor "+ nombreReservador);
                        }
                        else if (propuestaF.getReservador() instanceof Estudiante){
                            System.out.println("Estudiante "+ nombreReservador);
                        }
                        else{
                            System.out.println(nombreReservador);
                        }
                    }
                    else{
                        int i = 0;
                        int j = 0;
                        int idPropuesta = 0;
                        String fecha = "01-01-50000";
                        SimpleDateFormat parseF = new SimpleDateFormat("dd-MM-yyyy");
                        Date fechaMasReciente = parseF.parse(fecha);
                        Date fechaAux;
                        Propuesta propActual;
                        while(i < propuestas.size()){
                            while (j < propuestas.size()){
                                propActual = propuestas.get(j);
                                fechaAux = propActual.getFechaPuntualI(0);
                                if (fechaAux.compareTo(fechaMasReciente) < 0){
                                    fechaMasReciente = fechaAux;
                                }
                                j++;
                            }
                            System.out.println("Desde "+propuestas.get(i).getFechaPuntualF(0)+" hasta "+
                                    propuestas.get(i).getFechaPuntualF(0));
                            fecha = "01-01-2200";
                            fechaMasReciente = parseF.parse(fecha);
                            i++;
                        }
                    }
                }
                else if (confirm == 1){
                    if (propuestaF.isForAllSem() && propuestaF.isConfirmada()) {
                        System.out.println("Propuesta por todo el semestre, parte el "+propuestaF.getFechaPuntualI(0)+" hasta "+
                                propuestaF.getFechaPuntualF(0)+". La reserva termina el "+
                                propuestaF.getFechaPuntualI(propuestaF.getFechasPropuestasInicio().size()-1)+" hasta "+
                                propuestaF.getFechaPuntualF(propuestaF.getFechasPropuestasFinal().size()-1));
                        System.out.println("Esta reserva fue hecha por:");
                        if (propuestaF.getReservador() instanceof Profesor){
                            System.out.println("Profesor "+ nombreReservador);
                        }
                        else if (propuestaF.getReservador() instanceof Estudiante){
                            System.out.println("Estudiante "+ nombreReservador);
                        }
                        else{
                            System.out.println(nombreReservador);
                        }
                    }
                    else if (propuestaF.isConfirmada()){
                        int i = 0;
                        int j = 0;
                        int idPropuesta = 0;
                        String fecha = "01-01-50000";
                        SimpleDateFormat parseF = new SimpleDateFormat("dd-MM-yyyy");
                        Date fechaMasReciente = parseF.parse(fecha);
                        Date fechaAux;
                        Propuesta propActual;
                        while(i < propuestas.size()){
                            while (j < propuestas.size()){
                                propActual = propuestas.get(j);
                                fechaAux = propActual.getFechaPuntualI(0);
                                if (fechaAux.compareTo(fechaMasReciente) < 0){
                                    fechaMasReciente = fechaAux;
                                }
                                j++;
                            }
                            System.out.println("Desde "+propuestas.get(i).getFechaPuntualF(0)+" hasta "+
                                    propuestas.get(i).getFechaPuntualF(0));
                            fecha = "01-01-2200";
                            fechaMasReciente = parseF.parse(fecha);
                            i++;
                        }
                    }
                }
            }
        }
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