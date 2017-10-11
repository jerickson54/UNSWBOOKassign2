package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class messages {
	
	//for auto generated id
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		// Id of the user whos board the message exists on
		private String userID;
		private String message;
		private boolean containBullying;
		
		//Added default constructor
		public messages() {
			
		}
		public messages(String userID, String message, boolean containBullying) {
			super();
			this.userID = userID;
			this.message = message;
			this.containBullying = containBullying;
		}
		
		@Column
		public String getUserID() {
			return userID;
		}

		public void setUserID(String userID) {
			this.userID = userID;
		}

		@Column
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		
		@Column
		public boolean isContainBullying() {
			return containBullying;
		}
		public void setContainBullying(boolean containBullying) {
			this.containBullying = containBullying;
		}
		
		public int getId(){
			return id;
		}
		
		
		

}
