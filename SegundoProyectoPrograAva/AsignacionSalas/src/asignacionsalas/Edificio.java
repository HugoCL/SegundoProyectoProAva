package asignacionsalas;

import java.util.ArrayList;

public class Edificio {

    private String color;

    private int cantSalas = 0;

    private int cantLabs = 0;

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
        setCantSalas(getCantSalas()+1);
    }
    /*
    public void verDatos(){
        SaladeClase sala = salas.get(0);
        System.out.println(sala.getProfResponsable());
    }*/

    public void agregarLaboratorio(){

    }

    public ArrayList<SaladeClase> getSalas(){
        return salas;
    }

    public void printSalas(){
        for (Sala sala: salas) {
            System.out.print(sala.getNombreSala()+" ");
        }
    }

    public void printLabs(){
        if (laboratorios.size() == 0){
            System.out.println("No hay labs disponibles en este edificio");
        }
        else{
            for (Sala lab: laboratorios) {
                System.out.print(lab.getNombreSala()+" ");
            }
        }
    }

    public int getCantSalas() {
        return cantSalas;
    }

    public void setCantSalas(int cantSalas) {
        this.cantSalas = cantSalas;
    }

    public int getCantLabs() {
        return cantLabs;
    }

    public void setCantLabs(int cantLabs) {
        this.cantLabs = cantLabs;
    }
}
