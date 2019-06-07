package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                for (int i = 0; i <nClases; i++) {
                    Propuesta propuesta = new Propuesta();
                    System.out.println("Ingrese su fecha propuesta numero "+i+" junto a la hora de inicio para" +
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
                    propuesta.addFecha(fechaInicio, fechaFinal);
                    System.out.println("Propuesta guardada con exito!(Desde "+fechaInicio+" hasta "+fechaFinal+"\n");
                    propuesta.setReservador(reservador);
                    propuesta.setForAllSem(false);
                    propuestas.add(propuesta);
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
                    System.out.println("Desde "+fechaInicioAux+" hasta "+fechaFinalAux);
                    propuesta.setReservador(reservador);
                }
                System.out.println("Todas sus fechas han sido registradas con exito!\n");
                propuesta.setForAllSem(true);
                propuestas.add(propuesta);
                break;

            default:
                throw new IllegalStateException("Valor inesperado: " + op);
        }

        setPropuesta(true);
    }

    @Override
    public void reservarConfirmada(Responsables reservador, Date inicioSemestre, Date finalSemestre) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Se procederá a confirmar la propuesta de esta sala, continuar [Y/S]");
        char op = entrada.nextLine().charAt(0);
        if (op == 'Y'){
            // Implementar
        }
        else{
            System.out.println("Confirmación cancelada");
        }
    }


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

    public void printPropuestas(){
        for (Propuesta propuesta: propuestas) {
            propuesta.getFechas();
        }
    }

    public ArrayList<Propuesta> getPropuestas(){
        return propuestas;
    }
    /***
     *
     *
     */
    public void getPropuestasOrdenadas() throws ParseException{
        System.out.println("\nSe imprimirán las propuestas de la Sala "+getNombreSala()+":");
        if (propuestas.size() == 0){
            System.out.println("No hay reservas para esta Sala");
        }
        else{
            for (Propuesta propuestaF : propuestas) {
                String nombreReservador = propuestaF.getReservador().getNombreCompleto();
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
        }
    }
}
