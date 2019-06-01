package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;

public class Laboratorio extends Sala implements Reserva, Serializable {

    @Override
    public void reservarPropuesta(Responsables reservador) throws ParseException {

    }

    @Override
    public void reservarConfirmada(Responsables reservador) {

    }
}
