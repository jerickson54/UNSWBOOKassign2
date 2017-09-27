package servletAndBeans;

import db.Friends;
import db.FriendsDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isOwnProfile = request.getParameter("isOwnProfile");
        Friends user;
        if (isOwnProfile != null) {
            String userId = request.getSession().getAttribute("id").toString();
            user = FriendsDAO.retrieve(userId);
        } else {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            user = FriendsDAO.loginUser(userName, password);
        }
        String returnString;
        if(user == null){
            returnString = "falseLogin";
        } else if (user.getBanned() != null && user.getBanned()){
            returnString = "userBanned";
        } else {
            returnString = "WallCommand";
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("hasLoggedIn", true);
        }
        return returnString;
    }

}
