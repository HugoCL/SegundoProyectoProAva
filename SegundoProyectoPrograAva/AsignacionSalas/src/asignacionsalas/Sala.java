package asignacionsalas;

import java.util.ArrayList;
import java.util.Date;

public abstract class Sala {

    private String nombreSala;

    private int capacidad;

    private boolean propuesta;

    private boolean confirmada;

    private String profResponsable;

    private String actividad;

    private int sillas;

    private int mesas;

    private ArrayList<InsumosTecnologicos> datas = new ArrayList<InsumosTecnologicos>();

    private ArrayList<InsumosTecnologicos> computadores = new ArrayList<InsumosTecnologicos>();

    private int telones;

    private int pizarras;

    private Date fechaReservaConfirmada;

    private Incidencia[] incidencias;

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

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
}
