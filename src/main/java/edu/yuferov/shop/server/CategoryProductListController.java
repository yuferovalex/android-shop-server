package edu.yuferov.shop.server;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
class CategoryProductListController {

    private static final Map<Integer, List<Product>> PRODUCTS = new HashMap<>();

    private static final String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Vestibulum ipsum libero, ornare a quam ut, posuere pharetra mauris. Fusce vel felis eu libero " +
            "vestibulum sollicitudin. Vestibulum ullamcorper lectus eu ex eleifend, et porttitor mi varius. " +
            "Fusce ex massa, venenatis vel sem nec, sollicitudin tristique felis. Cras ullamcorper pellentesque " +
            "lorem, eget pretium lectus venenatis at. Curabitur lacinia odio at nibh ultricies pulvinar. Nunc purus " +
            "augue, scelerisque ut convallis ut, luctus sed lectus.";

    private static final String IMAGE = "https://w0.pngwave.com/png/155/611/artist-s-book-scalable-graphics-gray-books-s-png-clip-art-thumbnail.png";

    static {
        Random random = new Random(1);
        int productId = 1;
        for (Category category : CategoriesController.CATEGORIES) {
            List<Product> products = new ArrayList<>();
            for (int i = 1; i < 10000; i++, productId++) {
                String title = category.getName() + " " + productId;
                Product.ProductBuilder builder = Product.builder()
                        .id(productId)
                        .categoryId(category.getId())
                        .title(title)
                        .description(DESCRIPTION)
                        .image(IMAGE)
                        .thumbnail(IMAGE)
                        .price(random.nextInt(100000) / 100.00 + 100.00)
                        .discount(productId * 5 % 35);

                for (int attributeId = 1; attributeId <= 5; attributeId++) {
                    builder
                            .attribute(new Product.Attribute(
                                    "Атрибут " + attributeId,
                                    "Значение атрибута " + attributeId + " продукта " + title
                            ));
                }

                products.add(builder.build());
            }
            PRODUCTS.put(category.getId(), products);
        }
    }

    @GetMapping
    @RequestMapping("/category/{categoryId}")
    List<Product> getCategoryProductList(@PathVariable int categoryId, @RequestParam int page, @RequestParam int pageSize) {
        return PRODUCTS.get(categoryId).stream()
                .skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("/product/{productId}")
    Product getProduct(@PathVariable int productId) {
        return PRODUCTS.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(p -> p.getId() == productId)
                .findFirst()
                .get();
    }
}
