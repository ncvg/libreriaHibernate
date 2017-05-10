package arquitecturaLibros.Controlador.Acciones;

import arquitecturaLibros.aplicacion.bo.Categoria;
import arquitecturaLibros.aplicacion.bo.Libro;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FormularioInsertarLibroAccion extends Accion {
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response){
        
        List<Categoria> listaDeCategorias = null;
        listaDeCategorias = Categoria.buscarTodas();
        request.setAttribute("listaDeCategorias", listaDeCategorias);
        
        return "FormularioInsertarLibro.jsp";
    }
    
}
