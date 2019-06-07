package asignacionsalas;

import java.util.ArrayList;
import java.util.Date;

public class Propuesta {

    private ArrayList<Date> fechasPropuestasInicio = new ArrayList<Date>();
    private ArrayList<Date> fechasPropuestasFinal = new ArrayList<Date>();
    private Responsables reservador;
    private boolean isForAllSem;
    private String Actividad;

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

    public void setActividad(String actividad) {
        Actividad = actividad;
    }
}
