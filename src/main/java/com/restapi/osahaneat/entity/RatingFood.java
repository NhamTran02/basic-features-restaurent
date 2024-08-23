package com.restapi.osahaneat.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity(name = "RatingFood")
public class RatingFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name="food_id")
    private Food food;

    @Column(name = "content")
    private String content;
    @Column(name = "rate_poin")
    private int rate_poin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRate_poin() {
        return rate_poin;
    }

    public void setRate_poin(int rate_poin) {
        this.rate_poin = rate_poin;
    }
}
