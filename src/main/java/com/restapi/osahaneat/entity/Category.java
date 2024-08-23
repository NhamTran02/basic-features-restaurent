package com.restapi.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_cate")
    private String nameCate;
    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> foods;

    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurent> menuRestaurents;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<MenuRestaurent> getMenuRestaurents() {
        return menuRestaurents;
    }

    public void setMenuRestaurents(Set<MenuRestaurent> menuRestaurents) {
        this.menuRestaurents = menuRestaurents;
    }
}
