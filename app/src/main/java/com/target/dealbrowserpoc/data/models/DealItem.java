package com.target.dealbrowserpoc.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DealItem implements Serializable {
    private int index;

    @SerializedName("_id")
    private String id;
    private String title;
    private String description;
    private String originalPrice;
    private String salePrice;
    private String price;
    private String guid;
    @SerializedName("image")
    private String imageUrl;
    private String aisle;

    public int getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getAisle() {
        return aisle;
    }

    public String getPrice() {
        return price;
    }

    public String getGuid() {
        return guid;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}