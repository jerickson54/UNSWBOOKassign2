package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	public static List<friendsList> search(String userID){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		List<friendsList> returnList = new ArrayList<friendsList>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			//search both columns
			Query query = session.createQuery("FROM friendsList WHERE Friendid2 = :userID");
			query.setParameter("userID", userID);
			Query query2 = session.createQuery("FROM friendsList WHERE Friendid1 = :userID");
			query2.setParameter("userID", userID);
			
			List list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				friendsList fl = (friendsList) iterator.next();
				returnList.add(fl);
			}
			
			//loop through results of the second query
			List list2 = query2.list();
			for (Iterator iterator = list2.iterator(); iterator.hasNext();){
				friendsList fl2 = (friendsList) iterator.next();
				returnList.add(fl2);
			}
			
			tx.commit();
		}catch(HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return returnList;
	}

	public static void main(String args[]){

		/*
		friendsList l1 = new friendsList("z1111114","z1111112");
		friendsListDAO.saveOrUpdate(l1);
		friendsList l2 = new friendsList("z1111114","z1111113");
		friendsListDAO.saveOrUpdate(l2);
		friendsList l3 = new friendsList("z1111114","z1111115");
		friendsListDAO.saveOrUpdate(l3);
		friendsList l4 = new friendsList("z1111115","z1111117");
		friendsListDAO.saveOrUpdate(l4);
		friendsList l5 = new friendsList("z1111118","z1111115");
		friendsListDAO.saveOrUpdate(l5);
		*/
	}
		
}
