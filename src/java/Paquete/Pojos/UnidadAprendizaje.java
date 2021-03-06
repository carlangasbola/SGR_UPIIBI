package Paquete.Pojos;
// Generated 3/03/2019 08:38:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * UnidadAprendizaje generated by hbm2java
 */
public class UnidadAprendizaje  implements java.io.Serializable {


     private int idUnidadAprendizaje;
     private String nombre;
     private Set unidadGrupos = new HashSet(0);
     private Set unidadTematicas = new HashSet(0);

    public UnidadAprendizaje() {
    }

	
    public UnidadAprendizaje(int idUnidadAprendizaje, String nombre) {
        this.idUnidadAprendizaje = idUnidadAprendizaje;
        this.nombre = nombre;
    }
    public UnidadAprendizaje(int idUnidadAprendizaje, String nombre, Set unidadGrupos, Set unidadTematicas) {
       this.idUnidadAprendizaje = idUnidadAprendizaje;
       this.nombre = nombre;
       this.unidadGrupos = unidadGrupos;
       this.unidadTematicas = unidadTematicas;
    }
   
    public int getIdUnidadAprendizaje() {
        return this.idUnidadAprendizaje;
    }
    
    public void setIdUnidadAprendizaje(int idUnidadAprendizaje) {
        this.idUnidadAprendizaje = idUnidadAprendizaje;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getUnidadGrupos() {
        return this.unidadGrupos;
    }
    
    public void setUnidadGrupos(Set unidadGrupos) {
        this.unidadGrupos = unidadGrupos;
    }
    public Set getUnidadTematicas() {
        return this.unidadTematicas;
    }
    
    public void setUnidadTematicas(Set unidadTematicas) {
        this.unidadTematicas = unidadTematicas;
    }




}


