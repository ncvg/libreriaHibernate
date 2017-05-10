package arquitecturaLibros.Controlador.Acciones;

import arquitecturaLibros.aplicacion.bo.Categoria;
import arquitecturaLibros.aplicacion.bo.Libro;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FormularioEditarLibroAccion extends Accion {
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response){
        
        String isbn = request.getParameter("isbn");
        List<Categoria> listaDeCategorias = Categoria.buscarTodas();
        Libro libro = Libro.buscarPorClave(request.getParameter("isbn"));
        request.setAttribute("listaDeCategorias", listaDeCategorias);
        request.setAttribute("libro", libro);
        
        return "FormularioEditarLibro.jsp";
    }
}
