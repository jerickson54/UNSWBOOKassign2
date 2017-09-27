package servletAndBeans;

import db.Friends;
import db.FriendsDAO;
import db.friendsList;
import db.friendsListDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class AddFriendCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getSession().getAttribute("id").toString();
        String friendId = request.getParameter("friendid");
        Friends friend = FriendsDAO.retrieve(friendId);
        Friends user = FriendsDAO.retrieve(userId);

        /*
        friendsList l = new friendsList(userId,friendId);
        friendsListDAO.saveOrUpdate(l);
        */

        String from = "unswbooksocialmedia@gmail.com";
        String passwordFrom = "IHatePasswords";
        String to = friend.getEmailAddress();
        String sub = "UNSWBook Friend Request";

        //build the message based upon user input
        String msg = "Greetings " + friend.getName() + ",\n" + user.getName() +" has sent you a friend request";


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
            message.setContent("<img src = \"WebContent/img/UNSW_logo.jpg\"> <h1> Message from UNSW Book </h1><p>" + msg+  "</p>"
                   + "<a href = \"http://localhost:8080/UNSWBOOKASSIGN2/controller?action=confirmFriendRequest&id="+ friendId + "&friendid="+userId+"\">Confirm Friend Request</a>","text/html");

            //send message
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

        return "friendRequestSent.jsp";

    }
}
