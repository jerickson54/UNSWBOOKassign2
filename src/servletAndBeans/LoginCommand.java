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
        request.setAttribute("user", user);
        request.setAttribute("id", user.getId());
        return "WallCommand";
    }

}
