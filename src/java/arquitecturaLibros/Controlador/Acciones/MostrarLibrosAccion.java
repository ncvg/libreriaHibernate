package arquitecturaLibros.Controlador.Acciones;

import arquitecturaLibros.aplicacion.bo.Categoria;
import arquitecturaLibros.aplicacion.bo.Libro;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MostrarLibrosAccion extends Accion {
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse reponse){
        
        
        List<Libro> listaDeLibros = Libro.buscarTodos();
        List<Categoria> listaDeCategorias = Categoria.buscarTodas();
        request.setAttribute("listaDeLibros", listaDeLibros);
        request.setAttribute("listaDeCategorias", listaDeCategorias);
        
        return "mostrarLibros.jsp";
    }
    
}
