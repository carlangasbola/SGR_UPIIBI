package Paquete.Managedbean;

import Paquete.Beans.Mensajes;
import Paquete.Pojos.UnidadAprendizaje;
import Paquete.Pojos.UnidadTematica;
import Paquete.Utility.HibernateUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@ManagedBean(name = "UnidadAprendizaje")
@javax.faces.bean.ViewScoped
public class ManagedBeanUnidadAprendizaje implements java.io.Serializable {

    private UnidadAprendizaje unidadAprendizaje;
    private List<UnidadTematica> unidadesTematicas;
    private List<UnidadAprendizaje> unidadAprendizajeSeleccionada;

    @PostConstruct
    public void init() {
        unidadesTematicas = new java.util.ArrayList();
        unidadAprendizaje = new UnidadAprendizaje();
        unidadesTematicas.add(new UnidadTematica());
    }

    public void addInput() {
        unidadesTematicas.add(new UnidadTematica());
    }

    public void deleteInput() {
        Mensajes mensaje = new Mensajes();
        if (unidadesTematicas.size() - 1 == 0) {
            mensaje.setMessage("No hay datos que eliminar. Debe existir minímo una unidad temática en cada unidad de aprendizaje.");
            mensaje.warn();
        } else {
            unidadesTematicas.remove(unidadesTematicas.size() - 1);
            mensaje.setMessage("Removido.");
            mensaje.info();
        }
    }

    public List<UnidadAprendizaje> obtenerUnidadesAprendizaje() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM UnidadAprendizaje");
        List<UnidadAprendizaje> unidadesAprendizajeTematicas = q.list();
        return unidadesAprendizajeTematicas;
    }

    public void insertUnidadAprendizajeYTematica() {
        Mensajes mensaje = new Mensajes();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(unidadAprendizaje);

            for (UnidadTematica unidadT : unidadesTematicas) {
                UnidadTematica unidadTematica = new UnidadTematica();
                unidadTematica.setNombre(unidadT.getNombre());
                unidadTematica.setUnidadAprendizaje(unidadAprendizaje);
                session.save(unidadTematica);
            }

            tx.commit();
            mensaje.setMessage("Guardado con éxito");
            mensaje.info();
        } catch (RuntimeException e) {
            tx.rollback();
            mensaje.setMessage("No se puedo insertar el usuario, consulte el log para mas detalles");
            mensaje.fatal();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void EliminarUnidadAprendizaje(int id_UnidadAprendizaje) {
        Mensajes mensaje = new Mensajes();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete((UnidadAprendizaje) session.get(UnidadAprendizaje.class, Integer.valueOf(id_UnidadAprendizaje)));
            tx.commit();
            mensaje.setMessage("Eliminado del sistema");
            mensaje.info();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
                mensaje.setMessage("No se pudo eliminar, contacte el log de errores");
                mensaje.warn();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void UnidadesAprendizajeGrupo(int id_Grupo) {
    }
    
    
    //Getters y Setters

    public UnidadAprendizaje getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public List<UnidadTematica> getUnidadesTematicas() {
        return unidadesTematicas;
    }

    public void setUnidadesTematicas(List<UnidadTematica> unidadesTematicas) {
        this.unidadesTematicas = unidadesTematicas;
    }
    
    public List<UnidadAprendizaje> getUnidadAprendizajeSeleccionada() {
        return unidadAprendizajeSeleccionada;
    }

    public void setUnidadAprendizajeSeleccionada(List<UnidadAprendizaje> unidadAprendizajeSeleccionada) {
        this.unidadAprendizajeSeleccionada = unidadAprendizajeSeleccionada;
    }
}
