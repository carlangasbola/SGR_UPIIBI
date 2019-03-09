package Paquete.Managedbean;

import Paquete.Beans.Mensajes;
import Paquete.Pojos.DatosPractica;
import Paquete.Pojos.Grupo;
import Paquete.Pojos.Practica;
import Paquete.Pojos.UnidadTematica;
import Paquete.Pojos.Usuarios;
import Paquete.Utility.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Named("Practica")
@SessionScoped
public class ManagedBeanPractica implements Serializable {

    private Practica practica;
    private DatosPractica datosPractica;

    public ManagedBeanPractica() {
    }

    @PostConstruct
    private void init() {
        practica = new Practica();
        datosPractica = new DatosPractica();
    }

    public void guardarPractica() {
        
        Mensajes mensaje = new Mensajes();
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            practica.setCreacion( new Date() );
            practica.setUsuarios( (Usuarios) session.get(Usuarios.class, 37) );
            practica.setUnidadTematica( (UnidadTematica) session.get(UnidadTematica.class, 56) );
            
            session.save(practica);
            
            datosPractica.setIdPractica(practica.getIdPractica());
            datosPractica.setPractica(practica);
            session.save(datosPractica);
            
            tx.commit();

            mensaje.setMessage("Operación éxitosa");
            mensaje.info();
        } catch (RuntimeException e) {
            tx.rollback();
            mensaje.setMessage("No se pudo completar la transacción");
            mensaje.fatal();
          
        } finally {
            if (session != null) {
                session.close();
            }
        }
       
    }
    
    
    public List<Practica> obtenerPracticas(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Practica> practicas = new ArrayList();
        Query query = session.createQuery("FROM Practica");
        practicas = query.list();
        return practicas;
    }
    
    public String ir_Practica2(){
        return "creacionPractica2";
    }

    // GETTERS Y SETTERS
    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    public DatosPractica getDatosPractica() {
        return datosPractica;
    }

    public void setDatosPractica(DatosPractica datosPractica) {
        this.datosPractica = datosPractica;
    }
}
