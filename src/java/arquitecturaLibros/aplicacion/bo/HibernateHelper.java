package arquitecturaLibros.aplicacion.bo;

import arquitecturaLibros.DataBaseException;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateHelper {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = null;
        try{
            //lee el archivo de configuracion de hibernate
            configuration.configure();
            //construye la session
            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }catch(HibernateException ex){
            System.err.println("Error creando una dfactoria de session. "+ex);
            throw new DataBaseException(ex);
        }
    }
    


public static SessionFactory getSessionFactory(){
    return sessionFactory;
}

}