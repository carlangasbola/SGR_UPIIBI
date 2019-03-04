package Paquete.Managedbean;

import Paquete.Beans.Mensajes;
import Paquete.Pojos.DatosUsuario;
import Paquete.Pojos.Roles;
import Paquete.Pojos.Usuarios;
import Paquete.Utility.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



@Named("DatosUsuario")
@RequestScoped
public class ManagedBeanDatosUsuario
{
  public ManagedBeanDatosUsuario() {}
  
  private DatosUsuario datosUsuario = new DatosUsuario();
  private Roles roles = new Roles();
  
  public String obtenerDatosUsuario(int idUsuario) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    datosUsuario = ((DatosUsuario)session.get(DatosUsuario.class, Integer.valueOf(idUsuario)));
    return "EditarDatosUsuario";
  }
  
  public void actualizarDatosUsuario()
  {
    Mensajes mensaje = new Mensajes();
    Session session = null;
    Transaction tx = null;
    try
    {
      session = HibernateUtil.getSessionFactory().openSession();
      tx = session.beginTransaction();
      
      Usuarios u = new Usuarios();
      u = (Usuarios)session.get(Usuarios.class, Integer.valueOf(datosUsuario.getUsuarios().getIdUsuarios()));
      u.setLogin(datosUsuario.getCorreo());
      u.setPasssword(datosUsuario.getIdentificador());
      u.setRoles((Roles)session.get(Roles.class, Integer.valueOf(roles.getIdRol())));
      session.update(u);
      
      datosUsuario.setUsuarios(u);
      session.update(datosUsuario);
      
      tx.commit();
      mensaje.setMessage("Usuario agregado");
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
  
  public void InsertDatosUsuario()
  {
    Mensajes mensaje = new Mensajes();
    Session session = null;
    Transaction tx = null;
    try
    {
      session = HibernateUtil.getSessionFactory().openSession();
      tx = session.beginTransaction();
      tx.setTimeout(5);
      
      Usuarios u = new Usuarios();
      u.setLogin(datosUsuario.getCorreo());
      u.setPasssword(datosUsuario.getIdentificador());
      u.setRoles((Roles)session.get(Roles.class, Integer.valueOf(roles.getIdRol())));
      session.save(u);
      
      datosUsuario.setUsuarios(u);
      session.save(datosUsuario);
      
      tx.commit();
      mensaje.setMessage("Usuario agregado");
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
  
  public List<DatosUsuario> ObtenerDatosUsuarios()
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<DatosUsuario> datosUsuarios = new ArrayList();
    Query query = session.createQuery("FROM DatosUsuario");
    datosUsuarios = query.list();
    return datosUsuarios;
  }
  
  public List<DatosUsuario> obtenerDatosUsuarioRol(int idRol)
  {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<DatosUsuario> datosUsuarios = new ArrayList();
    Query query = session.createQuery("FROM DatosUsuario WHERE usuarios.roles.idRol = :idRol");
    query.setParameter("idRol", Integer.valueOf(idRol));
    datosUsuarios = query.list();
    return datosUsuarios;
  }
  

  public DatosUsuario getDatosUsuario()
  {
    return datosUsuario;
  }
  
  public void setDatosUsuario(DatosUsuario datosUsuario) {
    this.datosUsuario = datosUsuario;
  }
  
  public Roles getRoles() {
    return roles;
  }
  
  public void setRoles(Roles roles) {
    this.roles = roles;
  }
}