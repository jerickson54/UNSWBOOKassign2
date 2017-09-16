package db;

import org.hibernate.Session;

public class messagesDAO {
	
	public static void saveOrUpdate(messages m){
		
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(m);
		session.getTransaction().commit();
		session.close();
		}
		
		public static messages retrieve(String id){
			Session session = HibernateUtil.SESSION_FACTORY.openSession();
			session.beginTransaction();
			
			messages m = session.get(messages.class, id);
			session.getTransaction().commit();
			session.close();
			
			return m;
		}
		
		public static void delete(messages m){
			Session session = HibernateUtil.SESSION_FACTORY.openSession();
			session.beginTransaction();
			
			session.remove(m);
			session.getTransaction().commit();
			session.close();
		}
		
		public static void main(String args[]){
			/*
			messages m1 = new messages("z1111111","Secret tunnel. Secret tunnel. Through the mountains. Secret Secret secret tunnel. YEAHHHHH");
			messagesDAO.saveOrUpdate(m1);
			*/
			
		}
		
	

}
