package servletAndBeans;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

public class  SearchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String searchInput =  request.getParameter("searchInput").trim();
		
		List<Friends> results = FriendsDAO.search(searchInput);
		
		request.setAttribute("results", results);
		
		return "results.jsp";
	}

}
