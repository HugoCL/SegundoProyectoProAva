package asignacionsalas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/***
 * Clase que contiene los metodos y datos del administrador
 */

public class Administrador extends Responsables implements Serializable {


    private Perfil perfilAdmin;

    /***
     * Metodo para crear un perfil de admin
     * @param user Nombre de usuario
     * @param pass Password
     */
    public void crearPerfilAdmin(String user, String pass) {
        Perfil perfil = new Perfil();
        perfil.setNombreUsuario(user);
        perfil.setPassword(pass);
        setAdmin(true);
        setPerfilAdmin(perfil);
    }

    /***
     * Esta funcion muestra el menu del administrador y sus funciones
     * @param universidad se envia la universidad para hacer cambios en ella
     * @param iSemestre inicio del semestre
     * @param fSemestre fin de semestre
     * @throws ParseException Por si hay un error al separar la fecha
     */

    public void menuAdmin(Universidad universidad, Date iSemestre, Date fSemestre) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        int op = 0;
        do{
            System.out.println("MENU DE ADMIN");
            System.out.println("[1] Mostrar todas las reservas hechas por orden(Propuestas)");
            System.out.println("[2] Mostrar todas las reservas hechas (Confirmadas)");
            System.out.println("[3] Ver todos los incidentes");
            System.out.println("[4] Crear un nuevo estudiante o profesor");
            System.out.println("[5] Confirmar una reserva propuesta");
            System.out.println("[6] Crear nueva sala");
            System.out.println("[7] Destruir sala");
            System.out.println("[8] Cambiar fecha de inicio y/o final de semestre");
            System.out.println("[9] Cerrar sesion");
            System.out.print("Ingrese su opcion -> ");
            op = entrada.nextInt();
            switch (op) {
                case 1:
                    System.out.println("A continuacion se mostraran todas las reservas hechas");
                    ArrayList<Edificio> edificios = universidad.getEdificios();
                    for(Edificio edificio: edificios){
                        ArrayList<SaladeClase> salas = edificio.getSalas();
                        ArrayList<Laboratorio> laboratorios = edificio.getLaboratorios();
                        for(SaladeClase sala: salas){
                            sala.getPropuestasOrdenadas(0);
                        }
                        for(Laboratorio lab: laboratorios){
                            lab.getPropuestasOrdenadas(0);
                        }
                    }
                    break;
                case 2:
                    System.out.println("A continuacion se mostraran todas las reservas confirmadas");
                    ArrayList<Edificio> Edificios = universidad.getEdificios();
                    for(Edificio edificio: Edificios){
                        ArrayList<SaladeClase> salas = edificio.getSalas();
                        ArrayList<Laboratorio> laboratorios = edificio.getLaboratorios();
                        for(SaladeClase sala: salas){
                            sala.getPropuestasOrdenadas(1);
                        }
                        for(Laboratorio lab: laboratorios){
                            lab.getPropuestasOrdenadas(1);
                        }
                    }
                    break;
                case 3:
                    for (Edificio edificio: universidad.getEdificios()){
                        for (SaladeClase sala: edificio.getSalas()){
                            System.out.println("Incidencias de la Sala: "+sala.getNombreSala());
                            ArrayList<Incidencia> incidenciasSala = sala.getIncidencias();
                            if (sala.getIncidencias().size() == 0){
                                System.out.println("No hay incidencias en esta sala");
                            }
                            else{
                                for (Incidencia incidencia: incidenciasSala){
                                    System.out.println("Problema:");
                                    if (incidencia.getInsumoProblema().getInsumo() == 1){
                                        System.out.println("Computador numero "+incidencia.getInsumoProblema().getID());
                                        System.out.println("Detalles:");
                                        System.out.println(incidencia.getDetalleIncidencia());
                                    }
                                    else if (incidencia.getInsumoProblema().getInsumo() == 2){
                                        System.out.println("Data numero "+incidencia.getInsumoProblema().getID());
                                    }
                                    else{
                                        System.out.println("Problema de espacio");
                                        System.out.println(incidencia.getDetalleIncidencia());
                                    }
                                }

                            }
                        }

                        for (Laboratorio lab: edificio.getLaboratorios()){
                            System.out.println("Incidencias del Lab: "+ lab.getNombreSala());
                            for (Incidencia incidencia: lab.getIncidencias()){
                                if (lab.getIncidencias().size() == 0){
                                    System.out.println("No hay incidencias en esta sala");
                                }
                                else{
                                    System.out.println("Problema:");
                                    if (incidencia.getInsumoProblema().getInsumo() == 1){
                                        System.out.println("Computador numero "+incidencia.getInsumoProblema().getID());
                                        System.out.println("Detalles:");
                                        System.out.println(incidencia.getDetalleIncidencia());
                                    }
                                    else if (incidencia.getInsumoProblema().getInsumo() == 2){
                                        System.out.println("Data numero "+incidencia.getInsumoProblema().getID());
                                    }
                                    else{
                                        System.out.println("Problema de espacio");
                                        System.out.println(incidencia.getDetalleIncidencia());
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Se creara un nuevo perfil universitario");
                    System.out.println("Ingrese el tipo de persona quiere agregar, 1 para estudiante y 2 para profesor");
                    int rol = entrada.nextInt();
                    System.out.println("Ingrese el nombre completo (será el username con el que accedera al sistema)");
                    entrada.nextLine();
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese la password del nuevo usuario");
                    String pass = entrada.nextLine();
                    if (rol == 1){
                        universidad.registrarResponsable(nombre, rol, nombre, pass);
                    }
                    else if (rol == 2){
                        universidad.registrarResponsable(nombre,3, nombre, pass);
                    }
                    else{
                        System.out.println("Hubo un problema al crear el usuario. Rol no existe");
                    }
                    break;
                case 5:
                    System.out.println("Se procedera a confirmar una reserva de sala o laboratorio");
                    System.out.println("Edificios disponibles:");
                    universidad.getAllEdificios();
                    System.out.println("Ingrese el color del edificio a seleccionar(Sensible a mayusculas" +
                            " y minusculas");
                    entrada.nextLine();
                    String edificioC = entrada.nextLine();
                    for (Edificio edificio: universidad.getEdificios()) {
                        if (edificio.getColor().equals(edificioC)) {
                            System.out.println("Edificio encontrado");
                            System.out.println("En este edificio hay " + edificio.getCantSalas() + " salas y " + edificio.getCantLabs() + " laboratorios");
                            System.out.println("Desea confirmar una sala de clase[1] o un laboratorio[2]?");
                            int opS = entrada.nextInt();
                            if (opS == 1) {
                                System.out.print("Salas disponibles: ");
                                edificio.printSalas();
                                System.out.println();
                                System.out.println("Ingrese el nombre de la sala con la propuesta a confirmar" +
                                        "(Sensible a mayusculas y minusculas)");
                                entrada.nextLine();
                                String Sala = entrada.nextLine();
                                for (SaladeClase sala : edificio.getSalas()){
                                    if (sala.getNombreSala().equals(Sala)){
                                        sala.reservarConfirmada();
                                    }
                                }
                            }
                            else if (opS == 2) {
                                System.out.print("Labs disponibles: ");
                                edificio.printSalas();
                                System.out.println();
                                System.out.println("Ingrese el nombre del lab con la propuesta a confirmar" +
                                        "(Sensible a mayusculas y minusculas)");
                                entrada.nextLine();
                                String Lab = entrada.nextLine();
                                for (Laboratorio lab : edificio.getLaboratorios()){
                                    if (lab.getNombreSala().equals(Lab)){
                                        lab.reservarConfirmada();
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.println("Se procederá a crear una nueva sala");
                    System.out.println("Edificios disponibles:");
                    universidad.getAllEdificios();
                    System.out.println("Ingrese el color del edificio que se usara para la creacion (Sensible a mayusculas" +
                            " y minusculas");
                    entrada.nextLine();
                    String edificioS = entrada.nextLine();
                    for (Edificio edificio: universidad.getEdificios()) {
                        if (edificio.getColor().equals(edificioS)){
                            System.out.println("Se identifico el edificio");
                            System.out.println("Ingrese las especificaciones de la sala a continuacion:");
                            System.out.println("Nombre de la sala/laboratorio :");
                            String nombreS = entrada.nextLine();
                            System.out.println("Ingrese la cantidad de sillas:");
                            int sillas = entrada.nextInt();
                            System.out.println("Ingrese la cantidad de mesas:");
                            int mesas = entrada.nextInt();
                            System.out.println("Ingese la cantidad de datas:");
                            int datas = entrada.nextInt();
                            System.out.println("Ingrese la cantidad de telones:");
                            int telones = entrada.nextInt();
                            System.out.println("Ingrese la cantidad de computadores:");
                            int compus = entrada.nextInt();
                            System.out.println("Ingrese cantidad de pizarras:");
                            int pizarras = entrada.nextInt();
                            System.out.println("Ingrese capacidad:");
                            int capaci = entrada.nextInt();
                            System.out.println("La sala que se creará es un laboratorio[1] o una sala de clases[2]?");
                            int opS = entrada.nextInt();
                            if (opS == 1){
                                edificio.agregarLaboratorio(nombreS, "Actividad no registrada", capaci, compus, datas,
                                        mesas, pizarras, telones, sillas);
                                System.out.println("Laboratorio creado correctamente");
                            }
                            else if (opS == 2){
                                edificio.agregarSalaClases(nombreS, "Actividad no registrada", capaci, compus, datas,
                                        mesas, pizarras, telones, sillas);
                                System.out.println("Sala creada correctamente");
                            }
                            else{
                                System.out.println("Hubo un error al crear la sala, tipo de sala incorrecto.");
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("Se procedera a destruir una sala o laboratorio. CUIDADO, esta accion puede ser " +
                            "irreversible si no se han serializado los elementos universitarios");
                    System.out.println("Edificios disponibles:");
                    universidad.getAllEdificios();
                    System.out.println("Ingrese el color del edificio en el que se eliminara la sala (Sensible a mayusculas" +
                            " y minusculas");
                    entrada.nextLine();
                    String edificioE = entrada.nextLine();
                    for (Edificio edificio: universidad.getEdificios()){
                        if(edificio.getColor().equals(edificioE)){
                            System.out.println("Edificio encontrado");
                            System.out.println("En este edificio hay "+edificio.getCantSalas()+" salas y "+edificio.getCantLabs()+" laboratorios");
                            System.out.println("Desea eliminar una sala de clase[1] o un laboratorio[2]?");
                            int opS = entrada.nextInt();
                            if (opS == 1) {
                                System.out.print("Salas disponibles: ");
                                edificio.printSalas();
                                System.out.println();
                                System.out.println("Ingrese el nombre de la sala a eliminar" +
                                        "(Sensible a mayusculas y minusculas)");
                                entrada.nextLine();
                                String nSala = entrada.nextLine();
                                for (Iterator<SaladeClase> sala = edificio.getSalas().iterator(); sala.hasNext(); ) {
                                    SaladeClase salaActual = sala.next();
                                    if (salaActual.getNombreSala().equals(nSala)) {
                                        System.out.println("Sala encontrada");
                                        System.out.println("ADVERTENCIA: SE ELIMINARA ESTA SALA Y ESTA ACCION PUEDE SER" +
                                                "IRREVERSIBLE");
                                        System.out.println("Desea continuar? 1 para confirmar y 2 para cancelar");
                                        int opD = entrada.nextInt();
                                        if (opD == 1) {
                                            sala.remove();
                                        } else {
                                            System.out.println("Proceso de eliminacion cancelado");
                                        }
                                    }
                                }
                            }
                            else if (opS == 2){
                                System.out.println("Laboratorios disponibles: ");
                                edificio.printLabs();
                                System.out.println();
                                System.out.println("Ingrese el nombre del laboratorio a eliminar" +
                                        "(Sensible a mayusculas y minusculas)");
                                entrada.nextLine();
                                String nLab = entrada.nextLine();
                                for (Iterator<Laboratorio> laboratorio = edificio.getLaboratorios().iterator(); laboratorio.hasNext();){
                                    Laboratorio labactual = laboratorio.next();
                                    if (labactual.getNombreSala().equals(nLab)){
                                        System.out.println("Laboratorio encontrado");
                                        System.out.println("ADVERTENCIA: SE ELIMINARA ESTE LAB Y ESTA ACCION PUEDE SER" +
                                                "IRREVERSIBLE");
                                        System.out.println("Desea continuar? 1 para confirmar y 2 para cancelar");
                                        int opD = entrada.nextInt();
                                        if (opD == 1){
                                            laboratorio.remove();

                                        }
                                        else{
                                            System.out.println("Proceso de eliminacion cancelado");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 8:
                    System.out.println("Esta es una funcion adicional al enunciado y podrias causar problemas con el" +
                            "programa");
                    System.out.println("Ingrese el nuevo inicio del semestre: FORMATO dd-mm-aaaa (No usar este formato lanzará una excepcion)");
                    String fechaI = entrada.nextLine();
                    SimpleDateFormat fechaForm = new SimpleDateFormat("dd-MM-yyyy");
                    Date inicioSemestre = fechaForm.parse(fechaI);
                    System.out.println("Ingrese el nuevo final del semestre: FORMATO dd-mm-aaaa (No usar este formato lanzará una excepcion)");
                    String fechaF = entrada.nextLine();
                    Date finalSemestre = fechaForm.parse(fechaF);
                    universidad.setInicioSemestre(inicioSemestre);
                    universidad.setFinalSemestre(finalSemestre);
                    System.out.println("Fecha cambiada con exito!");
                case 9:
                    System.out.println("Cerrando sesion...");
                    break;
                default:
                    throw new IllegalStateException("Valor inseperado: " + op);
            }
        }while (op != 9);

    }
    public Perfil getPerfilAdmin() {
        return perfilAdmin;
    }

    public void setPerfilAdmin(Perfil perfilAdmin) {
        this.perfilAdmin = perfilAdmin;
    }

}