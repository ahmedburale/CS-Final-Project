package com.AHMED.Utilities;

public class Manager extends Member {
    private String email;
    private String password;

    public Manager(String name, String phone, String email, String password) {
        super(name, phone);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                "email = '" + email + '\'' +
                ", password = '" + password + '\'';
    }
}
