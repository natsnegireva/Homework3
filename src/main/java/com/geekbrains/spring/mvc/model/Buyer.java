package com.geekbrains.spring.mvc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Product> productList;

    public Buyer(String firstName, String lastName, List<Product> productList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.productList = new ArrayList<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", productList=" + productList +
                '}';
    }

    public Long getId() {
        return id;
    }
}
