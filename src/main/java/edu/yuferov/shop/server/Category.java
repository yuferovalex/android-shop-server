package edu.yuferov.shop.server;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Category {
    private int id;
    private String name;
    private String icon;
}
