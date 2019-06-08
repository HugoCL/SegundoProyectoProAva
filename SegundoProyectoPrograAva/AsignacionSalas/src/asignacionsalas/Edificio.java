package asignacionsalas;

import java.io.Serializable;
import java.util.ArrayList;

/***
 * Clase que guarda la informacion general del edificio y salas en su interior
 */
public class Edificio implements Serializable {

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

    /***
     * Metodo para agregar salas o
     * @param nombre Lo mismo que indica el nombre
     * @param actividad Lo mismo que indica el nombre
     * @param capacidad Lo mismo que indica el nombre
     * @param computadores Lo mismo que indica el nombre
     * @param datas Lo mismo que indica el nombre
     * @param mesas Lo mismo que indica el nombre
     * @param pizarras Lo mismo que indica el nombre
     * @param telones Lo mismo que indica el nombre
     * @param sillas Lo mismo que indica el nombre
     */
    public void agregarSalaClases(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                                  int pizarras, int telones, int sillas){
        SaladeClase nuevaSala = new SaladeClase();
        nuevaSala.crearSala(nombre, actividad, capacidad, computadores, datas, mesas, pizarras, telones,
                sillas);
        salas.add(nuevaSala);
        setCantSalas(getCantSalas()+1);
    }

    /***
     * Metodo para agregar salas o
     * @param nombre Lo mismo que indica el nombre
     * @param actividad Lo mismo que indica el nombre
     * @param capacidad Lo mismo que indica el nombre
     * @param computadores Lo mismo que indica el nombre
     * @param datas Lo mismo que indica el nombre
     * @param mesas Lo mismo que indica el nombre
     * @param pizarras Lo mismo que indica el nombre
     * @param telones Lo mismo que indica el nombre
     * @param sillas Lo mismo que indica el nombre
     */
    public void agregarLaboratorio(String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                                   int pizarras, int telones, int sillas){

        Laboratorio laboratorio =  new Laboratorio();
        laboratorio.crearLaboratorio(nombre, actividad, capacidad, computadores, datas, mesas, pizarras,
                telones, sillas);
        laboratorios.add(laboratorio);
        setCantLabs(getCantLabs()+1);

    }

    public ArrayList<SaladeClase> getSalas(){
        return salas;
    }

    public ArrayList<Laboratorio> getLaboratorios(){
        return laboratorios;
    }

    /***
     * Metodo que muestra las salas y sus capacidades
     */
    public void printSalas(){
        if (salas.size() == 0){
            System.out.println("No hay salas disponibles en este edificio");
        }
        else{
            for (SaladeClase sala: salas) {
                System.out.print(sala.getNombreSala()+" (Capacidad: "+sala.getCapacidad()+") ");
            }
        }
    }

    /***
     * Metodo que muestra los laboratorios y sus salas
     */
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
