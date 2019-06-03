package asignacionsalas;

import java.util.ArrayList;
import java.util.Scanner;

public class Incidencia {

    private String detalleIncidencia;

    private DTI EncargadoDTI;

    private Administrativo EncargadoAdmin;

    private String rolEncargado;

    private InsumosTecnologicos insumoProblema;

    public String getDetalleIncidencia() {
        return detalleIncidencia;
    }

    public void setDetalleIncidencia(String detalleIncidencia) {
        this.detalleIncidencia = detalleIncidencia;
    }

    public DTI getEncargadoDTI() {
        return EncargadoDTI;
    }

    public void setEncargado(DTI Encargado) {
        this.EncargadoDTI = Encargado;
    }

    public Administrativo getEncargadoAdmin() {
        return EncargadoAdmin;
    }

    public void setEncargadoAdmin(Administrativo encargadoAdmin) {
        EncargadoAdmin = encargadoAdmin;
    }

    public String getRolEncargado() {
        return rolEncargado;
    }

    public void setRolEncargado(String rolEncargado) {
        this.rolEncargado = rolEncargado;
    }

    public InsumosTecnologicos getInsumoProblema() {
        return insumoProblema;
    }

    public void setInsumoProblema(InsumosTecnologicos insumoProblema) {
        this.insumoProblema = insumoProblema;
    }

    public void asignarEncargadoDTI(ArrayList<DTI> empleadosDTI){
        Scanner entrada  = new Scanner(System.in);
        System.out.println("Ingrese el empleado de DTI que se encargará del problema:");
        for (int i = 0; i < empleadosDTI.size(); i++) {
            System.out.println("["+i+"] "+empleadosDTI.get(i).getNombreCompleto());
        }
        System.out.println("Ingrese el numero del empleado que desea asignar");
        int op = entrada.nextInt();
        setEncargado(empleadosDTI.get(op));
        setRolEncargado("DTI");
    }

    public void asignarEncargadoAdmin(ArrayList<Administrativo> administrativos){
        Scanner entrada  = new Scanner(System.in);
        System.out.println("Ingrese el administrativo que se encargará del problema:");
        for (int i = 0; i < administrativos.size(); i++) {
            System.out.println("["+i+"] "+administrativos.get(i).getNombreCompleto());
        }
        System.out.println("Ingrese el numero del administrativo que desea asignar");
        int op = entrada.nextInt();
        setEncargadoAdmin(administrativos.get(op));
        setRolEncargado("Administrativo");
    }
}
