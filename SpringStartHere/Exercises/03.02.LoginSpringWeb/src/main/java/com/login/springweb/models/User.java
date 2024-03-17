package com.login.springweb.models;

public class User {
    private String username;
    private String password;
    private String color;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.color = "black";
    }

    public User(String username, String password, String color) {
        this.username = username;
        this.password = password;
        this.color = color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
