package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Buyer;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import com.geekbrains.spring.mvc.model.Product;
import javax.persistence.*;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BuyerController {
    private EntityManager em;
    private Buyer buyer1;
    private Buyer buyer2;

    public BuyerController(EntityManager em, Buyer buyer1, Buyer buyer2) {
        this.em = em;
        this.buyer1 = buyer1;
        this.buyer2 = buyer2;
    }

    public BuyerController() {

    }

    public void init() {
        buyer1 = newBuyer1();
        buyer2 = newBuyer2();
    }

    Buyer newBuyer1() {
        List<Product> product = new ArrayList<>();
        product.add(new Product("product1", 21));
        product.add(new Product("product2", 22));
        product.add(new Product("product3", 23));
        return new Buyer ("Petr", "Petrov", product);
    }

    Buyer newBuyer2() {
        List<Product> product = new ArrayList<>();
        product.add(new Product("product1", 11));
        product.add(new Product("product2", 12));
        product.add(new Product("product3", 13));
        return new Buyer ("Ivan", "Ivanov", product);
    }

    public void insertBuyer(Buyer buyer) {
        em.getTransaction().begin();
        try{
            em.persist(buyer);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        }
    }

    private List<Buyer> getBuyersList() {
        return em.createQuery("from Buyer").getResultList();
    }

    private List<Product> getBuyerProduct (Buyer buyer) {
        Long id = findBuyerId(buyer);
        Query query = em.createQuery("from Product g where g.buyer.id = :id");
        query.setParameter("id", id);
        return (List<Product>)query.getResultList();
    }

    public void deleteBuyerProduct (Buyer buyer) {
        em.getTransaction().begin();
        Long id = findBuyerId(buyer);
        System.out.println("deleteBuyerProduct() - id: " + id);
        Query query = em.createQuery("from Product g where g.buyer.id = :id");
        query.setParameter("id", id);
        List<Product> productList = (List<Product>)query.getResultList();
        em.getTransaction().commit();
    }

    public Long findBuyerId(Buyer buyer){
        Query query = em.createQuery("from Buyer b " +
                "where b.firstName = :firstName and b.lastName = :lastName");
        List<Buyer> buyerList = (List<Buyer>)query.getResultList();
        Buyer buyer3;
        if(!buyerList.isEmpty()) {
            buyer3 = buyerList.get(0);
            return buyer3.getId();
        } else {
            return null;
        }
    }

    private boolean isBuyerNotExist(Long buyerId) {
        Query query = em.createQuery("from Buyer b where b.id = :id");
        query.setParameter("id", buyerId);
        List<Buyer> buyerList = (List<Buyer>)query.getResultList();
        return buyerList.isEmpty();
    }

    public void shutdown() {
        em.close();
    }

    public void printBuyersList() {
        List<Buyer> buyers = getBuyersList();
        buyers.forEach(System.out::println);
    }

    public void printBuyerProduct(Buyer buyer) {
        List<Product> productList = getBuyerProduct(buyer);
        productList.forEach(System.out::println);
    }

    public Buyer getBuyer1() {
        return buyer1;
    }

    public Buyer getBuyer2() {
        return buyer2;
    }

}

