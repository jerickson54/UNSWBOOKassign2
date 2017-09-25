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
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Friends user = FriendsDAO.loginUser(userName,password);
        String returnString;
        if(user == null){
            returnString = "falseLogin";
        } else {
            returnString = "WallCommand";
            request.setAttribute("user", user);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("hasLoggedIn", true);
        }
        return returnString;
    }

}
