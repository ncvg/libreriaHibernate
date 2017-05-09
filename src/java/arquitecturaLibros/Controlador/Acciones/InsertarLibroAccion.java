/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturaLibros.Controlador.Acciones;

import arquitecturaLibros.aplicacion.bo.Libro;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author esquilax
 */
public class InsertarLibroAccion extends Accion{
    
    public String ejecutar(HttpServletRequest request, HttpServletResponse response){
        
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        
        Libro libro = new Libro(isbn,titulo,categoria);
        libro.insertar();
        
        return "MostrarLibros.do";
    }
    
}
