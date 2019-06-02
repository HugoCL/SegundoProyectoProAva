package asignacionsalas;

import java.text.ParseException;
import java.util.Date;

public interface Reserva {

    void reservarPropuesta(Responsables reservador, Date inicioSemestre, Date finalSemestre) throws ParseException;

    void reservarConfirmada(Responsables reservador, Date Semestre, Date finalSemetre);
}
