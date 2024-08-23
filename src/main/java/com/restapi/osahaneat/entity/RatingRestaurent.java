package com.restapi.osahaneat.entity;

import com.restapi.osahaneat.entity.key.KeyMenuRestaurent;
import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity(name = "RatingRestaurent")
public class RatingRestaurent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurent restaurent;

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

    public Restaurent getRestaurant() {
        return restaurent;
    }

    public void setRestaurant(Restaurent restaurant) {
        this.restaurent = restaurant;
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
