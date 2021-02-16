package com.example.retailer.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "offer")
public class Offer {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "quantity")
    private Integer onQuantity;

    @OneToOne
    @JoinColumn(name = "promo_id", referencedColumnName = "id")
    private Promo promo;

    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getOnQuantity() {
        return onQuantity;
    }

    public void setOnQuantity(Integer onQuantity) {
        this.onQuantity = onQuantity;
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", discount=" + discount +
                ", onQuantity=" + onQuantity +
                ", promo=" + promo +
                ", item=" + item +
                '}';
    }
}
