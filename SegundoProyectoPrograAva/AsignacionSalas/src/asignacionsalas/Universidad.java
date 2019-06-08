package asignacionsalas;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/***
 * Clase mas importante del programa. Concentra toda la informacion y las clases del sistema
 */
public class Universidad implements Serializable {

    private String nombre;

    private Date inicioSemestre;

    private Date finalSemestre;

    protected ArrayList<Edificio> edificios = new ArrayList<Edificio>();

    private ArrayList<DTI> empleadosDTI = new ArrayList<DTI>();

    private ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

    private ArrayList<Administrador> administradores = new ArrayList<Administrador>();

    private ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    private int cantEdificios;


    /***
     * Metodo que se encarga de las creaciones de salas
     * @param color Es el string con el color del edificio en el que ira la sala
     * @param SalaoLab Es un int que indica si la sala que se creara es una sala de clases(0) o laboratorio(1)
     */

    public void crearSala(String color, int SalaoLab, String nombre, String actividad, int capacidad, int computadores, int datas, int mesas,
                          int pizarras, int telones, int sillas) {
        for (int i = 0; i < edificios.size(); i++) {
            Edificio edificioActual = edificios.get(i);
            if (edificioActual.getColor().equals(color)) {
                if (SalaoLab == 0){
                    edificioActual.agregarSalaClases(nombre, actividad, capacidad, computadores, datas, mesas,
                            pizarras, telones, sillas);
                }
                else{
                    edificioActual.agregarLaboratorio(nombre, actividad, capacidad, computadores, datas, mesas, pizarras,
                            telones, sillas);
                }
            }
        }

    }

    /***
     * Metooo que se uso para crear los edificios. Deprecated porque esta todo serializado
     * @deprecated
     * @param color
     */
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

    public void registrarResponsable(String nombreCompleto, int rol, String user, String pass){
        if (rol == 1){
            Estudiante estudiante = new Estudiante();
            estudiante.setNombreCompleto(nombreCompleto);
            estudiante.configPerfil(user, pass);
            estudiantes.add(estudiante);
        }
        else if (rol == 2){
            Administrador administrador = new Administrador();
            administrador.setNombreCompleto(nombreCompleto);
            administrador.crearPerfilAdmin(user, pass);
            administradores.add(administrador);
        }
        else if (rol == 3){
            Profesor profesor = new Profesor();
            profesor.setNombreCompleto(nombreCompleto);
            profesor.configPerfil(user, pass);
            profesores.add(profesor);
        }
    }

    /***
     * Metodo que serializa todos los datos del programa
     */
    public void Serializar(){
        try {
            FileOutputStream archivo = new FileOutputStream("SerializacionEdificios");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(edificios);
            archivo = new FileOutputStream("SerializacionEstudiantes");
            salida = new ObjectOutputStream(archivo);
            salida.writeObject(estudiantes);
            archivo = new FileOutputStream("SerializacionProfesores");
            salida = new ObjectOutputStream(archivo);
            salida.writeObject(profesores);
            archivo = new FileOutputStream("SerializacionAdministradores");
            salida = new ObjectOutputStream(archivo);
            salida.writeObject(administradores);
            salida.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Metodo que des-serializa lo que se encuentra en los archivos
     * @throws FileNotFoundException
     */
    public void DesSerializar() throws FileNotFoundException {
        try {
            FileInputStream archivo = new FileInputStream("SerializacionEdificios");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            ArrayList<Edificio> edificiosDes = (ArrayList<Edificio>) entrada.readObject();
            archivo = new FileInputStream("SerializacionEstudiantes");
            entrada = new ObjectInputStream(archivo);
            ArrayList<Estudiante> estudiantesDes = (ArrayList<Estudiante>) entrada.readObject();
            archivo = new FileInputStream("SerializacionProfesores");
            entrada = new ObjectInputStream(archivo);
            ArrayList<Profesor> profesoresDes = (ArrayList<Profesor>) entrada.readObject();
            archivo = new FileInputStream("SerializacionAdministradores");
            entrada = new ObjectInputStream(archivo);
            ArrayList<Administrador> administradoresDes = (ArrayList<Administrador>) entrada.readObject();
            edificios = edificiosDes;
            estudiantes = estudiantesDes;
            profesores = profesoresDes;
            administradores = administradoresDes;
            entrada.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }


    }

    /***
     * Metodo de busqueda de alguna persona, profesor, estudiante o admin
     * @param rol Es int que indica rol. 1 para estudiante, 2 para admin, 3 para profesor
     * @param nombreCompleto Como el nombre indica
     * @return retorna el objeto correspondiente a la persona
     */
    public Responsables getResponsable(int rol, String nombreCompleto){
        if (rol == 1){
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombreCompleto().equals(nombreCompleto)) {
                    return estudiante;
                }
            }
            System.out.println("No se encontro el estudiante");
        }
        else if (rol == 2){
            for (Administrador administrador : administradores) {
                if (administrador.getPerfilAdmin().getNombreUsuario().equals(nombreCompleto)) {
                    return administrador;
                }
            }
            System.out.println("No se encontro el administrativo");
        }
        else if (rol == 3){
            for (Profesor profesor : profesores) {
                if (profesor.getNombreCompleto().equals(nombreCompleto)) {
                    return profesor;
                }
            }
            System.out.println("No se encontro el profesor");
        }
        else{
            System.out.println("Rol no existe");
            return null;
        }
        return  null;
    }

    /***
     * Metodo que printea todos los edificios disponibles
     */
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

    public ArrayList<DTI> getEmpleadosDTI() {
        return empleadosDTI;
    }

    public void setEmpleadosDTI(ArrayList<DTI> empleadosDTI) {
        this.empleadosDTI = empleadosDTI;
    }
}
