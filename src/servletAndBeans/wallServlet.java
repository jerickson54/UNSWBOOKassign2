package servletAndBeans;

import db.Friends;
import db.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wallServlet")
public class wallServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public wallServlet() {
        super();
    }

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null) {
            String id = req.getParameter("id");
            wallBean wall = getUser(id);
            req.setAttribute("wall", wall);
        }

        RequestDispatcher rd = req.getRequestDispatcher("userWall.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
