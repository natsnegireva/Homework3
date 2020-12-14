package com.geekbrains.spring.mvc;

import com.geekbrains.spring.mvc.controllers.BuyerController;

public class ConsoleApp {

    public static void main(String[] args) {
        BuyerController controller;
        controller = new BuyerController();
        System.out.println("***initialising***");
        controller.init();
        controller.insertBuyer(controller.getBuyer1());
        controller.insertBuyer(controller.getBuyer2());
        controller.printBuyersList();

        System.out.println("***trying to make a duplicate of Buyer1***");
        controller.insertBuyer(controller.getBuyer1());
        controller.printBuyersList();

        System.out.println("***printing a goods list of Buyer1***");
        controller.printBuyerProduct(controller.getBuyer1());
        System.out.println("***printing a goods list of Buyer2***");
        controller.printBuyerProduct(controller.getBuyer2());

        System.out.println("***deleting a goods list of Buyer1***");
        controller.printBuyerProduct(controller.getBuyer1());
        System.out.println("***After deleting. printing a goods list of Buyer1***");
        controller.printBuyerProduct(controller.getBuyer1());
        System.out.println("***After deleting. printing a goods list of buyers***");
        controller.printBuyersList();

        controller.shutdown();
    }
}
