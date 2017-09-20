package servletAndBeans;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Friends;

public class RegisterCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		//Create or get the bean with all tokens stored
		NewUsersBean tokens;
		tokens = (NewUsersBean) request.getServletContext().getAttribute("newUsers");
		if (tokens == null) {
			request.getServletContext().setAttribute("newUsers", new NewUsersBean());
			tokens = (NewUsersBean) request.getServletContext().getAttribute("newUsers");	
		}
		
		//Generate random token
		Random rand = new Random();
		String token = String.valueOf(rand.nextInt(999999999));
		
		//add the friend to the tokens bean
		tokens.addUser(token, newFriend);
		
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
         		+ "<a href = \"http://localhost:8080/UNSWBOOKASSIGN2/controller?action=confirmNewUser&token="+ token + "\">Confirm Account</a>","text/html");

         //send message  
         Transport.send(message);    
         System.out.println("Message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
		
        return "/emailSend.jsp";
	}

}
