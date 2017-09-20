package servletAndBeans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

public class ConfirmNewUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get hashMap
		NewUsersBean tokens;
		tokens = (NewUsersBean) request.getServletContext().getAttribute("newUsers");
		if (tokens == null) {
			request.getServletContext().setAttribute("newUsers", new NewUsersBean());
			tokens = (NewUsersBean) request.getServletContext().getAttribute("newUsers");	
		}
		
		//get token from link
		String token = (String) request.getParameter("token");
		
		Friends toCreate = tokens.getUser(token);
		
		if (toCreate != null) {
			System.out.println(toCreate.getName());
			FriendsDAO.saveOrUpdate(toCreate);
			return "/emailConfirmed.jsp";
		}
		
		return null;
	}
}