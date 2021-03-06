package Paquete.Pojos;
// Generated 3/03/2019 08:38:04 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * UnidadGrupo generated by hbm2java
 */
public class UnidadGrupo  implements java.io.Serializable {


     private int idUnidadGrupo;
     private Grupo grupo;
     private UnidadAprendizaje unidadAprendizaje;
     private Usuarios usuarios;
     private Set listaGrupos = new HashSet(0);
     private Set sesionDeLaboratorios = new HashSet(0);
     private Set equipos = new HashSet(0);
     private Set eventoses = new HashSet(0);

    public UnidadGrupo() {
    }

	
    public UnidadGrupo(int idUnidadGrupo, Grupo grupo, UnidadAprendizaje unidadAprendizaje, Usuarios usuarios) {
        this.idUnidadGrupo = idUnidadGrupo;
        this.grupo = grupo;
        this.unidadAprendizaje = unidadAprendizaje;
        this.usuarios = usuarios;
    }
    public UnidadGrupo(int idUnidadGrupo, Grupo grupo, UnidadAprendizaje unidadAprendizaje, Usuarios usuarios, Set listaGrupos, Set sesionDeLaboratorios, Set equipos, Set eventoses) {
       this.idUnidadGrupo = idUnidadGrupo;
       this.grupo = grupo;
       this.unidadAprendizaje = unidadAprendizaje;
       this.usuarios = usuarios;
       this.listaGrupos = listaGrupos;
       this.sesionDeLaboratorios = sesionDeLaboratorios;
       this.equipos = equipos;
       this.eventoses = eventoses;
    }
   
    public int getIdUnidadGrupo() {
        return this.idUnidadGrupo;
    }
    
    public void setIdUnidadGrupo(int idUnidadGrupo) {
        this.idUnidadGrupo = idUnidadGrupo;
    }
    public Grupo getGrupo() {
        return this.grupo;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public UnidadAprendizaje getUnidadAprendizaje() {
        return this.unidadAprendizaje;
    }
    
    public void setUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public Set getListaGrupos() {
        return this.listaGrupos;
    }
    
    public void setListaGrupos(Set listaGrupos) {
        this.listaGrupos = listaGrupos;
    }
    public Set getSesionDeLaboratorios() {
        return this.sesionDeLaboratorios;
    }
    
    public void setSesionDeLaboratorios(Set sesionDeLaboratorios) {
        this.sesionDeLaboratorios = sesionDeLaboratorios;
    }
    public Set getEquipos() {
        return this.equipos;
    }
    
    public void setEquipos(Set equipos) {
        this.equipos = equipos;
    }
    public Set getEventoses() {
        return this.eventoses;
    }
    
    public void setEventoses(Set eventoses) {
        this.eventoses = eventoses;
    }




}


