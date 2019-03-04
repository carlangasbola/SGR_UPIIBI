package Paquete.Pojos;
// Generated 3/03/2019 08:38:04 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Residuos generated by hbm2java
 */
public class Residuos  implements java.io.Serializable {


     private int idResiduo;
     private SesionDeLaboratorio sesionDeLaboratorio;
     private byte[] nombre;
     private byte[] tipo;
     private double cantidad;
     private Date fechaDeIngreso;

    public Residuos() {
    }

    public Residuos(int idResiduo, SesionDeLaboratorio sesionDeLaboratorio, byte[] nombre, byte[] tipo, double cantidad, Date fechaDeIngreso) {
       this.idResiduo = idResiduo;
       this.sesionDeLaboratorio = sesionDeLaboratorio;
       this.nombre = nombre;
       this.tipo = tipo;
       this.cantidad = cantidad;
       this.fechaDeIngreso = fechaDeIngreso;
    }
   
    public int getIdResiduo() {
        return this.idResiduo;
    }
    
    public void setIdResiduo(int idResiduo) {
        this.idResiduo = idResiduo;
    }
    public SesionDeLaboratorio getSesionDeLaboratorio() {
        return this.sesionDeLaboratorio;
    }
    
    public void setSesionDeLaboratorio(SesionDeLaboratorio sesionDeLaboratorio) {
        this.sesionDeLaboratorio = sesionDeLaboratorio;
    }
    public byte[] getNombre() {
        return this.nombre;
    }
    
    public void setNombre(byte[] nombre) {
        this.nombre = nombre;
    }
    public byte[] getTipo() {
        return this.tipo;
    }
    
    public void setTipo(byte[] tipo) {
        this.tipo = tipo;
    }
    public double getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public Date getFechaDeIngreso() {
        return this.fechaDeIngreso;
    }
    
    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }




}


