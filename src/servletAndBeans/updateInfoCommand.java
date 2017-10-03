package servletAndBeans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

public class updateInfoCommand implements Command {
	
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
		
		String id = request.getSession().getAttribute("id").toString();
		WallBean currentUser = getUser(id);
		request.setAttribute("user", currentUser);
		
		return "updateDetails.jsp";
	}

}
