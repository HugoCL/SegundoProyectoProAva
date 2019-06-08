package asignacionsalas;

import java.text.ParseException;
import java.util.Date;

/***
 * Interface que declara metodos de reserva para las Salas
 */
public interface Reserva {
    /***
     * Metodo que se encarga de realizar una propuesta de reserva. Se sobreescribe
     * @param reservador Objeto de la persona encargada de la reserva
     * @param inicioSemestre Inicio del semestre
     * @param finalSemestre Fin del semetrs
     * @throws ParseException Por si hay error de parseo
     */
    void reservarPropuesta(Responsables reservador, Date inicioSemestre, Date finalSemestre) throws ParseException;

    /***
     * Metodo que se encarga de confirmar una reserva propuesta. Se sobreescribe
     */
    void reservarConfirmada();
}
