
package arquitecturaLibros.Controlador.Acciones;

import arquitecturaLibros.aplicacion.bo.Categoria;
import arquitecturaLibros.aplicacion.bo.Libro;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FiltrarAccion extends Accion{
    
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response){
        
        List<Libro> listaDeLibros = null;
        List<Categoria> listaDeCategorias = Categoria.buscarTodas();
            
        if(request.getParameter("categoria")==null||
                request.getParameter("categoria").equals("seleccionar")){
            listaDeLibros = Libro.buscarTodos();
        }else{
            listaDeLibros = Libro.buscarPorCategoria(request.getParameter("categoria"));
        }
        request.setAttribute("listaDeLibros",listaDeLibros);
        request.setAttribute("listaDeCategorias",listaDeCategorias);
        
        return "mostrarLibros.jsp";
        
    }
    
}
