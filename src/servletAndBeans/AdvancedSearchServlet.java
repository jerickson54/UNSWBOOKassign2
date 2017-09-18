package servletAndBeans;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

/**
 * Servlet implementation class AdvancedSearchServlet
 */
@WebServlet("/AdvancedSearch")
public class AdvancedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
