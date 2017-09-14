package db;

import org.hibernate.Session;

public class FriendsDAO {
	
	public static void saveOrUpdate(Friends friend){
		
	Session session = HibernateUtil.SESSION_FACTORY.openSession();
	session.beginTransaction();
	
	session.saveOrUpdate(friend);
	session.getTransaction().commit();
	session.close();
	}
	
	public static Friends retrieve(String id){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();
		
		Friends f = session.get(Friends.class, id);
		session.getTransaction().commit();
		session.close();
		
		return f;
	}
	
	public static void delete(Friends f){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();
		
		session.remove(f);
		session.getTransaction().commit();
		session.close();
	}
	
	
	//already populated successfully. Note might want to store all this info in xml file. IDK
	//To view go to database in pgAdmin III > UNSWBOOK>Schema>tables>friends>view data button
	//success
	
	
	public static void main(String[] args){
		Friends f1 = new Friends("David Chadwick", "123456", "Male",23);
		FriendsDAO.saveOrUpdate(f1);
		Friends f2 = new Friends("Josh Edwards", "123457", "Male",22);
		FriendsDAO.saveOrUpdate(f2);
		Friends f3 = new Friends("Seth Myers", "123458", "Male",21);
		FriendsDAO.saveOrUpdate(f3);
		Friends f4 = new Friends("Lucas Elder", "123459", "Male",20);
		FriendsDAO.saveOrUpdate(f4);
		
	}
	
	
	

}
