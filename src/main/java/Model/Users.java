package Model;


import Annotation.Column;
import Annotation.Entity;
import Annotation.Id;
import Annotation.Table;

@Entity
@Table(name= "pilots")
public class Users {

    @Id
    //GenerateValue(strategy = GenerationType.Auto)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String firstName;
    @Column(name = "userName")
    private String userName;
    @Column(name = "rank")
    private String rank;

    public Users() {
        super();
    }

    public Users(String firstName, String userName, String rank) {
        this.firstName = firstName;
        this.userName = userName;
        this.rank = rank;
    }

    public Users(int id, String firstName, String userName, String rank) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return rank;
    }

    public void setPassword(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {

        return  id + "   " +
                "   " +  firstName + "   " +
                "   " + userName + "   " +
                "   " + rank ;
    }
}
