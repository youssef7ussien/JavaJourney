package com.hello_spring_web;

public class User {
  private String name;
  private String color;

  public User(String name, String color) {
    this.name = name;
    this.color = color.isEmpty() ? "black" : color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
