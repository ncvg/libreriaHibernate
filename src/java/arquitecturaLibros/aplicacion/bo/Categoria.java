package arquitecturaLibros.aplicacion.bo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Entity  //indentifica la clase a persistir
@Table(name="categorias") //indica con que tabla esta relacionada
public class Categoria {
    
    @Id
    private String id;
    private String descripcion;
    @OneToMany
    @JoinColumn(name="categoria")
    private List<Libro> listaDeLibros;
    
    @Override
    public int hashCode(){
        return id.hashCode();
    }
    
    @Override
    public boolean equals(Object o){
        String categoriaId = ((Categoria)o).getId();
        return id.equals(categoriaId);
    }
    
    public Categoria(){
        
    }
    
    public Categoria(String id){
        this.id = id;
    }
    
    public Categoria(String id, String descripcion){
        this.id = id;
        this.descripcion = descripcion;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public List<Libro> getListaDeLibros(){
        return this.listaDeLibros;
    }
    
    public void setListaDeLibros(List<Libro> listaDeLibros){
        this.listaDeLibros = listaDeLibros;
    }

    public static List<Categoria> buscarTodas(){
        SessionFactory factoriaSession = HibernateHelper.getSessionFactory();
        Session session = factoriaSession.openSession();
        
        List<Categoria> listaDeCategorias = session.createQuery("from Categoria categoria").list();
        session.close();
        return listaDeCategorias;
    }
}
