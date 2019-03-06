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
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author iron1
 */
@Named(value = "UnidadGrupo")
@RequestScoped
public class ManagedBeanUnidadGrupo implements Serializable {

    private Usuarios docente;
    private UnidadAprendizaje unidadAprendizaje;
    private Grupo grupo;
    
    @PostConstruct
    private void init() {
        docente = new Usuarios();
        unidadAprendizaje = new UnidadAprendizaje();
        grupo = new Grupo();
    }
    
    
    public List<UnidadGrupo> obtenerUnidadesAprendizaje(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM UnidadGrupo");
        return q.list();
    }

    public void insertUnidadGrupo() {
        Mensajes mensaje = new Mensajes();
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
             UnidadGrupo unidadGrupo = new UnidadGrupo();
             unidadGrupo.setUsuarios( (Usuarios)session.get(Usuarios.class, docente.getIdUsuarios()) );
             unidadGrupo.setUnidadAprendizaje((UnidadAprendizaje)session.get(UnidadAprendizaje.class, unidadAprendizaje.getIdUnidadAprendizaje() ));
             unidadGrupo.setGrupo( (Grupo)session.get(Grupo.class, grupo.getIdGrupo()) );

            session.save(unidadGrupo);

            tx.commit();
            mensaje.setMessage("Agregado con exito");
            mensaje.info();
        } catch (RuntimeException e) {
            tx.rollback();
            mensaje.setMessage("No se puedo insertar la unidad grupo, consulte el log para mas detalles");
            mensaje.fatal();
        } finally {
            if (session != null) {
                session.close();
            }
        }
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
