package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class likes {
	
	//for auto generated id
	
			@Id
			@GeneratedValue(strategy=GenerationType.IDENTITY)
			private int id;
			//used an int because messageID is auto generated as an int
			private int messageID;
			//used a string because that is the zid for Friends.java
			private String userID;
			
			public likes(int messageID, String userID) {
				super();
				this.messageID = messageID;
				this.userID = userID;
			}
			
			@Column
			public int getMessageID() {
				return messageID;
			}

			public void setMessageID(int messageID) {
				this.messageID = messageID;
			}

			@Column
			public String getUserID() {
				return userID;
			}

			public void setUserID(String userID) {
				this.userID = userID;
			}
			
			public int getId(){
				return id;
			}
			
			
			
			
			

}
