package db;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activity")
public class activity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String userID;
    private String description;
    private Timestamp time;

    //Added default constructor
    public activity() {

    }

    public activity(String userID, String description, Timestamp time) {
        super();
        this.userID = userID;
        this.description = description;
        this.time = time;
    }

    @Column
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }
}
