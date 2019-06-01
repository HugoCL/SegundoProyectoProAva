package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SaladeClase extends Sala implements Reserva, Serializable{


    @Override
    public void reservarPropuesta(Responsables reservador) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese su fecha propuesta para esta Sala de clases FORMATO: dd-mm-aaaa");
        System.out.println("Ejemplo -> 05-06-2000 Si no se sigue el formato, se lanzará una Exception");
        String fecha = entrada.nextLine();
        SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy");
        Date fechafinal = fechaForm.parse(fecha);
        System.out.println("Reserva Lista!");
        set
    }

    @Override
    public void reservarConfirmada(Responsables reservador) {

    }


    public void crearSala(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                          int pizarras, String profesor, int telones, int sillas) {

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
        //setProfResponsable(profesor);
        setPropuesta(false);
        setTelones(telones);
        setSillas(sillas);


    }
}
