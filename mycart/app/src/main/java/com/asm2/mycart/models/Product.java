package com.asm2.mycart.models;

import androidx.annotation.NonNull;

public class Product {

    private long id;
    private String thumbnail;
    private String name;
    private Category category;
    private double price;
    private int url;

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public Product(long id, String thumbnail, String name, Category category, double price) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
