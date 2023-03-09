package com.asm1.tut5.models;

import androidx.annotation.NonNull;

public class Friend {

    private String name;
    private String email;
    private String phone;
    private String organization;

    public Friend(String name, String email, String phone, String organization) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.organization = organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    public Friend() {

    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
