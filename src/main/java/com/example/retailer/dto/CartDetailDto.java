package com.example.retailer.dto;

import com.example.retailer.entity.Offer;
import com.example.retailer.entity.UserCart;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class CartDetailDto implements Serializable {

    private List<Offer> appliedOffers;

    private Set<UserCart> userCartList;

    private Float totalAmount;

    private Float discountedAmount;

    private Float payableAmount;

    public List<Offer> getAppliedOffers() {
        return appliedOffers;
    }

    public void setAppliedOffers(List<Offer> appliedOffers) {
        this.appliedOffers = appliedOffers;
    }

    public Set<UserCart> getUserCartList() {
        return userCartList;
    }

    public void setUserCartList(Set<UserCart> userCartList) {
        this.userCartList = userCartList;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Float discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Float getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Float payableAmount) {
        this.payableAmount = payableAmount;
    }
}
