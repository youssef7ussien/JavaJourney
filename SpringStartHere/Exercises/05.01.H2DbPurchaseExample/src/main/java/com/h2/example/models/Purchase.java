package com.h2.example.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Purchase {
    private Long id;
    private String product;
    private BigDecimal price;
    private int quantity;

    public Purchase() {
    }

    public Purchase(Long id, String product, BigDecimal price, int quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id
                && Objects.equals(product, purchase.product)
                && Objects.equals(price, purchase.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, price);
    }
}
