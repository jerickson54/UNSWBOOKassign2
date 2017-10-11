package servletAndBeans;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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

import db.*;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;

public class WallCommand implements Command {

    public WallBean getUser(String id) {
        WallBean wallUser = new WallBean();
        Friends user = FriendsDAO.retrieve(id);
        wallUser.setUserID(user.getId());
        wallUser.setName(user.getName());
        wallUser.setDob(user.getDob());
        wallUser.setEmail(user.getEmailAddress());
        return wallUser;
    }
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        if(request.getSession().getAttribute("id") != null || request.getSession().getAttribute("friendid") != null) {
            String id;
            if (request.getSession().getAttribute("friendid") != null) {
                id = request.getSession().getAttribute("friendid").toString();
            } else {
                id = request.getSession().getAttribute("id").toString();
            }
            WallBean wall = getUser(id);
            request.setAttribute("wall", wall);

            if(request.getParameter("message") != null) {
                
            	boolean isBullying = false;
               
                
                
                ExtractionKeywordImpl ek = new ExtractionKeywordImpl();
    			URL url =ExtractionCommand.class.getResource("/");

    			String file = url.getPath() + "servletAndBeans/bullFlagWords.txt";
    			String file2 = url.getPath() + "servletAndBeans/comparison.txt";
    			String content = request.getParameter("message");
    		
    			
    			try {
    				String keys = ek.ExtractSentenceKeyword(content, new File(file));
    				List<String> keywords = Arrays.asList(keys.split("\\s*,\\s*"));
    				
    				String keysComparison = ek.ExtractSentenceKeyword(content, new File(file2));
    				List<String> keywordsComparison = Arrays.asList(keysComparison.split("\\s*,\\s*"));
    				
    				if(keywords.size() != keywordsComparison.size()){
    					
    					System.out.println("I contain harmful information");
    					isBullying = true;
    					//send email
    					//try to send an email
    					String from = "unswbooksocialmedia@gmail.com";
    					String passwordFrom = "IHatePasswords";
    					String to = FriendsDAO.retrieve("7777777").getEmailAddress();
    					String sub = "A user has been flagged for a harmful post";
    					
    					//build the message based upon user input
    					String msg = "The following message by " + id + " has been flagged to contain bullying content: " + "\n" + content  + " Please take action accordingly.";
    					
    					
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
    			         message.setContent("<p>"+ msg+ "</p>","text/html");

    			         //send message  
    			         Transport.send(message);    
    			         System.out.println("Message sent for bullying successfully");    
    			        } catch (MessagingException e) {throw new RuntimeException(e);}   
    					
    				}
    				
    				else{
    					System.out.println("No bullying detected.");
    					isBullying = false;
    				}
    				
    				
    			} catch (Exception e) {
    				System.out.println("Bully comparison failed.");
    			}
    			messages m1 = new messages(request.getSession().getAttribute("id").toString(),request.getParameter("message"),isBullying);
    			 messagesDAO.saveOrUpdate(m1);
            }

            if(request.getParameter("messageLiked") != null) {
                likes l = new likes(Integer.parseInt(request.getParameter("messageLiked")), request.getSession().getAttribute("id").toString(),false);
                likesDAO.saveOrUpdate(l);
            }

            List<messages> messagesList = messagesDAO.search(id);
            Collections.reverse(messagesList);
            List<Integer> likes = new ArrayList<Integer>();
            for (messages m: messagesList) {
                likes.add(likesDAO.searchByMessageID(m.getId()).size());
            }
            request.setAttribute("messages", messagesList);
            request.setAttribute("likes", likes);
        }
        return "userWall.jsp";
	}
}
