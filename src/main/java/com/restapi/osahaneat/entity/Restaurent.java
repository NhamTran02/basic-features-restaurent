package com.restapi.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name="Restaurent")
public class Restaurent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "description")
    private String desc;
    @Column(name = "image")
    private String image;
    @Column(name = "is_freeship")
    private boolean isFressship;
    @Column(name = "address")
    private String address;
    @Column(name = "open_date")
    private Date openDate;


    @OneToMany(mappedBy = "restaurent")
    private Set<RatingRestaurent> ratingRestaurents;

    @OneToMany(mappedBy = "restaurent")
    private Set<Orders> orders;

    @OneToMany(mappedBy = "restaurent")
    private Set<MenuRestaurent> menuRestaurents;

    @OneToMany(mappedBy = "restaurent")
    private Set<Promo> promos;

    public Set<Promo> getPromos() {
        return promos;
    }

    public void setPromos(Set<Promo> promos) {
        this.promos = promos;
    }

    public Set<MenuRestaurent> getMenuRestaurents() {
        return menuRestaurents;
    }

    public void setMenuRestaurents(Set<MenuRestaurent> menuRestaurents) {
        this.menuRestaurents = menuRestaurents;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFressship() {
        return isFressship;
    }

    public void setFressship(boolean fressship) {
        isFressship = fressship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
