package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public abstract class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        products.add (new Product ("product1", 21));
        products.add (new Product ("product2", 22));
        products.add (new Product ("product3", 23));
    }

    public ProductRepository() {
    }

    public List<Product> getProductList() {
        return products;
    }

    public void setProductList(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductRepository{" +
                "products =" + products +
                '}';
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList (products);
    }

    public Product saveOrUpdateProduct(Product product) {
        for (int i = 0; i < products.size (); i++) {
            if (products.get (i).getId ().equals (product.getId ())) {
                products.set (i, product);
                return product;
            }
        }
        throw new RuntimeException ("Wow");
    }

    public Product findById(Long id) {
        for (Product p : products) {
            if (p.getId ().equals (id)) {
                return p;
            }
        }
        throw new RuntimeException ("Product not found");
    }
}
