package com.restapi.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name ="Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurent restaurent;

    @Column(name = "creatte_date")
    private Date createtDate;

    @OneToMany(mappedBy = "orders")
    private Set<OrderItem> orderItems;

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

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

    public Restaurent getRestaurent() {
        return restaurent;
    }

    public void setRestaurent(Restaurent restaurent) {
        this.restaurent = restaurent;
    }

    public Date getCreatetDate() {
        return createtDate;
    }

    public void setCreatetDate(Date createtDate) {
        this.createtDate = createtDate;
    }
}
