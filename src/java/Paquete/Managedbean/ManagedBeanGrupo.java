package Paquete.Managedbean;

import Paquete.Beans.Mensajes;
import Paquete.Pojos.Grupo;
import Paquete.Utility.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Named("Grupo")
@RequestScoped
public class ManagedBeanGrupo {

    public ManagedBeanGrupo() {
    }

    private Grupo grupo;
    private List<Grupo> gruposSeleccionados;

    @PostConstruct
    private void init() {
        grupo = new Grupo();
        gruposSeleccionados = new ArrayList();
    }

    public void eliminarGrupo(int idGrupo) {
        Mensajes mensaje = new Mensajes();
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete((Grupo) session.get(Grupo.class, idGrupo));
            tx.commit();

            mensaje.setMessage("Operación éxitosa");
            mensaje.info();
        } catch (RuntimeException e) {
            tx.rollback();
            mensaje.setMessage("No se pudo completar la transacción");
            mensaje.fatal();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Long cantidadUnidadesAprendizaje(int idGrupo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM UnidadGrupo WHERE grupo.idGrupo = :idGrupo");
        q.setParameter("idGrupo", idGrupo);
        Long count = (Long) q.uniqueResult();
        session.close();
        return count;
    }

    public List<Grupo> obtenerGrupos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Grupo> grupos = new ArrayList();
        Query query = session.createQuery("FROM Grupo");
        grupos = query.list();
        session.close();
        return grupos;
    }

    public void insertarGrupo() {
        Mensajes mensaje = new Mensajes();
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(grupo);
            tx.commit();
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGruposSeleccionados() {
        return gruposSeleccionados;
    }

    public void setGruposSeleccionados(List<Grupo> gruposSeleccionados) {
        this.gruposSeleccionados = gruposSeleccionados;
    }
}
