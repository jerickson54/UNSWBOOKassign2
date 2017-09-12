package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Friends")
public class Friends {
	
	private String name;
	private String id;
	private String gender;
	private int age;
	
	public Friends(String name,String id, String gender, int age) {
		super();
		this.name = name;
		this.id = id;
		this.gender = gender;
		this.age = age;
	}
	
	
	@Id
	public String getId() {
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
	
	@Column(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
