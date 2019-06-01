/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asignacionsalas;

import java.text.ParseException;

/**
 *
 * @author Hugo
 */
public class AsignacionSalas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Universidad universidad = new Universidad();
        universidad.setNombre("Universidad de Talca");
        universidad.setCantEdificios(0);
        universidad.crearEdificio("Verde");
        universidad.crearEdificio("Amarillo");
        universidad.crearEdificio("Cafe");
        universidad.crearEdificio("Naranjo");

        universidad.crearSala("Naranjo", 0, "Sala 11", "Sala Comun", 50,
                0, 1, 50, 1, "Julio Profe", 1, 50);
        universidad.crearSala("Naranjo", 0, "Sala 12", "Sala Comun", 48,
                0, 1, 48, 1, "Roque Bustamante", 1, 48);
        universidad.crearSala("Naranjo", 0, "Sala 13", "Sala Comun", 61,
                0, 1, 61, 1, "Armin Gusenbauer", 1, 61);
        universidad.crearSala("Naranjo", 0, "Sala 14", "Sala Comun", 55,
                0, 1, 55, 1, "Jorge Espinoza", 1, 55);
        universidad.crearSala("Naranjo", 0, "Sala 21", "Sala Comun", 42,
                0, 1, 42, 1, "Wilfred Flores", 1, 42);

        universidad.crearSala("Verde", 1, "Lab 1", "Sala de Computadores", 42,
                42, 1, 15, 1, "Rodrigo Pavez", 1, 42);
        universidad.crearSala("Verde", 1, "Lab 2", "Sala de Computadores", 30,
                30, 1, 10, 1, "Ruth Garrido", 1, 30);
        universidad.crearSala("Verde", 1, "Lab 3", "Sala de Computadores", 20,
                20, 0, 1, 0, "Luis Silvestre", 0, 20);

        universidad.crearSala("Amarillo", 0, "S1", "Sala Comun", 40,
                0, 1, 40, 1, "Profesor Charles Xavier", 1, 40);
        universidad.crearSala("Amarillo", 0, "S2", "Sala Comun", 44,
                0, 1, 44, 1, "Profesor Jirafales", 1, 44);

        universidad.crearSala("Cafe", 0, "103", "Sala Comun", 38,
                0, 1, 38, 1, "Profesor Luis Guajardo", 1, 38);
        universidad.crearSala("Cafe", 0, "104", "Sala Comun", 45,
                0, 1, 45, 1, "Profesor Fernando Cataldo", 1, 45);
        universidad.crearSala("Cafe", 0, "104", "Sala Comun", 42,
                0, 1, 42, 1, "Profesor Rosa", 1, 42);
        universidad.crearSala("Cafe", 0, "105", "Sala Comun", 51,
                0, 1, 51, 1, "Profesor Daniel Moreno", 1, 51);
        universidad.crearSala("Cafe", 0, "106", "Sala Comun", 56,
                0, 1, 56, 1, "Profesor Dumbledore", 1, 56);

        //universidad.verDatos();

        universidad.registrarResponsable("Hugo Castro", 1);
        universidad.registrarResponsable("Cesar", 1);
        universidad.registrarResponsable("Ariel Rojas", 1);
        universidad.registrarResponsable("Rodrigo Pavez", 3);
        universidad.registrarResponsable("Ruth Garrido", 2);

        Responsables yo = universidad.getResponsable(1, "Hugo Castro");
        yo.realizarReserva(yo, universidad);
        //responsables.verResponsable();
        //universidad.Serializar();

    }
    
}
