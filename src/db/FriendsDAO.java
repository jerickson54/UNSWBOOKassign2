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
		
		/*
		Friends f1 = new Friends("David Chadwick","coolDude@gmail.com","10/10/1994",22,"Male","dChaddy","coolDude123","z1111111");
		FriendsDAO.saveOrUpdate(f1);
		Friends f2 = new Friends("Seth Myers","zubbyPenguin@gmail.com","09/1/1996",21,"Male","zubbyPenguin","coolDude124","z1111112");
		FriendsDAO.saveOrUpdate(f2);
		Friends f3 = new Friends("Lucas Elder","elderEnterprise@gmail.com","05/2/1997",20,"Male","STARLORD","coolDude125","z1111113");
		FriendsDAO.saveOrUpdate(f3);
		Friends f4 = new Friends("Josh Edwards","patriotsForever@gmail.com","04/12/1995",22,"Male","goPats","coolDude126","z1111114");
		FriendsDAO.saveOrUpdate(f4);
		*/
		
		
		
		
		
		
	}
	
	
	

}
