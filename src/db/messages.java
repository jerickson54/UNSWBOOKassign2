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
		
		//Added default constructor
		public messages() {
			
		}
		public messages(String userID, String message) {
			super();
			this.userID = userID;
			this.message = message;
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
		
		public int getId(){
			return id;
		}
		
		
		

}
