package asignacionsalas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public abstract class Sala {

    private String nombreSala;

    private int capacidad;

    private boolean propuesta;

    private boolean confirmada;

    private String actividad;

    private int sillas;

    private int mesas;

    private ArrayList<InsumosTecnologicos> datas = new ArrayList<InsumosTecnologicos>();

    private ArrayList<InsumosTecnologicos> computadores = new ArrayList<InsumosTecnologicos>();

    private int telones;

    private int pizarras;

    private Date fechaReservaConfirmada;

    private ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isPropuesta() {
        return propuesta;
    }

    public void setPropuesta(boolean propuesta) {
        this.propuesta = propuesta;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }
    /*
    public Profesor getProfResponsable() {
        return profResponsable;
    }

    public void setProfResponsable(Profesor profResponsable) {
        this.profResponsable = profResponsable;
    }*/

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getSillas() {
        return sillas;
    }

    public void setSillas(int sillas) {
        this.sillas = sillas;
    }

    public int getMesas() {
        return mesas;
    }

    public void setMesas(int mesas) {
        this.mesas = mesas;
    }

    public int getTelones() {
        return telones;
    }

    public void setTelones(int telones) {
        this.telones = telones;
    }

    public int getPizarras() {
        return pizarras;
    }

    public void setPizarras(int pizarras) {
        this.pizarras = pizarras;
    }

    /***
     *
     * @param tipo Es un int que indica que clase de insumo es. 1 para PC y 2 para Data
     */
    public void registrarInsumoT(int tipo, int id){
        InsumosTecnologicos insumo = new InsumosTecnologicos();
        insumo.setID(id);
        insumo.setEstado("Bueno");
        if (tipo == 1){
            computadores.add(insumo);
        }
        else{
            datas.add(insumo);
        }
    }

    public void registrarIncidenciaS(Universidad universidad){
        Scanner entrada  = new Scanner(System.in);
        System.out.println("Se registrará una incidencia de en esta Sala: "+getNombreSala());
        System.out.println("Sobre que tipo de insumo desea realizar un reporte: Computador[1], Data[2] o Espacio[3]");
        int op = entrada.nextInt();
        switch (op) {
            case 1:
                System.out.println("Has elegido Computador");
                System.out.println("Computadores disponible en esta sala:\n");
                for (InsumosTecnologicos computador : computadores) {
                    System.out.println("Computador n°" + computador.getID());
                }
                System.out.println("Ingrese el id del computador que presenta una incidencia:");
                int id = entrada.nextInt();
                InsumosTecnologicos pcIncidencia = null;
                for (InsumosTecnologicos computador : computadores) {
                    if (computador.getID() == id) {
                        pcIncidencia = computador;
                    }
                }

                if (pcIncidencia == null) {
                    System.out.println("Este id no existe. Terminando registro de incidencia");
                } else {
                    System.out.println("Se registrará una incidencia sobre el computador n°:" + pcIncidencia.getID());
                    System.out.println("Ingrese el estado actual del computador: Bueno [1], regular[2] o malo[3]");
                    entrada.nextLine();
                    String estado = entrada.nextLine();
                    pcIncidencia.setEstado(estado);
                    Incidencia incidencia = new Incidencia();
                    System.out.println("Ingrese los detalles de la incidencia:");
                    incidencia.setDetalleIncidencia(entrada.nextLine());
                    incidencia.setInsumoProblema(pcIncidencia);
                }
                break;
            case 2:
                System.out.println("Has elegido Data");
                System.out.println("Data(s) disponible en esta sala:\n");
                for (InsumosTecnologicos data : datas) {
                    System.out.println("Data n°" + data.getID());
                }
                System.out.println("Ingrese el id del data que presenta una incidencia:");
                int idD = entrada.nextInt();
                InsumosTecnologicos dataIncidencia = null;
                for (InsumosTecnologicos data : datas) {
                    if (data.getID() == idD) {
                        dataIncidencia = data;
                    }
                }

                if (dataIncidencia == null) {
                    System.out.println("Este id no existe. Terminando registro de incidencia");
                }
                else {
                    System.out.println("Se registrará una incidencia sobre el data n°:" + dataIncidencia.getID());
                    System.out.println("Ingrese el estado actual del data: Bueno [1], regular[2] o malo[3]");
                    entrada.nextLine();
                    String estado = entrada.nextLine();
                    dataIncidencia.setEstado(estado);
                    Incidencia incidencia = new Incidencia();
                    System.out.println("Ingrese los detalles de la incidencia:");
                    incidencia.setDetalleIncidencia(entrada.nextLine());
                    incidencia.setRolEncargado("DTI");
                    incidencia.setInsumoProblema(dataIncidencia);
                    incidencias.add(incidencia);
                }
                break;

            case 3:
                System.out.println("Has elegido realizar un reporte sobre esta sala");
                Incidencia incidencia = new Incidencia();
                System.out.println("Ingrese los detalles de la incidencia:");
                incidencia.setDetalleIncidencia(entrada.nextLine());
                incidencia.setRolEncargado("Administrador");
                incidencias.add(incidencia);
                break;

        }
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
}
