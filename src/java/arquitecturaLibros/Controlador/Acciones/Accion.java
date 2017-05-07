
package arquitecturaLibros.Controlador.Acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class Accion {
    
    public abstract String ejecutar(HttpServletRequest resquest, HttpServletResponse response);
        
    public static Accion getAccion(String tipo){
        Accion accion = null;
        Package pkg = Accion.class.getPackage();
        
        try{
            accion = (Accion)Class.forName(pkg.getName()+"."+tipo+"Accion").newInstance();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return accion;
    }
    
}
