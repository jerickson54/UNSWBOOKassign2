package db;

import org.hibernate.Session;

public class likesDAO {
	
public static void saveOrUpdate(likes l){
		
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(l);
		session.getTransaction().commit();
		session.close();
		}
		
		public static likes retrieve(String id){
			Session session = HibernateUtil.SESSION_FACTORY.openSession();
			session.beginTransaction();
			
			likes l = session.get(likes.class, id);
			session.getTransaction().commit();
			session.close();
			
			return l;
		}
		
		public static void delete(likes l){
			Session session = HibernateUtil.SESSION_FACTORY.openSession();
			session.beginTransaction();
			
			session.remove(l);
			session.getTransaction().commit();
			session.close();
		}
		
		public static void main(String args[]){

			likes l = new likes(1,"z1111111",false);
			likesDAO.saveOrUpdate(l);

		}

}
