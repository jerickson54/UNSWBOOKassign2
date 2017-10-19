package servletAndBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		List<String> toJSON = new ArrayList<String>();
		
		//friends of friends
		Friends f = null;
		
		List<Friends> fList =  FriendsDAO.search(name);
		
		if (fList != null)
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
		
		//friends of friends
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
		
		for(String ssss: toJSON){
			System.out.println(ssss);
		}
			
			
		
		
		
		
		
		return "d3Example.jsp";
	}

}
