
package arquitecturaLibros;

import java.sql.SQLException;
import java.util.List;


public class Libro {
    
    private String isbn;
    private String titulo;
    private String categoria;
    
    //contructores
    
    public Libro(){
        
    }
    
    public Libro(String isbn){
        this.isbn = isbn;
    }
    
    public Libro(String isbn, String titulo, String categoria){
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
    }
    
    public String getIsbn(){
        return this.isbn;
    }
    
    public void setIsbn(String newISBN){
        this.isbn = newISBN;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public void setTitulo(String newTitulo){
        this.titulo = newTitulo;
    }
    
    public String getCategoria(){
        return this.categoria;
    }
    
    public void setCategoria(String newCategoria){
        this.categoria = newCategoria;
    }
    
    
    
    
    public void insertar(){
        String consultaSQL = "INSERT INTO libros (isbn,titulo,categoria) VALUES ";
        consultaSQL += "('"+this.isbn+"','"+this.titulo+"','"+this.categoria+"')";
        DataBaseHelper helper = new DataBaseHelper();
        helper.modificarRegistro(consultaSQL);
    }
    public static List<Libro> buscarTodos(){
        String consultaSQL = "select isbn,titulo,categoria from libros";
        DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
        List<Libro> listaDeLibros = helper.seleccionarRegistros(consultaSQL, Libro.class);
        
        return listaDeLibros;
    }
    
    public static List<String> buscarTodasLasCategorias(){
        String consultaSQL = "select distinct(categoria) as categoria from libros";
        DataBaseHelper<String> helper = new DataBaseHelper<String>();
        List<String> listaDeCategorias = helper.seleccionarRegistros(consultaSQL, String.class);
        
        return listaDeCategorias;
        
        
    }
    
    public void borrar() throws DataBaseException{
        String consultaSQL = "delete from libros where isbn='"+this.isbn+"'";
        DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
        helper.modificarRegistro(consultaSQL);
    }
    
    public static Libro buscarPorClave(String isbn){
        String consultaSQL = "select isbn,titulo,categoria from libros where isbn='"+isbn+"'";
        DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
        List<Libro> listaDeLibros = helper.seleccionarRegistros(consultaSQL, Libro.class);
        return listaDeLibros.get(0);
    }
    
    public void salvar() throws DataBaseException{
        String consultaSQL = "update libros set titulo='"+this.titulo+"',categoria='"
                +categoria+"'where isbn='"+this.isbn+"'";
        DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
        helper.modificarRegistro(consultaSQL);
    }
    
    public static List<Libro> buscarPorCategoria (String categoria){
        String consultaSQL = "select isbn, titulo, categoria from libros where categoria='"+
                categoria+"'";
        DataBaseHelper<Libro> helper = new DataBaseHelper<Libro>();
        List<Libro> listaDeLibros = helper.seleccionarRegistros(consultaSQL, Libro.class);
        
        return listaDeLibros;
    }
        
}
