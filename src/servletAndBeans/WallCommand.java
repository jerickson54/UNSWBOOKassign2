package servletAndBeans;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.FriendsDAO;

import db.Friends;
import db.messages;
import db.messagesDAO;

public class WallCommand implements Command {

    public WallBean getUser(String id) {
        WallBean wallUser = new WallBean();
        Friends user = FriendsDAO.retrieve(id);
        wallUser.setUserID(user.getId());
        wallUser.setName(user.getName());
        wallUser.setDob(user.getDob());
        wallUser.setEmail(user.getEmailAddress());
        return wallUser;
    }
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        if(request.getSession().getAttribute("id") != null) {
            String id = request.getSession().getAttribute("id").toString();
            WallBean wall = getUser(id);
            request.setAttribute("wall", wall);

            if(request.getParameter("message") != null) {
                System.out.println(request.getParameter("message"));
                messages m1 = new messages(request.getSession().getAttribute("id").toString(),request.getParameter("message"));
                messagesDAO.saveOrUpdate(m1);
            }

            List<messages> messages = messagesDAO.search(id);
            Collections.reverse(messages);
            request.setAttribute("messages", messages);
        }
        return "userWall.jsp";
	}
}
