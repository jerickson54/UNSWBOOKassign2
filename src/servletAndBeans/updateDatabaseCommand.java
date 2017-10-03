package servletAndBeans;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;
import db.FriendsDAO;

public class updateDatabaseCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getSession().getAttribute("id").toString();
		LocalDateTime now = LocalDateTime.now();
		int age;
		
		String name = request.getParameter("name");
		String emailAddress = request.getParameter("emailAddress");
		String gender = request.getParameter("gender");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		if(Integer.parseInt(month) < 10){
			String temp = month;
			month = "0";
			month+=temp;
		}
		
		if(Integer.parseInt(day) < 10){
			String temp = day;
			day = "0";
			day+=temp;
		}
		
		String dob = year + "-" + month + "-" + day;
		
		
		
		String username = FriendsDAO.retrieve(id).getUsername();
		String password = request.getParameter("password");
		
		
		
		
		//calculate age based upon user birthday accordingly
		if(now.getMonthValue() == Integer.parseInt(month) && now.getDayOfMonth() < Integer.parseInt(day))
			age = now.getYear()-Integer.parseInt(year) - 1;
		
		else if(now.getMonthValue() < Integer.parseInt(month))
			age = now.getYear()-Integer.parseInt(year) - 1;
			
		else
			age = now.getYear()-Integer.parseInt(year);
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(emailAddress);
		System.out.println(dob);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(username);
		System.out.println(password);
		
		Friends updatedFriend = new Friends(name,emailAddress,dob,age,gender,username,password,id);
		FriendsDAO.saveOrUpdate(updatedFriend);
		
		
		return "default.jsp";
	}

}
