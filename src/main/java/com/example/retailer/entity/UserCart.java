package com.example.retailer.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "user_cart")
public class UserCart {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "purchased_qty")
    private Integer purchasedQty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getPurchasedQty() {
        return purchasedQty;
    }

    public void setPurchasedQty(Integer purchasedQty) {
        this.purchasedQty = purchasedQty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserCart{" +
                "id=" + id +
                ", item=" + item +
                ", user=" + user +
                ", purchasedQty=" + purchasedQty +
                '}';
    }
}
