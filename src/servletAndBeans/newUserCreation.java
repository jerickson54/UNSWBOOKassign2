package servletAndBeans;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.FriendsDAO;
import db.Friends;

/**
 * Servlet implementation class newUserCreation
 */
@WebServlet("/newUserCreation")
public class newUserCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newUserCreation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get hashMap
		NewUsersBean tokens;
		tokens = (NewUsersBean) getServletContext().getAttribute("newUsers");
		if (tokens == null) {
			getServletContext().setAttribute("newUsers", new NewUsersBean());
			tokens = (NewUsersBean) getServletContext().getAttribute("newUsers");	
		}
		
		//get token from link
		String token = (String) request.getParameter("token");
		
		Friends toCreate = tokens.getUser(token);
		
		if (toCreate != null) {
			System.out.println(toCreate.getName());
			FriendsDAO.saveOrUpdate(toCreate);
			request.getRequestDispatcher("/default.jsp").forward(request,response);
		}
		request.getRequestDispatcher("/default.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
