package Paquete.Pojos;
// Generated 3/03/2019 08:38:04 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * HistorialEquipo generated by hbm2java
 */
public class HistorialEquipo  implements java.io.Serializable {


     private int idHistorialEquipo;
     private EquipoLaboratorio equipoLaboratorio;
     private SesionDeLaboratorio sesionDeLaboratorio;
     private Date fecha;

    public HistorialEquipo() {
    }

    public HistorialEquipo(int idHistorialEquipo, EquipoLaboratorio equipoLaboratorio, SesionDeLaboratorio sesionDeLaboratorio, Date fecha) {
       this.idHistorialEquipo = idHistorialEquipo;
       this.equipoLaboratorio = equipoLaboratorio;
       this.sesionDeLaboratorio = sesionDeLaboratorio;
       this.fecha = fecha;
    }
   
    public int getIdHistorialEquipo() {
        return this.idHistorialEquipo;
    }
    
    public void setIdHistorialEquipo(int idHistorialEquipo) {
        this.idHistorialEquipo = idHistorialEquipo;
    }
    public EquipoLaboratorio getEquipoLaboratorio() {
        return this.equipoLaboratorio;
    }
    
    public void setEquipoLaboratorio(EquipoLaboratorio equipoLaboratorio) {
        this.equipoLaboratorio = equipoLaboratorio;
    }
    public SesionDeLaboratorio getSesionDeLaboratorio() {
        return this.sesionDeLaboratorio;
    }
    
    public void setSesionDeLaboratorio(SesionDeLaboratorio sesionDeLaboratorio) {
        this.sesionDeLaboratorio = sesionDeLaboratorio;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}


