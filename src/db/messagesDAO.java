package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class messagesDAO {
	
	public static void saveOrUpdate(messages m){
		
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(m);
		session.getTransaction().commit();
		session.close();

		// activity logging
		String user = FriendsDAO.retrieve(m.getUserID()).getName();
		String description = user + " posted the message: " + m.getMessage();
		System.out.println(description);
		activity a = new activity(m.getUserID(), description, new Timestamp(System.currentTimeMillis()));
		activityDAO.saveOrUpdate(a);
	}
		
	public static messages retrieve(int id){
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

public static List<messages> search(String userID){
	Session session = HibernateUtil.SESSION_FACTORY.openSession();
	List<messages> returnList = new ArrayList<messages>();
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		Query query = session.createQuery("FROM messages WHERE userID = :userid");
		query.setParameter("userid", userID);

		List list = query.list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();){
			messages user = (messages) iterator.next();
			returnList.add(user);
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
		messages m1 = new messages("z1111111","Secret tunnel. Secret tunnel. Through the mountains. Secret Secret secret tunnel. YEAHHHHH");
		messagesDAO.saveOrUpdate(m1);
		*/

	}
}
