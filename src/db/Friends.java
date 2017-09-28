package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friends")
public class Friends {
	
	private String name;
	private String emailAddress;
	private String dob;
	private String gender;
	private int age;
	private String username;
	private String password;
	private String id;
	private Boolean isAdmin;
	private Boolean isBanned;

	
	public Friends() {
		this.name = "";
		this.emailAddress = "";
		this.dob = "";
		this.age = -1;
		this.id = "";
		this.gender ="";
		this.username = "";
		this.password = "";
	}
	//Added default constructor needed for storing of query result
	public Friends(String name,String emailAddress, String dob,int age,String gender,String username,String password, String id) {
		super();
		this.name = name;
		this.emailAddress = emailAddress;
		this.dob = dob;
		this.age = age;
		this.id = id;
		this.gender = gender;
		this.username = username;
		this.password = password;
	}
	
	@Id
	public String getId() {
	    //System.out.println(id);
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column
	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}
	
	@Column(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "emailaddress")
	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	

	@Column(name = "username")
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "isadmin")
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "isbanned")
	public Boolean getBanned() {
		return isBanned;
	}

	public void setBanned(Boolean banned) {
		isBanned = banned;
	}
}
