package servletAndBeans;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

public class AdvancedSearchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name").trim();
		String gender = request.getParameter("gender").trim();
		String date = request.getParameter("date").trim();
		String username = request.getParameter("username").trim();
		String id = request.getParameter("zid").trim();

		//Check that at least one of the fields contains a value
		if (!name.equals("") || !gender.equals("") || !date.equals("") || !username.equals("") || !id.equals("")) {
			List<Friends> results = FriendsDAO.searchAdvanced(name, gender, date, username, id);
			request.setAttribute("results", results);
		}
		return "results.jsp";
	}
}