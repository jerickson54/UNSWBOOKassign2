package servletAndBeans;

import db.activity;
import db.activityDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserActivityCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String zId = request.getSession().getAttribute("friendid").toString();
        List <activity> activities = activityDAO.search(zId);
        System.out.println("===============================ACTIVITIES======================");
        System.out.println("ID IS :" + zId);

        for(activity act: activities) {
            System.out.println("activities id" + act.getUserID() + " at time" + act.getTime() + " message " + act.getDescription());

        }

        request.setAttribute("activities", activities);
        return "activityReport.jsp";
    }
}
