package Paquete.Managedbean;

import Paquete.Pojos.UnidadTematica;
import Paquete.Utility.HibernateUtil;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;

@Named("UnidadTematica")
@Dependent
public class ManagedBeanUnidadTematica {

    public ManagedBeanUnidadTematica() {
    }

    public List<UnidadTematica> obtenerUnidadTematica(int Id_UnidadAprendizaje) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("FROM UnidadTematica WHERE unidadAprendizaje.idUnidadAprendizaje = :id");
        q.setParameter("id", Id_UnidadAprendizaje);
        List<UnidadTematica> unidadesTematicas = q.list();
        return unidadesTematicas;
    }
}
