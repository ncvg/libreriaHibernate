
package arquitecturaLibros.Controlador;

import arquitecturaLibros.Controlador.Acciones.Accion;
import arquitecturaLibros.Controlador.Acciones.BorrarLibroAccion;
import arquitecturaLibros.Controlador.Acciones.FiltrarAccion;
import arquitecturaLibros.Controlador.Acciones.FormularioEditarLibroAccion;
import arquitecturaLibros.Controlador.Acciones.FormularioInsertarLibroAccion;
import arquitecturaLibros.Controlador.Acciones.InsertarLibroAccion;
import arquitecturaLibros.Controlador.Acciones.MostrarLibrosAccion;
import arquitecturaLibros.Controlador.Acciones.SalvarLibrosAccion;
import arquitecturaLibros.Libro;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControladorLibros extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException{
        
        RequestDispatcher despachador = null;
        Accion accion = null;
        String url = request.getServletPath();
        
        accion = Accion.getAccion(url.substring(1,url.length()-3));
        
        
        despachador = request.getRequestDispatcher(accion.ejecutar(request, response));
        
        despachador.forward(request, response);
        
    }
    
}