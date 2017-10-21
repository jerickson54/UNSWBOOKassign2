package servletAndBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import db.Friends;
import db.FriendsDAO;
import db.friendsList;
import db.friendsListDAO;
import db.likesDAO;
import db.likes;
import db.messages;
import db.messagesDAO;

public class queryResultsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name").trim();
		String messages = request.getParameter("Messages");
		String friends = request.getParameter("friends");
		
		if (name == null || messages == null || friends == null) {
			return "";
		}
		//Initiate JSON variables
		JSONObject json = new JSONObject();
		JSONArray nodes = new JSONArray();
		JSONArray links = new JSONArray();
		
		/*
		Groups in JSON
		1 = friends
		2 = messages
		*/
		
		HashSet<String> queriedUsers = new HashSet<String>();
		HashSet<Integer> queriedMessages = new HashSet<Integer>();
		//Get the nodes
		if (!name.equals("")) {
			List<Friends> users = FriendsDAO.search(name);
			for (Friends f: users) {
				queriedUsers.add(f.getId());
			}
		}
		if (!friends.equals("")) {
			List<Friends> users = FriendsDAO.search(friends);
			for (Friends f: users) {
				queriedUsers.add(f.getId());
				List<friendsList> f2List = friendsListDAO.search(f.getId());
				for (friendsList f2 : f2List) {
					String f2id;
					if (f2.getId1().equals(f.getId())) {
						f2id = f2.getId2();
						queriedUsers.add(f2.getId2());
					}
					else {
						queriedUsers.add(f2.getId1());
						f2id = f2.getId1();
					}
					List<friendsList> f3List = friendsListDAO.search(f2id);
					for (friendsList f3 : f3List) {
						if (f3.getId1().equals(f2id)) {
							queriedUsers.add(f3.getId2());
						}
						else {
							queriedUsers.add(f3.getId1());
						}
					}
				}
			}
		}
		if (!messages.equals("")) {
			List<messages> msgList = messagesDAO.contentSearch(messages);
			//TODO: Implement contentSearch
			for (messages m: msgList) {
				queriedMessages.add(m.getId());
			}
			
		}
		List<Friends> userList = FriendsDAO.getEverything();
		List<messages> msgList = messagesDAO.getEverything();
		List<friendsList> friendConnections = friendsListDAO.getEverything();
		List<likes> likeList = likesDAO.getLikes();
		//Display everythings
		//Get the nodes
		//Add users to nodes in JSON
		for (Friends user : userList) {
			JSONObject jsUser = new JSONObject();
			if (queriedUsers.contains(user.getId())){
				jsUser.put("queried", true);
			}
			else {
				jsUser.put("queried", false);
			}
			jsUser.put("name", user.getName());
			jsUser.put("gender", user.getGender());
			jsUser.put("group", 1);
			jsUser.put("id", user.getId());
			nodes.put(jsUser);
		}
		
		//Add messages to nodes in JSON
		for (messages msg: msgList) {
			JSONObject jsMsg = new JSONObject();
			if (queriedMessages.contains(msg.getId())) {
				jsMsg.put("queried", true);
			}
			else {
				jsMsg.put("queried", false);
			}
			jsMsg.put("message", msg.getMessage());
			jsMsg.put("group", 2);
			jsMsg.put("id", msg.getId());
			nodes.put(jsMsg);
			//Add author of message as link in JSON
			JSONObject link = new JSONObject();
			link.put("edge", "posted");
			link.put("target", msg.getId());
			link.put("source", msg.getUserID());
			links.put(link);
		}
		
		//Get the links
		//Get links between friends
		for (friendsList connection: friendConnections) {
			JSONObject link = new JSONObject();
			link.put("edge", "friends");
			link.put("target", connection.getId1());
			link.put("source", connection.getId2());
			links.put(link);
		}
		
		//Get links for likes (between messages and posts)
		
		for (likes l: likeList) {
			JSONObject link = new JSONObject();
			link.put("edge", "liked");
			link.put("target", l.getMessageID());
			link.put("source", l.getUserID());
			links.put(link);
		}
		
		//Assemble JSON file and make it a String
		json.put("nodes", nodes);
		json.put("links", links);
		String jsonString = json.toString();
		request.setAttribute("json", jsonString);
		
		
		return "d3Example.jsp";
		
		/*
		//friends of friends
		Friends f = null;
		
		List<Friends> fList =  FriendsDAO.search(name);
		
		if (fList.size() < 1) return "d3Example.jsp";
		
		f = fList.get(0);
		
		
		String id = f.getId();
	
		List<friendsList> res = friendsListDAO.search(id);
		List<String> myFriends = new ArrayList<String>();
		for(friendsList ss: res){
			if(ss.getId1().equals(id))
				myFriends.add(ss.getId2());
			if(ss.getId2().equals(id))
				myFriends.add(ss.getId1());
						
		}

		//Second Search
		List<String> results = new ArrayList<String>();
		for (String id2 : myFriends){
			List<friendsList> res2 = friendsListDAO.search(id2);
			
			for(friendsList ss: res2){
				if(ss.getId1().equals(id))
					results.add(ss.getId2());
				if(ss.getId2().equals(id))
					results.add(ss.getId1());
							
			}
		}
		
		
		for(String type: results){
			String toDisplay = "";
			toDisplay += FriendsDAO.retrieve(type).getName() + " ";
			toDisplay += FriendsDAO.retrieve(type).getEmailAddress() + " ";
			toDisplay += FriendsDAO.retrieve(type).getUsername();
			toJSON.add(toDisplay);
		}
<<<<<<< HEAD
		
		for(String ssss: toJSON){
			System.out.println(ssss);
		}
			
			
		
		
		
		
		
		return "d3Example.jsp";
=======
		*/

	}

}
