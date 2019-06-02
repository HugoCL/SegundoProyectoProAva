package asignacionsalas;

import java.util.ArrayList;
import java.util.Date;

public class Propuesta {

    private ArrayList<Date> fechasPropuestasInicio = new ArrayList<Date>();
    private ArrayList<Date> fechasPropuestasFinal = new ArrayList<Date>();
    private Responsables reservador;

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
            System.out.println("Fecha propuesta (Inicio): "+fechasPropuestasInicio.get(j));
            System.out.println("Fecha propuesta (Final): "+fechasPropuestasFinal.get(j));
        }
    }

}
