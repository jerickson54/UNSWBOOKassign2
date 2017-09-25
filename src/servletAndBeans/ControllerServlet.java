package servletAndBeans;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> commands;
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		commands = new HashMap<String, Command>();
		commands.put("register", new RegisterCommand());
		commands.put("confirmNewUser", new ConfirmNewUserCommand());
		commands.put("search", new SearchCommand());
		commands.put("advancedSearch", new AdvancedSearchCommand());
		commands.put("profile", new ProfileCommand());
		commands.put("wall", new WallCommand());	
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		//commands.put("addFriend", new AddFriendCommand());
		
	}
	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void resolveCommand(
		HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {


		// TODO: find the command that was requested by 
		// the client and then call the execute method
		Command cmd = (Command) commands.get(request.getParameter("action"));
		if (cmd == null) {
			RequestDispatcher rd = request.getRequestDispatcher("default.jsp");
			rd.forward(request, response);
		}else {
			String nextPage = cmd.execute(request, response);
			String falseLogin = "false";
			if (nextPage.equals("WallCommand") || nextPage.equals("Profile")) {
				//viewing friend's profile
				if(nextPage.equals("Profile")){
					request.getSession().setAttribute("viewFriendProfile", true);
				} else {
					request.getSession().setAttribute("viewFriendProfile", false);
				}
				request.getSession(true).setAttribute("falseLogin", falseLogin);
				WallCommand wc = new WallCommand();
                nextPage = wc.execute(request,response);
				request.getSession().setAttribute("friendid", null);
			} else if (nextPage.equals("falseLogin")) {
				nextPage = "default.jsp";
				falseLogin = "true";
				request.getSession(true).setAttribute("falseLogin", falseLogin);
			} else if (nextPage.equals("userBanned")) {
				nextPage = "default.jsp";
				request.getSession().setAttribute("userBanned", true);
			}
            RequestDispatcher rd = request.getRequestDispatcher(nextPage);
            rd.forward(request, response);
		}
	}

	/** 
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    resolveCommand(request, response);
	}
	
	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    resolveCommand(request, response);
	}
	
	/** Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "Controller for the application";
	}	
}