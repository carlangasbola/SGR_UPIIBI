/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquete.Managedbean;

import Paquete.Beans.Mensajes;
import Paquete.Pojos.Grupo;
import Paquete.Pojos.UnidadAprendizaje;
import Paquete.Pojos.UnidadGrupo;
import Paquete.Pojos.Usuarios;
import Paquete.Utility.HibernateUtil;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author iron1
 */
@Named(value = "UnidadGrupo")
@RequestScoped
public class ManagedBeanUnidadGrupo {

    private Usuarios docente;
    private UnidadAprendizaje unidadAprendizaje;
    private Grupo grupo;

    @PostConstruct
    private void init() {
        docente = new Usuarios();
        unidadAprendizaje = new UnidadAprendizaje();
        grupo = new Grupo();
    }

    public List<UnidadGrupo> obtenerUnidadesAprendizaje() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM UnidadGrupo");
        return q.list();
    }

    public List<UnidadGrupo> obtenerUnidadesAprendizajeGrupo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM UnidadGrupo WHERE grupo.idGrupo = :id");
        q.setParameter("id", grupo.getIdGrupo());
        return q.list();
    }

    public void insertUnidadGrupo() {
        Mensajes mensaje = new Mensajes();
        Session session = null;
        Transaction tx = null;
        ///////////// ESTA ES LA UNICA FORMA EN LA QUE PUDE HACER ESTA SELECCIÓN
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        grupo.setIdGrupo((int) sessionMap.get("Id_Grupo"));
        //////////////////////////////////////
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            UnidadGrupo unidadGrupo = new UnidadGrupo();
            unidadGrupo.setUsuarios((Usuarios) session.get(Usuarios.class, docente.getIdUsuarios()));
            unidadGrupo.setUnidadAprendizaje((UnidadAprendizaje) session.get(UnidadAprendizaje.class, unidadAprendizaje.getIdUnidadAprendizaje()));
            unidadGrupo.setGrupo((Grupo) session.get(Grupo.class, grupo.getIdGrupo()));

            session.save(unidadGrupo);

            tx.commit();
            mensaje.setMessage("Agregado con exito");
            mensaje.info();
        } catch (RuntimeException e) {
            tx.rollback();
            mensaje.setMessage("No se puedo insertar la unidad grupo, probablemente ya esta asignada");
            mensaje.fatal();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public String redirect() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.remove("Id_Grupo");
        sessionMap.put("Id_Grupo", grupo.getIdGrupo());
        return "RelacionarUnidadAprendizajeGrupo";
    }

    // Getters y Setters
    public Usuarios getDocente() {
        return docente;
    }

    public void setDocente(Usuarios docente) {
        this.docente = docente;
    }

    public UnidadAprendizaje getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
