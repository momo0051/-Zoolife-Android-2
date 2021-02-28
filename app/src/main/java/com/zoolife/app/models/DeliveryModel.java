package com.zoolife.app.models;

import java.io.Serializable;

public class DeliveryModel implements Serializable {

    public String itemTitle, city, username, phone, email, id;

    public DeliveryModel(String itemTitle, String city, String username, String phone, String email, String id) {
        this.itemTitle = itemTitle;
        this.city = city;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.id = id;
    }
}
