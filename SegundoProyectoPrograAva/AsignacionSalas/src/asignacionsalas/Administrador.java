package asignacionsalas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Administrador extends Responsables {


    private Perfil perfilAdmin;

    public void crearPerfilAdmin(String user, String pass) {
        Perfil perfil = new Perfil();
        perfil.setNombreUsuario(user);
        perfil.setPassword(pass);
        setAdmin(true);
        setPerfilAdmin(perfil);
    }

    public void cambioSala(Universidad universidad) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Cambio de salas");
        System.out.println("Ingrese el edificio: ");
        System.out.print("Edificios disponibles: ");
        universidad.getAllEdificios();
        System.out.println();
        System.out.println("Ingrese edificio de la sala a reservar");
        String edificioEle = entrada.nextLine();
        for (Edificio edificio : universidad.getEdificios()) {
            if (edificio.getColor().equals(edificioEle)) {
                System.out.println("\nEdificio encontrado!");
                System.out.println("En este edificio hay " + edificio.getCantSalas() + " salas y " + edificio.getCantLabs() + " laboratorios");
                System.out.print("Salas disponibles: ");
                edificio.printSalas();
                System.out.println();
                System.out.println("Laboratorios disponibles: ");
                edificio.printLabs();
            }
        }
    }

    public void menuAdmin(Administrador admin, Universidad universidad, Date iSemestre, Date fSemestre) throws ParseException {
        Scanner entrada = new Scanner(System.in);
        int op = 0;
        System.out.println("MENU DE ADMIN");
        System.out.println("[1] Mostrar todas las reservas hechas por orden(Propuestas)");
        System.out.println("[2] Mostrar todas las reservas hechas (Confirmadas)");
        System.out.println("[3] Ver todos los incidentes");
        System.out.println("[4] Crear un nuevo estudiante o profesor");
        System.out.println("[5] Confirmar una reserva propuesta");
        System.out.println("[6] Crear nueva sala");
        System.out.println("[7] Destruir sala");
        System.out.println("[X] Cerrar sesion");
        System.out.print("Ingrese su opcion -> ");
        op = entrada.nextInt();
        switch (op) {
            case 1:
                System.out.println("A continuacion se mostraran todas las reservas hechas");
                ArrayList<Edificio> edificios = universidad.getEdificios();
                for(Edificio edificio: edificios){
                    ArrayList<SaladeClase> salas = edificio.getSalas();
                    for(SaladeClase sala: salas){
                        sala.getPropuestasOrdenadas();
                    }
                }
                break;
            case 2:
                // PENDIENTE!!!!
                break;
            case 3:
                // PENDIENTE!!!
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
                // PENDIENTE
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
                System.out.println("Cerrando sesion...");
                break;
            default:
                throw new IllegalStateException("Valor inseperado: " + op);
        }
    }
    public Perfil getPerfilAdmin() {
        return perfilAdmin;
    }

    public void setPerfilAdmin(Perfil perfilAdmin) {
        this.perfilAdmin = perfilAdmin;
    }

}