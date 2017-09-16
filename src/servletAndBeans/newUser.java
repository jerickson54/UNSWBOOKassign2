package servletAndBeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import db.Friends;


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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		Friends newFriend = new Friends(name,emailAddress,dob,age,gender,username,password,zid);
		Friends.setNewUser(newFriend);
		
		
		//try to send an email
		String from = "unswbooksocialmedia@gmail.com";
		String passwordFrom = "IHatePasswords";
		String to = emailAddress;
		String sub = "Welcome to UNSWBOOK";
		
		//build the message based upon user input
		String msg = "Greetings " + name + ". We would like to welcome you to UNSWBOOK. Your username is: " + username +". Please click the following link below to "
				+ "confirm your account.";
		
		
		 //Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,passwordFrom);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);
         message.setContent("<img src = \"WebContent/img/UNSW_logo.jpg\"> <h1> Welcome to UNSWBOOK </h1><p>" + msg+  "</p> "
         		+ "<a href = \"http://localhost:8080/UNSWBOOKASSIGN2/newUserCreation\">Confirm Account</a>","text/html");
         
         
         //send message  
         Transport.send(message);    
         System.out.println("Message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
		
        request.getRequestDispatcher("/emailSend.jsp").forward(request,response);
		
		
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
