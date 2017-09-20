package servletAndBeans;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

public class ProfileCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String zId = request.getParameter("id");
		Friends user = FriendsDAO.retrieve(zId);
		request.setAttribute("user", user);
		return "profile.jsp";
	}

}
