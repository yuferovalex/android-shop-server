package edu.yuferov.shop.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
class Product {
    private int id;
    private int categoryId;
    private String title;
    private String description;
    private String thumbnail;
    private String image;
    private double price;
    private double discount;

    @Singular
    private List<Attribute> attributes;

    @Data
    @AllArgsConstructor
    static class Attribute {
        private String name;
        private String value;
    }
}

