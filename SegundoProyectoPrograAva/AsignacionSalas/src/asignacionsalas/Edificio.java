package asignacionsalas;

import java.util.ArrayList;

public class Edificio {

    private String color;

    private ArrayList<Laboratorio> laboratorios = new ArrayList<Laboratorio>();

    private ArrayList<SaladeClase> salas = new ArrayList<SaladeClase>();

    Edificio(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void agregarSalaClases(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                                  int pizarras, String nombreProfesor, int telones, int sillas){
        SaladeClase nuevaSala = new SaladeClase();
        nuevaSala.crearSala(nombre, actividad, capacidad, computadores, datas, mesas, pizarras, nombreProfesor, telones,
                sillas);
        salas.add(nuevaSala);
    }
    /*
    public void verDatos(){
        SaladeClase sala = salas.get(0);
        System.out.println(sala.getProfResponsable());
    }*/

    public void agregarLaboratorio(){

    }
}
