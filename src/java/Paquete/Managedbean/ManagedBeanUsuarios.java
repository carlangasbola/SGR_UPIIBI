package Paquete.Managedbean;

import Paquete.Beans.Mensajes;
import Paquete.Pojos.Usuarios;
import Paquete.Utility.HibernateUtil;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Named("Usuarios")
@RequestScoped
public class ManagedBeanUsuarios {

    public ManagedBeanUsuarios() {
    }

    public void EliminarUsuario(int idUsuario) {
        Mensajes mensaje = new Mensajes();
        Transaction transObj = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transObj = session.beginTransaction();
            session.delete((Usuarios) session.get(Usuarios.class, Integer.valueOf(idUsuario)));

            transObj.commit();
            mensaje.setMessage("Eliminado del sistema");
            mensaje.info();
        } catch (HibernateException exObj) {
            if (transObj != null) {
                transObj.rollback();
                mensaje.setMessage("No se pudo eliminar, contacte el log de errores");
                mensaje.warn();
            }
        } finally {
            session.close();
        }
    }

    public long numeroUsuariosRegistrados() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("select count(*) from Usuarios");
        List l = q.list();
        Object[] result = (Object[]) l.get(0);

        Long res1 = (Long) result[0];
        long count = res1.longValue();
        return count;
    }
}
