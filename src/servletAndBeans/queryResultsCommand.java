package servletAndBeans;

import java.io.IOException;
import java.util.ArrayList;
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

public class queryResultsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name").trim();
		String messages = request.getParameter("Messages");
		String friends = request.getParameter("friends");
		
		//Initiate JSON variables
		JSONObject json = new JSONObject();
		JSONArray nodes = new JSONArray();
		JSONArray links = new JSONArray();
		
		/*
		Groups in JSON
		1 = friends
		2 = messages
		*/
		
		
		//Get the nodes
		List<Friends> userList = FriendsDAO.getEverything();
		for (Friends user : userList) {
			JSONObject jsUser = new JSONObject();
			jsUser.put("id", user.getId());
			jsUser.put("name", user.getName());
			jsUser.put("gender", user.getGender());
			jsUser.put("group", 1);
			nodes.put(jsUser);
		}
		
		//TODO: Also add messages as nodes.
		
		
		//Get the links
		List<friendsList> friendConnections = friendsListDAO.getEverything();
		for (friendsList connection: friendConnections) {
			JSONObject link = new JSONObject();
			link.put("target", connection.getId1());
			link.put("source", connection.getId2());
			link.put("edge", "friends");
			links.put(link);
		}
		
		//TODO: Also receive links between message and who posted it
		//TODO: ALso receive links between users who liked it
		
		//Assemble JSON file and make it a String
		json.put("nodes", nodes);
		json.put("links", links);
		String jsonString = json.toString();
		request.setAttribute("json", jsonString);
		System.out.println(jsonString);
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
>>>>>>> cfbdcd78ee1b1c296b7e85de8c5c1f855d9cc123
	}

}
