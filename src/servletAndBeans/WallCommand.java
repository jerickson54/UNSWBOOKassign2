package servletAndBeans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.FriendsDAO;

import db.Friends;

public class WallCommand implements Command {

    public WallBean getUser(String id) {
        WallBean wallUser = new WallBean();
        Friends user = FriendsDAO.retrieve(id);
        wallUser.setUserID(user.getId());
        wallUser.setName(user.getName());
        return wallUser;
    }
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        if(request.getAttribute("id") != null) {
            String id = request.getAttribute("id").toString();
            WallBean wall = getUser(id);
            request.setAttribute("wall", wall);
        }
        return "userWall.jsp";
	}
}
