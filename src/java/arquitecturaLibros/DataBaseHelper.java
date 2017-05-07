package arquitecturaLibros;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class DataBaseHelper<T> {
    
    private static final Logger log = Logger.getLogger(DataBaseHelper.class.getPackage().getName());
    
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "libreria";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://"+ HOST +":"+PORT+"/"+DATABASE;
    
    public int modificarRegistro(String consultaSQL){
        Connection conexion = null;
        Statement sentencia = null;
        int filas = 0;
        
        try{
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            sentencia = conexion.createStatement();
            filas = sentencia.executeUpdate(consultaSQL);
        }catch(ClassNotFoundException e){
            log.error("Driver no encontrado "+e.getMessage());
            throw new DataBaseException("Error de SQL",e);
        }catch(SQLException e){
            log.error("ERROR SQL "+e.getMessage());
            throw new DataBaseException("Error SQL");
        }finally{
            if(sentencia!=null){
                try{
                    sentencia.close();
                }catch(SQLException e){
                    log.error("Error con la sentencia"+e.getMessage());
                }
            }
            if(conexion!=null){
                try{
                    conexion.close();
                }catch(SQLException e){
                    log.error("Error cerrando la conexion "+e.getMessage());
                }
            }
        }
        return filas;
    }
    
    public List<T> seleccionarRegistros(String consultaSQL, Class clase){
        
        Connection conexion = null;
        Statement sentencia = null;
        ResultSet filas = null;
        List<T> listaDeObjetos = new ArrayList<T>();
        
        try{
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            sentencia = conexion.createStatement();
            filas = sentencia.executeQuery(consultaSQL);
            while(filas.next()){
                T objeto= (T) Class.forName(clase.getName()).newInstance();
                Method[] metodos = objeto.getClass().getDeclaredMethods();
                for (Method metodo : metodos) {
                    if (metodo.getName().startsWith("set")) {
                        String columna = metodo.getName().substring(3).toLowerCase();
                        metodo.invoke(objeto, filas.getString(columna));
                    }
                    if(objeto.getClass().getName().equals("java.lang.String")){
                        objeto = (T) filas.getString(1);
                    }
                }
                listaDeObjetos.add(objeto);
            }
        }catch(Exception e){
            log.error("Error al seleccionar registros " + e.getMessage());
            throw new DataBaseException("Error de SQL",e);
        }finally{
            if(sentencia!=null){
                try{
                    sentencia.close();
                }catch(SQLException e){
                    log.error("Error de SQL "+e.getMessage());
                    throw new DataBaseException("Error de SQL "+e);
                }
            }
            if(conexion!=null){
                try{
                    conexion.close();
                }catch(SQLException e){
                    log.error("Error al cerrar la conexion "+e.getMessage());
                    throw new DataBaseException("Error al cerrar la conexion",e);
                }
            }
            return listaDeObjetos;
        }
        
        
    }
    
}