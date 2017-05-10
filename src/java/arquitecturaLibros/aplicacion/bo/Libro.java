
package arquitecturaLibros.aplicacion.bo;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


@Entity
@Table(name="libros")
public class Libro {
    
    @Id
    private String isbn;
    private String titulo;
    @ManyToOne
    @JoinColumn(name="categoria")
    private Categoria categoria;
    
    @Override
    public int hashCode(){
        return isbn.hashCode();
    }
    
    @Override
    public boolean equals(Object o){
        String isbnLibro = ((Libro)o).getIsbn();
        return isbnLibro.equals(isbn);
    }
    
    //contructores
    
    public Libro(){
        super();
    }
    
    public Libro(String isbn){
        super();
        this.isbn = isbn;
    }
    
    public Libro(String isbn, String titulo, Categoria categoria){
        super();
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
    
    public Categoria getCategoria(){
        return this.categoria;
    }
    
    public void setCategoria(Categoria newCategoria){
        this.categoria = newCategoria;
    }
    
    @SuppressWarnings("unchecked")
    public static List<Categoria> buscarTodasLasCategorias(){
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        String consulta = "SELECT DISTINCT Categoria.id FROM Categoria categoria";
        
        List<Categoria> listaDeCategorias = session.createQuery(consulta).list();
        
        session.close();
        return listaDeCategorias;
    }
    
    public void insertar(){
        
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();
    }
    
    public void borrar(){
        
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        session.beginTransaction();
        session.delete(this);
        session.getTransaction().commit();
    }
    
    public void salvar(){
        
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        session.beginTransaction();
        session.update(this);
        session.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
    public static List<Libro> buscarTodos(){
        
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        List<Libro> listaDeLibros = session.createQuery("from Libro libro right join fetch"
                + " libro.categoria").list();
        session.close();
        return listaDeLibros;
    }
    
    public static Libro buscarPorClave(String isbn){
        
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        Libro libro = (Libro) session.get(Libro.class, isbn);
        session.close();
        return libro;
    }
    
    @SuppressWarnings("unchecked")
    public static List<Libro> buscarPorCategoria(String categoria){
        
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        Query consulta = session.createQuery("from Libro libro where libro.categoria=:categoria");
        consulta.setString("categoria",categoria);
        List<Libro> listaDeLibros = consulta.list();
        session.close();
        return listaDeLibros;
    }
    
    /*
    ESTE ES EL VIEJO CODIGO DE LIBRO EL CUAL USA UNA CLASE LLAMADA DATABASEHELPER
    
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
        */
}
