package com.restapi.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    @OneToMany(mappedBy = "users")
    private Set<RatingFood> ratingFoods;

    @OneToMany(mappedBy = "users")
    private Set<RatingRestaurent> ratingRestaurents;

    @OneToMany(mappedBy = "users")
    private Set<Orders> orders;

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<RatingRestaurent> getRatingRestaurents() {
        return ratingRestaurents;
    }

    public void setRatingRestaurents(Set<RatingRestaurent> ratingRestaurents) {
        this.ratingRestaurents = ratingRestaurents;
    }

    public Set<RatingFood> getRatingFoods() {
        return ratingFoods;
    }

    public void setRatingFoods(Set<RatingFood> ratingFoods) {
        this.ratingFoods = ratingFoods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Roles getRole() {
        return roles;
    }

    public void setRole(Roles roles) {
        this.roles = roles;
    }
}
