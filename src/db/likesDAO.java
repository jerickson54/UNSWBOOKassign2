package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class likesDAO {
	
	public static void saveOrUpdate(likes l){

		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();

		session.saveOrUpdate(l);
		session.getTransaction().commit();
		session.close();

		// activity logging
		String liker = FriendsDAO.retrieve(l.getUserID()).getName();
		messages m = messagesDAO.retrieve(l.getMessageID());
		String owner = FriendsDAO.retrieve(m.getUserID()).getName();
		String description = liker + " likes " + owner + "'s message: " + m.getMessage();
		activity a = new activity(l.getUserID(), description, new Timestamp(System.currentTimeMillis()));
		activityDAO.saveOrUpdate(a);
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

public static List<likes> search(int mID){
	Session session = HibernateUtil.SESSION_FACTORY.openSession();
	List<likes> returnList = new ArrayList<likes>();
	Transaction tx = null;
	try {
		tx = session.beginTransaction();
		Query query = session.createQuery("FROM likes WHERE messageID = :mID");
		query.setParameter("mID", mID);

		List list = query.list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();){
			likes like = (likes) iterator.next();
			returnList.add(like);
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

		likes l = new likes(1,"z1111111",false);
		likesDAO.saveOrUpdate(l);

	}

}
