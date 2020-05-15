package edu.yuferov.shop.server;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/category")
class CategoriesController {

    static final List<Category> CATEGORIES = Arrays.asList(
            Category.builder()
                    .id(1)
                    .name("Детектив")
                    .icon("https://cdn3.iconfinder.com/data/icons/caps-hats/512/Mans_Cap-512.png")
                    .build(),
            Category.builder()
                    .id(2)
                    .name("Драма")
                    .icon("https://cdn2.iconfinder.com/data/icons/life-concepts-lifestyles/128/entertainment-2-512.png")
                    .build(),
            Category.builder()
                    .id(3)
                    .name("Комедия")
                    .icon("https://cdn2.iconfinder.com/data/icons/life-concepts-lifestyles/128/entertainment-2-512.png")
                    .build()
    );

    @GetMapping
    List<Category> get() {
        return CATEGORIES;
    }

}
