package db;

import org.hibernate.Session;

import java.sql.Timestamp;

public class friendsListDAO {

	public static void saveOrUpdate(friendsList f){
		
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(f);
		session.getTransaction().commit();
		session.close();

		// activity logging
		String f1 = FriendsDAO.retrieve(f.getId1()).getName();
		String f2 = FriendsDAO.retrieve(f.getId2()).getName();
		String description = f1 + " requested to be friends with " + f2;
		activity a = new activity(f.getId1(), description, new Timestamp(System.currentTimeMillis()));
		activityDAO.saveOrUpdate(a);
	}
		
	public static friendsList retrieve(String id){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();

		friendsList f = session.get(friendsList.class, id);
		session.getTransaction().commit();
		session.close();

		return f;
	}

	public static void delete(friendsList f){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();

		session.remove(f);
		session.getTransaction().commit();
		session.close();
	}

	public static void main(String args[]){

		/*
		friendsList l1 = new friendsList("z1111111","z1111112");
		friendsListDAO.saveOrUpdate(l1);
		friendsList l2 = new friendsList("z1111111","z1111113");
		friendsListDAO.saveOrUpdate(l2);
		friendsList l3 = new friendsList("z1111112","z1111114");
		friendsListDAO.saveOrUpdate(l3);
		*/
	}
		
}
