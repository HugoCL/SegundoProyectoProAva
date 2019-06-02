package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Laboratorio extends Sala implements Reserva, Serializable {

    private ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();

    @Override
    public void reservarPropuesta(Responsables reservador, Date inicioSemestre, Date finalSemestre) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese su fecha propuesta para este laboratorio FORMATO: dd-mm-aaaa");
        System.out.println("Ejemplo -> 05-06-2000 Si no se sigue el formato, se lanzará una Exception");
        String fecha = entrada.nextLine();
        SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy");
        Date fechafinal = fechaForm.parse(fecha);
        System.out.println("Reserva propuesta lista!");
        setPropuesta(true);
        //Quedé aquí!!!!!!!
    }

    @Override
    public void reservarConfirmada(Responsables reservador, Date inicioSemestre, Date finalSemestre) {

    }

    public void crearLaboratorio(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                                 int pizarras, String nombreProfesor, int telones, int sillas){

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


    }
}