package servletAndBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import db.Friends;

public class NewUsersBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Friends> newUsers;
	
	public NewUsersBean(){
		setNewUsers(new HashMap<String,Friends>());
	}

	public Map<String, Friends> getNewUsers() {
		return newUsers;
	}

	public void setNewUsers(Map<String, Friends> newUsers) {
		this.newUsers = newUsers;
	}
	
	public Friends getUser(String token) {
		Friends tmp = newUsers.get(token);
		newUsers.remove(token);
		return tmp;
	}
	
	public void addUser(String token, Friends user) {
		newUsers.put(token, user);
	}
	
}
