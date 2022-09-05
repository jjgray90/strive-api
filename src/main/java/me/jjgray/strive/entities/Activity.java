package me.jjgray.strive.entities;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float distance;
    private float time;
    private float pace;
    private String location;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;


    public Activity() {

    }

    public Activity(int id, float distance, float time, float pace, String location, User user) {
        this.id = id;
        this.distance = distance;
        this.time = time;
        this.pace = pace;
        this.location = location;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getPace() {
        return pace;
    }

    public void setPace(float pace) {
        this.pace = pace;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
