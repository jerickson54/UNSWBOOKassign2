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
			//user id of the person who liked the message
			private String userID;
            //has the user seen the message?
            private Boolean hasSeen;
			
			//Added default constructor
			public likes() {
				
			}
			public likes(int messageID, String userID, Boolean hasSeen) {
				super();
				this.messageID = messageID;
				this.userID = userID;
				this.hasSeen = hasSeen;
			}

            @Column(name="messageId")
			public int getMessageID() {
				return messageID;
			}

			public void setMessageID(int messageID) {
				this.messageID = messageID;
			}

            @Column(name="userId")
			public String getUserID() {
				return userID;
			}

			public void setUserID(String userID) {
				this.userID = userID;
			}

            @Column(name="hasSeen")
            public Boolean getHasSeen() {
                return hasSeen;
            }

            public void setHasSeen(Boolean hasSeen) {
                this.hasSeen = hasSeen;
            }
			
			public int getId(){
				return id;
			}


}
