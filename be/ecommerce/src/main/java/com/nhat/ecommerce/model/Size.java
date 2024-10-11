package com.nhat.ecommerce.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Size {

    private String name;
    private String quantity;

    public Size() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
