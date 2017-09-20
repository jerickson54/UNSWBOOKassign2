package servletAndBeans;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import db.Friends;
import db.HibernateUtil;

public class WallCommand implements Command {

    public wallBean getUser(String id) {
        wallBean wallUser = new wallBean();
        Friends user = null;
        Session session = HibernateUtil.SESSION_FACTORY.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Friends WHERE id = :id");
            query.setParameter("id", id);
            user = (Friends) query.list().get(0);
        }
        catch(HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        wallUser.setUserID(user.getId());
        wallUser.setName(user.getName());

        return wallUser;
    }
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        if(request.getParameter("id") != null) {
            String id = request.getParameter("id");
            wallBean wall = getUser(id);
            request.setAttribute("wall", wall);
        }
        return "userWall.jsp";
	}
}
