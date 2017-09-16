package servletAndBeans;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

/**
 * Servlet implementation class newUser
 */
@WebServlet("/newUser")
public class newUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		LocalDateTime now = LocalDateTime.now();
		int age;
		
		String name = request.getParameter("name");
		String emailAddress = request.getParameter("emailAddress");
		String gender = request.getParameter("gender");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String dob = "0"+month+"/"+day+"/"+year;
		
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String zid = request.getParameter("zid");
		
		//calculate age based upon user birthday accordingly
		if(now.getMonthValue() == Integer.parseInt(month) && now.getDayOfMonth() < Integer.parseInt(day))
			age = now.getYear()-Integer.parseInt(year) - 1;
		
		else if(now.getMonthValue() < Integer.parseInt(month))
			age = now.getYear()-Integer.parseInt(year) - 1;
			
		else
			age = now.getYear()-Integer.parseInt(year);
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
