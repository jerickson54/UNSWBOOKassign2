package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "likes")
public class likes implements Serializable {
			//used an int because messageID is auto generated as an int
			@Id
			private int messageID;
			//user id of the person who liked the message
			@Id
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

            @Column(name="userId")
			public String getUserID() {
				return userID;
			}

            @Column(name="hasSeen")
            public Boolean getHasSeen() {
                return hasSeen;
            }

            public void setHasSeen(Boolean hasSeen) {
                this.hasSeen = hasSeen;
            }
}
