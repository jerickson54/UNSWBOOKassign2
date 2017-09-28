package servletAndBeans;

import db.Friends;
import db.FriendsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BanCommand implements Command{


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String zId = request.getSession().getAttribute("friendid").toString();
        Friends f = FriendsDAO.retrieve(zId);
        f.setBanned(true);
        FriendsDAO.saveOrUpdate(f);
        return "bannedConfirmed.jsp";
    }
}
