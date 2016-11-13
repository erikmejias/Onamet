package com.erikmejia.onamet.model;

/**
 * Created by erik on 11/13/16.
 */

public class User {
    private String id;
    private String name;
    private boolean isVerified;
    private String phone_number;

    public User() {}

    public User(String id, String name, boolean isVerified, String phone_number) {
        this.id = id;
        this.name = name;
        this.isVerified = isVerified;
        this.phone_number = phone_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isVerified=" + isVerified +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
