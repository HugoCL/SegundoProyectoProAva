package asignacionsalas;

import java.util.ArrayList;
import java.util.Date;

/***
 * Clase encarga del manejo y almacenamiento de las propuestas, ademas de las reservas confirmadas
 */
public class Propuesta {

    private ArrayList<Date> fechasPropuestasInicio = new ArrayList<Date>();
    private ArrayList<Date> fechasPropuestasFinal = new ArrayList<Date>();
    private Responsables reservador;
    private boolean isForAllSem;
    private String Actividad;
    private boolean isConfirmada;

    public void addFecha(Date fechaInicio, Date fechaFinal) {
        fechasPropuestasInicio.add(fechaInicio);
        fechasPropuestasFinal.add(fechaFinal);
    }

    public void setReservador(Responsables responsable){
        this.reservador = responsable;
    }

    public Responsables getReservador(){
        return reservador;
    }

    public void getFechas(){
        for (int j = 0; j < fechasPropuestasInicio.size(); j++) {
            System.out.println("Fecha propuesta n°"+(j+1)+" (Inicio): "+fechasPropuestasInicio.get(j));
            System.out.println("Fecha propuesta n°"+(j+1)+" (Final): "+fechasPropuestasFinal.get(j));
        }
    }

    public Date getFechaPuntualI(int id){
        return fechasPropuestasInicio.get(id);
    }

    public Date getFechaPuntualF(int id){
        return fechasPropuestasFinal.get(id);
    }

    public boolean isForAllSem() {
        return isForAllSem;
    }

    public void setForAllSem(boolean forAllSem) {
        isForAllSem = forAllSem;
    }

    public ArrayList<Date> getFechasPropuestasFinal() {
        return fechasPropuestasFinal;
    }

    public ArrayList<Date> getFechasPropuestasInicio() {
        return fechasPropuestasInicio;
    }

    public String getActividad() {
        return Actividad;
    }

    public int confirmChecker(Date inicio, Date Final){
        for (int j = 0; j < fechasPropuestasInicio.size(); j++) {
            if (inicio.compareTo(fechasPropuestasInicio.get(j)) >= 0 || Final.compareTo(fechasPropuestasFinal.get(j)) <= 0 &&
                    isConfirmada){
                return -1;
            }
        }
        return 0;
    }
    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    public boolean isConfirmada() {
        return isConfirmada;
    }

    public void setConfirmada(boolean confirmada) {
        isConfirmada = confirmada;
    }
}
