package com.AHMED.Utilities;

public class Member {
    private String name;
    private String phone;

    public Member(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name = '" + name + '\'' +
                ", phone = '" + phone + '\'';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
