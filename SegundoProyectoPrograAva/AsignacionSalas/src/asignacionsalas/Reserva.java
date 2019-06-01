package asignacionsalas;

import java.text.ParseException;

public interface Reserva {

    void reservarPropuesta(Responsables reservador) throws ParseException;

    void reservarConfirmada(Responsables reservador);
}
