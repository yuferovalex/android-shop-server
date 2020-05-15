package edu.yuferov.shop.server;

import lombok.Data;

import java.util.List;

@Data
class Order {
    @Data
    static class Item {
        private int productId;
        private int count;
    }

    private List<Item> cart;
    private double totalPrice;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String paymentType;
}
