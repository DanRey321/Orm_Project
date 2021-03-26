package Model;

import Annotation.Column;
import Annotation.Entity;
import Annotation.Id;
import Annotation.Table;

@Entity
@Table(name= "jets")
public class Jets {

    @Id
    private int jetId;
    @Column(name = "type")
    private String type;
    @Column(name = "speed")
    private String speed;
    @Column(name = "cost")
    private String cost;
    @Column(name = "year")
    private String year;

    public Jets() {
        super();
    }

    public Jets(String type, String speed, String cost, String year) {
        this.type = type;
        this.speed = speed;
        this.cost = cost;
        this.year = year;
    }

    public Jets(String type, String speed) {
        this.type = type;
        this.speed = speed;
    }

    public Jets(int jetId, String type, String speed, String cost, String year) {
        this.jetId = jetId;
        this.type = type;
        this.speed = speed;
        this.cost = cost;
        this.year = year;
    }

    public int getJetId() {
        return jetId;
    }

    public void setJetId(int jetId) {
        this.jetId = jetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Jets " + '\'' +
                " -------------------------" + '\'' +
                " Jet ID: " + jetId + '\'' +
                " Type: " + type + '\'' +
                " Speed: " + speed + '\'' +
                " Cost: " + cost + '\'' +
                "Year: " + year + '\'' +
                 '\'' + '\'' ;
    }
}
