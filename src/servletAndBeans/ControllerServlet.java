package servletAndBeans;

import db.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		commands.put("addFriend", new AddFriendCommand());
		commands.put("confirmFriendRequest", new ConfirmFriendCommand());
		commands.put("banUser", new BanCommand());
		commands.put("activityReport", new UserActivityCommand());
		commands.put("upload", new UploadCommand());
		commands.put("updateInfo", new updateInfoCommand());
		commands.put("updateDatabase", new updateDatabaseCommand());
		commands.put("extraction", new ExtractionCommand());
		commands.put("queryResults", new queryResultsCommand());

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
			if(!nextPage.equals("default.jsp")) {
				updateNotifications(request);
			}
			if (nextPage.equals("WallCommand") || nextPage.equals("Profile")) {
				//viewing friend's profile
				if(nextPage.equals("Profile")){
					request.getSession().setAttribute("viewFriendProfile", true);
				} else {
					request.getSession().setAttribute("friendid", null);
					request.getSession().setAttribute("viewFriendProfile", false);
				}
				request.getSession(true).setAttribute("falseLogin", falseLogin);
				WallCommand wc = new WallCommand();
                nextPage = wc.execute(request,response);
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

	public void updateNotifications(HttpServletRequest request) {
		if(request.getSession().getAttribute("hasLoggedIn") != null) {
			if((boolean) request.getSession().getAttribute("hasLoggedIn")) { // If user is logged in update notifications
				String loggedInUser = request.getSession().getAttribute("id").toString();
				List<String> notifications = new ArrayList<String>();
				List<friendsList> friendRequests = friendsListDAO.search(loggedInUser);
				List<likes> likes = likesDAO.getLikes();
				for(friendsList fR : friendRequests) {
					if(!fR.getHasSeen()) {
						notifications.add(FriendsDAO.retrieve(fR.getId1()).getName() + " has accepted your friend request");
						if(request.getParameter("hasSeen") != null) {
							fR.setHasSeen(true);
							friendsListDAO.saveOrUpdate(fR);
						}
					}
				}
				for(likes like : likes) {
					if(messagesDAO.retrieve(like.getMessageID()).getUserID().equals(loggedInUser)
							&& !like.getHasSeen()) {
						notifications.add(FriendsDAO.retrieve(like.getUserID()).getName() +
								" likes your message \"" + messagesDAO.retrieve(like.getMessageID()).getMessage()
								+ "\"");
						if(request.getParameter("hasSeen") != null) {
							like.setHasSeen(true);
							likesDAO.saveOrUpdate(like);
						}
					}
				}
				request.getSession().setAttribute("notifications", notifications);
			}
		}
	}
}