package sample.cafekiosk.spring.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponse {
    private final Long id;
    private final String productNumber;
    private final ProductType type;
    private final ProductSellingStatus sellingStatus;
    private final String name;
    private final int price;

    @Builder
    private ProductResponse(Long id, String productNumber, ProductType type, String name, int price, ProductSellingStatus sellingStatus) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.name = name;
        this.price = price;
        this.sellingStatus = sellingStatus;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .name(product.getName())
                .price(product.getPrice())
                .sellingStatus(product.getSellingStatus())
                .build();
    }
}
