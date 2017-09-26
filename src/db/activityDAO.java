package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class activityDAO {

    public static void saveOrUpdate(activity activity){
        Session session = HibernateUtil.SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.saveOrUpdate(activity);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(activity activity){
        Session session = HibernateUtil.SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.remove(activity);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("rawtypes")
    public static List<activity> search(String userID){
        Session session = HibernateUtil.SESSION_FACTORY.openSession();
        List<activity> returnList = new ArrayList<activity>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM activity WHERE userID = :userID");
            query.setParameter("userID", userID);

            List list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();){
                activity a = (activity) iterator.next();
                returnList.add(a);
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

    public static void main(String[] args){
        activity a = new activity("1", "test", new Timestamp(System.currentTimeMillis()));
        activityDAO.saveOrUpdate(a);
    }
}
