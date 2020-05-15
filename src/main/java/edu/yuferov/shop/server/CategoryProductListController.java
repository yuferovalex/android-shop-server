package edu.yuferov.shop.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
class CategoryProductListController {

    private static final List<Product> PRODUCTS = new ArrayList<>();

    private static final String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Vestibulum ipsum libero, ornare a quam ut, posuere pharetra mauris. Fusce vel felis eu libero " +
            "vestibulum sollicitudin. Vestibulum ullamcorper lectus eu ex eleifend, et porttitor mi varius. " +
            "Fusce ex massa, venenatis vel sem nec, sollicitudin tristique felis. Cras ullamcorper pellentesque " +
            "lorem, eget pretium lectus venenatis at. Curabitur lacinia odio at nibh ultricies pulvinar. Nunc purus " +
            "augue, scelerisque ut convallis ut, luctus sed lectus.";

    static {
        Random random = new Random(1);
        for (Category category : CategoriesController.CATEGORIES) {
            for (int productId = 1; productId < 10; productId++) {
                PRODUCTS.add(
                        Product.builder()
                                .id(productId)
                                .categoryId(category.getId())
                                .title(category.getName() + productId)
                                .description(DESCRIPTION)
                                .price(random.nextDouble() * 1000 + 100)
                                .discount(100.0 - (productId * 5 % 35))
                                .build()
                );
            }
        }
    }

    @GetMapping
    @RequestMapping("/category/{categoryId}")
    List<Product> getCategoryProductList(@PathVariable int categoryId) {
        return PRODUCTS.stream()
                .filter(p -> p.getCategoryId() == categoryId)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("/product/{productId}")
    Product getProduct(@PathVariable int productId) {
        return PRODUCTS.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .get();
    }
}
