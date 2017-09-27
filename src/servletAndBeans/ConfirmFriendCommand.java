package servletAndBeans;

import db.Friends;
import db.FriendsDAO;
import db.friendsList;
import db.friendsListDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmFriendCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getSession().getAttribute("id").toString();
        String friendId = request.getParameter("friendid");

        // Adding the friend list to the database
        friendsList l = new friendsList(userId,friendId);
        friendsListDAO.saveOrUpdate(l);

        // Logging in as the new user
        Friends user = FriendsDAO.retrieve(userId);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("id", userId);
        request.getSession().setAttribute("hasLoggedIn", true);
        return "Profile";
    }
}
