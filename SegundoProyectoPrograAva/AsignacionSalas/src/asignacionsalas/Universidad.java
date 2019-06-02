package asignacionsalas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Universidad implements Serializable {

    private String nombre;

    private Date inicioSemestre;

    private Date finalSemestre;

    protected ArrayList<Edificio> edificios = new ArrayList<Edificio>();

    private ArrayList<DTI> empleadosDTI = new ArrayList<DTI>();

    private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    private ArrayList<Administrativo> administrativos = new ArrayList<Administrativo>();

    private ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    private int cantEdificios;

    public void eliminarSala() {
    }

    /***
     *
     * @param color Es el string con el color del edificio en el que ira la sala
     * @param SalaoLab Es un int que indica si la sala que se creara es una sala de clases(0) o laboratorio(1)
     */

    public void crearSala(String color, int SalaoLab, String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                          int pizarras, String nombreProfesor, int telones, int sillas) {
        for (int i = 0; i < edificios.size(); i++) {
            Edificio edificioActual = edificios.get(i);
            if (edificioActual.getColor().equals(color)) {
                if (SalaoLab == 0){
                    edificioActual.agregarSalaClases(nombre, actividad, capacidad, computadores, datas, mesas,
                            pizarras, nombreProfesor, telones, sillas);
                }
            }
        }

    }

    public void crearEdificio(String color){
        if (getCantEdificios() == 0){
            edificios.add(new Edificio(color));
            setCantEdificios(getCantEdificios()+1);
        }
        else{
            int yaExiste = 0;
            for (Edificio edificioActual : edificios) {
                if (edificioActual.getColor().equals(color)) {
                    System.out.println("Este color ya existe para un edificio, ingrese otro color");
                    yaExiste = 1;
                    break;
                }
            }
            if (yaExiste == 0){
                edificios.add(new Edificio(color));
                setCantEdificios(getCantEdificios()+1);
            }
        }
    }
    /***
     *
     * @param nombreCompleto es el nombre de la persona a crear
     * @param rol es un int que representa que rol tiene la persona creada. 1 para estudiante, 2 para administrativo y
     *            3 para profesor
     */

    public void registrarResponsable(String nombreCompleto, int rol){
        if (rol == 1){
            Estudiante estudiante = new Estudiante();
            estudiante.setNombreCompleto(nombreCompleto);
            estudiantes.add(estudiante);
        }
        else if (rol == 2){
            Administrativo administrativo = new Administrativo();
            administrativo.setNombreCompleto(nombreCompleto);
            administrativos.add(administrativo);
        }
        else if (rol == 3){
            Profesor profesor = new Profesor();
            profesor.setNombreCompleto(nombreCompleto);
            profesores.add(profesor);
        }
    }
    //SIN TERMINAR
    public void Serializar(){
        String nombreArchivo = "SerializaciónResponsable";
        try {
            FileOutputStream archivo = new FileOutputStream(nombreArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(archivo);

            salida.writeObject(estudiantes.get(0));
        }

        catch (IOException ex){
            System.out.println("Excepcion detectada");
        }
    }

    /*public void verDatos(){
        Edificio edificio = edificios.get(1);
        edificio.verDatos();
    }*/

    public Responsables getResponsable(int rol, String nombreCompleto){
        if (rol == 1){
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombreCompleto().equals(nombreCompleto)) {
                    return estudiante;
                }
            }
        }
        else if (rol == 2){
            for (Administrativo administrativo : administrativos) {
                if (administrativo.getNombreCompleto().equals(nombreCompleto)) {
                    return administrativo;
                }
            }
        }
        else if (rol == 3){
            for (Profesor profesor : profesores) {
                if (profesor.getNombreCompleto().equals(nombreCompleto)) {
                    return profesor;
                }
            }
        }
        else{
            System.out.println("No se encontró esa persona");
            return null;
        }
        System.out.println("Rol no existe");
        return null;
    }

    public void getAllEdificios(){
        System.out.println(getCantEdificios());
        for (Edificio edificio : edificios) {
            System.out.print(edificio.getColor()+" ");
        }
        System.out.println();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Edificio> getEdificios(){
        return edificios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantEdificios() {
        return cantEdificios;
    }

    public void setCantEdificios(int cantEdificios) {
        this.cantEdificios = cantEdificios;
    }

    public Date getInicioSemestre() {
        return inicioSemestre;
    }

    public void setInicioSemestre(Date inicioSemestre) {
        this.inicioSemestre = inicioSemestre;
    }

    public Date getFinalSemestre() {
        return finalSemestre;
    }

    public void setFinalSemestre(Date finalSemestre) {
        this.finalSemestre = finalSemestre;
    }
}
