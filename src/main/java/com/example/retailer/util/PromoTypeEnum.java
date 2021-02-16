package com.example.retailer.util;

public enum PromoTypeEnum {

    PRICE_DISCOUNT("PRICE DISCOUNT"),
    FREE_ITEM("FREE ITEM");

    public final String promoType;

    private PromoTypeEnum (String promoType) {
        this.promoType = promoType;
    }

    public String getValue() {
        return promoType;
    }
}
