package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendsList")
public class friendsList {
	
	//for auto generated id
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
		
	private String Friendid1;
	private String Friendid2;
	private boolean hasSeen;
	
	//Added default constructor
	public friendsList() {
		
	}
	
	public friendsList(String Friendid1, String Friendid2) {
		super();
		this.Friendid1 = Friendid1;
		this.Friendid2 = Friendid2;
		hasSeen = false;
	}

	
	@Column(name = "Friendid1")
	public String getId1() {
		return Friendid1;
	}

	public void setId1(String Friendid1) {
		this.Friendid1 = Friendid1;
	}

	@Column(name = "Friendid2")
	public String getId2() {
		return Friendid2;
	}

	public void setId2(String Friendid2) {
		this.Friendid2 = Friendid2;
	}
	
	public int getId(){
		return id;
	}

	@Column(name = "hasSeen")
	public boolean getHasSeen() {
		return hasSeen;
	}

	public void setHasSeen(boolean hasSeen) {
		this.hasSeen = hasSeen;
	}
}