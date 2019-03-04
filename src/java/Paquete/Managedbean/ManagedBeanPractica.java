package Paquete.Managedbean;

import Paquete.Pojos.Practica;
import Paquete.Utility.HibernateUtil;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Named("Practica")
@RequestScoped
public class ManagedBeanPractica {

    private Practica practica;

    public ManagedBeanPractica() {
    }

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    @PostConstruct
    private void init() {
        practica = new Practica();
    }

    public void guardarPractica() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.save(practica);
    }
}
