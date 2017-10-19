package servletAndBeans;

public class nodeD3 {

	private String name;
	private int group;
	private String dob;
	private String gender;
	private String username;
	
	
	
	
	
	
	
	
	public nodeD3(String name, int group, String dob, String gender, String username) {
		super();
		this.name = name;
		this.group = group;
		this.dob = dob;
		this.gender = gender;
		this.username = username;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	
	
}
