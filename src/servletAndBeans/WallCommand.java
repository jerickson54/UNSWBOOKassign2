package servletAndBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;

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

        if(request.getSession().getAttribute("id") != null || request.getSession().getAttribute("friendid") != null) {
            String id;
            if (request.getSession().getAttribute("friendid") != null) {
                id = request.getSession().getAttribute("friendid").toString();
            } else {
                id = request.getSession().getAttribute("id").toString();
            }
            WallBean wall = getUser(id);
            request.setAttribute("wall", wall);

            if(request.getParameter("message") != null) {
                System.out.println(request.getParameter("message"));
                messages m1 = new messages(request.getSession().getAttribute("id").toString(),request.getParameter("message"));
                messagesDAO.saveOrUpdate(m1);
            }

            if(request.getParameter("messageLiked") != null) {
                likes l = new likes(Integer.parseInt(request.getParameter("messageLiked")), request.getSession().getAttribute("id").toString(),false);
                likesDAO.saveOrUpdate(l);
            }

            List<messages> messagesList = messagesDAO.search(id);
            Collections.reverse(messagesList);
            List<Integer> likes = new ArrayList<Integer>();
            for (messages m: messagesList) {
                likes.add(likesDAO.searchByMessageID(m.getId()).size());
            }
            request.setAttribute("messages", messagesList);
            request.setAttribute("likes", likes);
        }
        return "userWall.jsp";
	}
}
