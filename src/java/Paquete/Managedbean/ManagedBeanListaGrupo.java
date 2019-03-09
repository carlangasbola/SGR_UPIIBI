/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paquete.Managedbean;

import Paquete.Beans.UtilPath;
import Paquete.Pojos.DatosUsuario;
import Paquete.Pojos.ListaGrupo;
import Paquete.Pojos.Roles;
import Paquete.Pojos.UnidadGrupo;
import Paquete.Pojos.Usuarios;
import Paquete.Utility.HibernateUtil;
import java.io.File;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jxl.Sheet;
import jxl.Workbook;
import org.hibernate.Session;

/**
 *
 * @author iron1
 */
@Named(value = "ListaGrupo")
@RequestScoped
public class ManagedBeanListaGrupo {

    private Session hibernateSession;

    //Parametros para la creación de un usuario    

    private String nombre;
    private String paterno;
    private String materno;
    private String identificador;
  
    private int tipousuario = 4;
    private int rol = 4;
    private int idDatos = 4;
    private String filename = ManagedBeanUploadExcel.fileName;
    private int idUnidadGrupo = 9;

    public ManagedBeanListaGrupo() {
    }

    public void LeerArchivosExcel() {
        
        int i = idUnidadGrupo;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = UtilPath.getUrlDefinida(ec.getRealPath("/"));
        System.out.println(filename);
        String archivoDestino = realPath + File.separator + "web" + File.separator + "ExcelUpload" + File.separator + filename;
        int contador = 1;

        hibernateSession = HibernateUtil.getSessionFactory().openSession();
        UnidadGrupo ug = new UnidadGrupo();

        ug = (UnidadGrupo) hibernateSession.get(UnidadGrupo.class, idUnidadGrupo);

        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));

            //Recorre cada hoja
            for (int hojas = 0; hojas < archivoExcel.getNumberOfSheets(); hojas++) {
                Sheet hoja = archivoExcel.getSheet(hojas);
                int x = 0;
                int ideq = 0;
                int numColumnas = hoja.getColumns();
                int numFilas = hoja.getRows();
                String dato;
                  hibernateSession.beginTransaction();
                //Recorre cada fila de la hoja
                for (int fila = 1; fila < numFilas; fila++) {
                    for (int columna = 1; columna < numColumnas; columna++) {
                        dato = hoja.getCell(columna, fila).getContents();
                        System.out.print(dato + " ");
                        //Intruccion switch que evalua la variable contador
                        switch (contador) {
                            case 1:
                                identificador = dato;
                                contador++;
                                break;
                            case 2:
                                nombre = dato;
                                contador++;
                                break;
                            case 3:
                                paterno = dato;
                                contador++;
                                break;
                            case 4:
                                materno = dato;
                                contador = 1;
                                break;
                            //case 5:
                            //num_Equipo = dato;
                            //contador=1;
                            //break;
                            //case 6:
                            //password = dato;
                            //contador++;
                            //break;
                            //case 7:
                            //numeroSeguro = dato;
                            //contador++;
                            //break;
                            //case 8:
                            //identificador = dato;
                            //contador=1;
                            //break;    
                        }

                    }
                    ListaGrupo listgroup = new ListaGrupo();
                    Usuarios user = new Usuarios();
                    DatosUsuario datauser = new DatosUsuario();
 
                    Roles roles = new Roles();
                  

                    //List<UnidadGrupo> ug = consulta.crearSelectidUnidadGrupo("FROM UnidadGrupo WHERE unidadAprendizaje = " + unidad_aprendizaje +" AND grupo = " + grupo) ;
                    int idug = idUnidadGrupo;
                    //System.out.println("idunidadgrupo" + idug);
                    // Obetener el rol
                    roles.setIdRol(rol);
                    //El login y el password sera con el identificador
                    user.setLogin(identificador);
                    user.setPasssword(identificador);
                    user.setRoles(roles);
                    //Guardamos el usuario en la base de datos
                    hibernateSession.save(user);

                    //Llenamos la tabala de datos de usuario
                    datauser.setNombre(nombre);
                    datauser.setApellidoPaterno(paterno);
                    datauser.setApellidoMaterno(materno);
                    //datauser.setTelefono(telefono);
                    //datauser.setCorreo(correo);
                    //datauser.setNumeroSeguro(numeroSeguro);
                    datauser.setIdentificador(identificador);
                    // Hacemos la relacion de los datos de usuario con la tabla usuario
                    datauser.setUsuarios(user);
                    //Guardamos los datos del usuario
                    hibernateSession.save(datauser);
                    
                  

                    listgroup.setUsuarios(user);
                    listgroup.setUnidadGrupo(ug);
                    
                    hibernateSession.save(listgroup);
                    

                    

                }
                  hibernateSession.getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Agregados con éxito"));
              
            }
        } catch (Exception e) {
            hibernateSession.getTransaction().rollback();
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                    "Mensaje del sistema",
                                    "No se completo la acción, inténtelo más tarde,Probablemente se intento insertar un usuario que ya existe "));
            System.out.println("ExepcionAlumno : " + e);
        }
    }

}
