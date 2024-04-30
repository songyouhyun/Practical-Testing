package sample.cafekiosk.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequestDto;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequestDto request, LocalDateTime registeredDateTime) {
        List<String> productsNumbers = request.getProductNumbers();
        List<Product> duplicateProducts = findProductBy(productsNumbers);

        Order order = Order.create(duplicateProducts, registeredDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);
    }

    private List<Product> findProductBy(List<String> productsNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productsNumbers);
        Map<String, Product> productMap = products.stream().collect(
                Collectors.toMap(Product::getProductNumber, product -> product));

        return productsNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }
}
