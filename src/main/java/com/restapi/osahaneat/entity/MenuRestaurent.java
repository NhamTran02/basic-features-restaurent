package com.restapi.osahaneat.entity;

import com.restapi.osahaneat.entity.key.KeyMenuRestaurent;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "MenuRestaurent")
public class MenuRestaurent {
    @EmbeddedId
    KeyMenuRestaurent menuRestaurent;

    @ManyToOne
    @JoinColumn(name = "cate_id",insertable = false,updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "res_id",insertable = false,updatable = false)
    private Restaurent restaurent;

    @Column(name = "create_date")
    private Date createDate;

    public KeyMenuRestaurent getMenuRestaurent() {
        return menuRestaurent;
    }

    public void setMenuRestaurent(KeyMenuRestaurent menuRestaurent) {
        this.menuRestaurent = menuRestaurent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Restaurent getRestaurent() {
        return restaurent;
    }

    public void setRestaurent(Restaurent restaurent) {
        this.restaurent = restaurent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
