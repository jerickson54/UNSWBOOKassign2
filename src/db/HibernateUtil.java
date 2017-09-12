package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static final SessionFactory SESSION_FACTORY = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory(){
		
		try{
			return new Configuration().configure("/config/hibernate.cfg.xml").buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Initial SessionFactory creation failed" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static void shutdown(){
		if(SESSION_FACTORY != null)
			SESSION_FACTORY.close();
	}
}
