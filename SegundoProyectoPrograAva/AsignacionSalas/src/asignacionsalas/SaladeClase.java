package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 * Clase que contiene la informacion de una sala de clases y sus propuestas
 */

public class SaladeClase extends Sala implements Reserva, Serializable{

    private ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();


    @Override
    public void reservarPropuesta(Responsables reservador, Date inicioSemestre, Date finalSemestre) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Que clase de reservacion desea realizar?");
        System.out.println("[1] Reservacion de una o varias fechas");
        System.out.println("[2] Reservacion por un semestre");
        int op = entrada.nextInt();
        switch (op){
            case 1:
                System.out.println("Ha seleccionado reservacion de una o varias fechas");
                System.out.println("Cuantas fechas de clases desea agregar?");
                int nClases = entrada.nextInt();
                int errorS = 0;
                for (int i = 0; i <nClases; i++) {
                    Propuesta propuesta = new Propuesta();
                    System.out.println("Ingrese su fecha propuesta numero "+(i+1)+" junto a la hora de inicio para" +
                            " esta Sala de clases FORMATO: dd-mm-aaaa hh-mm");
                    System.out.println("Ejemplo -> 05-06-2020 13:00 (Clase el dia 5 de junio del 2000 desde las 1 " +
                            "de la tarde" + "Si no se sigue el formato, se lanzará una " + "Exception y " + "el programa fallara");
                    entrada.nextLine();
                    String fecha = entrada.nextLine();
                    SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    System.out.println("Por cuantas horas reservará la clase?");
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
                        }
                        else{
                            System.out.println("La sala se encuentra disponible, la reserva puede continuar normalmente");
                        }
                    }
                    if (errorS == 0){
                        propuesta.addFecha(fechaInicio, fechaFinal);
                        entrada.nextLine();
                        System.out.println("Ingrese la actividad a realizar");
                        propuesta.setActividad(entrada.nextLine());
                        System.out.println("Propuesta guardada con exito!(Desde "+fechaInicio+" hasta "+fechaFinal+"\n");
                        propuesta.setReservador(reservador);
                        propuesta.setForAllSem(false);
                        propuesta.setConfirmada(false);
                        propuestas.add(propuesta);
                    }
                    else{
                        System.out.println("Hubo un problema con una reserva solapada con una reserva confirmada");
                        break;
                    }
                }
                break;

            case 2:
                Propuesta propuesta = new Propuesta();
                System.out.println("Ha seleccionado reservacion por un semestre");
                    System.out.println("Ingrese la fecha de inicio de sus clases y hora de inicio para que su"  +
                            "propuesta se replique semanalmente hasta el fin de semestre FORMATO: dd-mm-aaaa hh-mm");
                System.out.println("Ejemplo -> 05-06-2020 13:00 (Clase el dia 5 de junio del 2000 desde las 1 " +
                        "de la tarde" + "Si no se sigue el formato, se lanzará una " + "Exception y " + "el programa fallara");
                entrada.nextLine();
                String fecha = entrada.nextLine();
                SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                System.out.println("Por cuantas horas reservará la clase?");
                int horas = entrada.nextInt();
                Date fechaInicio = fechaForm.parse(fecha);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicio);
                calendar.add(Calendar.HOUR_OF_DAY, horas);
                Date fechaFinal = calendar.getTime();

                propuesta.addFecha(fechaInicio, fechaFinal);
                entrada.nextLine();
                System.out.println("Ingrese la actividad a realizar estas clases");
                String actividad = entrada.nextLine();
                int semanas = 1;
                Date fechaInicioAux = fechaInicio, fechaFinalAux = fechaFinal;
                while(fechaInicioAux.compareTo(inicioSemestre) >= 0 && fechaFinalAux.compareTo(finalSemestre) <= 0){
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
                    System.out.println("Desde "+fechaInicioAux+" hasta "+fechaFinalAux);
                    propuesta.setReservador(reservador);
                    setActividad(actividad);

                }
                System.out.println("Todas sus fechas han sido registradas con exito!\n");
                propuesta.setForAllSem(true);
                propuesta.setConfirmada(false);
                propuestas.add(propuesta);
                break;

            default:
                throw new IllegalStateException("Valor inesperado: " + op);
        }

        setPropuesta(true);
    }

    @Override
    public void reservarConfirmada() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("A continuacion se mostraran las propuestas para poder confirmarlas");
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
     * Metood encargado de crear la sala finalmente
     * @param nombre Lo mismo que el nombre indica
     * @param actividad Lo mismo que el nombre indica
     * @param capacidad Lo mismo que el nombre indica
     * @param computadores Lo mismo que el nombre indica
     * @param datas Lo mismo que el nombre indica
     * @param mesas Lo mismo que el nombre indica
     * @param pizarras Lo mismo que el nombre indica
     * @param telones Lo mismo que el nombre indica
     * @param sillas Lo mismo que el nombre indica
     */
    @SuppressWarnings("Duplicates")
    public void crearSala(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                          int pizarras, int telones, int sillas) {

        setNombreSala(nombre);
        setActividad(actividad);
        setCapacidad(capacidad);
        for (int i = 1; i <= computadores; i++) {
            registrarInsumoT(1, i);
        }
        setConfirmada(false);
        for (int i = 1; i <= datas; i++) {
            registrarInsumoT(2, i);
        }
        setMesas(mesas);
        setPizarras(pizarras);
        setPropuesta(false);
        setTelones(telones);
        setSillas(sillas);


    }

    /***
     * Metodo encargado de mostrar las propuestas ordenadas
     * @param confirm es si se muestran las confirmadas o las propuestas. 0 para propuestas, 1 para confirmadas
     * @throws ParseException
     */
    public void getPropuestasOrdenadas(int confirm) throws ParseException{
        System.out.println("\nSe imprimirán las propuestas de la Sala "+getNombreSala()+":");
        if (propuestas.size() == 0){
            System.out.println("No hay reservas para esta Sala");
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
}
