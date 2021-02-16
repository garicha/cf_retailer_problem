package com.example.retailer.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {

    private int itemId;

    private int addedQty;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAddedQty() {
        return addedQty;
    }

    public void setAddedQty(int addedQty) {
        this.addedQty = addedQty;
    }
}
