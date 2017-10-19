package db;

import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FriendsDAO {
	
	public static void saveOrUpdate(Friends friend){
		
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		session.beginTransaction();

		session.saveOrUpdate(friend);
		session.getTransaction().commit();
		session.close();

		// activity logging
		String description;
		
		if(friend.getBanned()) {
			description = friend.getName() + " has been banned";
		} else {
			description = friend.getName() + " has created an account";
			

		}
		activity a = new activity(friend.getId(), description, new Timestamp(System.currentTimeMillis()));
		activityDAO.saveOrUpdate(a);
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
	
	@SuppressWarnings("rawtypes")
	public static Friends loginUser(String username, String password){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		Friends result = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Friends WHERE username = :username AND password = :password");
			q.setParameter("username", username);
			q.setParameter("password", password);
			List list = q.list();
			for(Iterator iterator = list.iterator();iterator.hasNext();){
				Friends user = (Friends)iterator.next();
				result = user;
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null)tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static List<Friends> getEverything(){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		List<Friends> returnList = new ArrayList<Friends>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Friends");
		
			List list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				Friends user = (Friends) iterator.next();
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
	
	@SuppressWarnings("rawtypes")
	public static List<Friends> search(String name){
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		List<Friends> returnList = new ArrayList<Friends>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Friends WHERE name = :name");
			query.setParameter("name", name);
		
			List list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				Friends user = (Friends) iterator.next();
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
	
	@SuppressWarnings("rawtypes")
	public static List<Friends> searchAdvanced(String name, String gender, String date, String username, String id){
		//Build the query string depending on what parameters left blank
		StringBuilder sb = new StringBuilder();
		List<String> queryParts = new ArrayList<String>();
		sb.append("FROM Friends WHERE");
		if (!name.equals("")) queryParts.add("name = :name");	
		if (!gender.equals("")) queryParts.add("gender = :gender");
		if(!date.equals("")) queryParts.add("dob = :dob");
		if (!username.equals("")) queryParts.add("username = :username");
		if (!id.equals("")) queryParts.add("id = :id");
		
		for (int i = 0; i < queryParts.size(); i++) {
			sb.append(" " + queryParts.get(i));
			if (i+1 != queryParts.size()) {
				sb.append(" and");
			}
		}
		
		Session session = HibernateUtil.SESSION_FACTORY.openSession();
		List<Friends> returnList = new ArrayList<Friends>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(sb.toString());
			//add parameters depending on what parameters left blank
			if (! name.equals("")) query.setParameter("name", name);
			if (! gender.equals("")) query.setParameter("gender", gender);
			if (! date.equals("")) query.setParameter("dob", date);
			if (! username.equals("")) query.setParameter("username", username);
			if (! id.equals("")) query.setParameter("id", id);
		
			List list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				Friends user = (Friends) iterator.next();
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
	
	//already populated successfully. Note might want to store all this info in xml file. IDK
	//To view go to database in pgAdmin III > UNSWBOOK>Schema>tables>friends>view data button
	//success
	
	public static void main(String[] args){

		/*
		System.out.println("gets into friend main");
		Friends f1 = new Friends("David Chadwick","jerickson5454@gmail.com","10/10/1994",22,"Male","dChaddy","coolDude123","z1111111");
		FriendsDAO.saveOrUpdate(f1);
		Friends f2 = new Friends("Seth Myers","jerickson5454@gmail.com","09/1/1996",21,"Male","zubbyPenguin","coolDude124","z1111112");
		FriendsDAO.saveOrUpdate(f2);
		Friends f3 = new Friends("Lucas Elder","jerickson5454@gmail.com","05/2/1997",20,"Male","STARLORD","coolDude125","z1111113");
		FriendsDAO.saveOrUpdate(f3);
		Friends f4 = new Friends("Josh Edwards","jerickson5454@gmail.com","04/12/1995",22,"Male","goPats","coolDude126","z1111114");
		FriendsDAO.saveOrUpdate(f4);
		
		Friends admin = new Friends("admin","jerickson5454@gmail.com","01/01/1905",112,"Male","admin","password","7777777");
		FriendsDAO.saveOrUpdate(admin);
		*/
	}
	
	
	
	
	

}
