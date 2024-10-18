package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@DataJpaTest // 자동 롤백 지원
class OrderRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("해당 일자의 주문들을 조회한다.")
    void findOrderBy() {
        // given
        LocalDateTime startDateTime = LocalDate.now().atStartOfDay();
        LocalDateTime endDateTime = LocalDate.now().plusDays(1).atStartOfDay();
        List<Product> products = List.of(
                createProduct("001", HANDMADE, SELLING, "아메리카노", 4000)
        );
        productRepository.saveAll(products);

        LocalDateTime now = LocalDateTime.now();
        Order order = Order.create(products, now);
        orderRepository.save(order);

        // when
        List<Order> orders = orderRepository.findOrdersBy(startDateTime, endDateTime, OrderStatus.INIT);

        //then
        assertThat(orders).hasSize(1)
                .extracting("orderStatus", "totalPrice", "registeredDateTime")
                .contains(
                        tuple(OrderStatus.INIT, 4000, now)
                );
    }

    private Product createProduct(String productNumber, ProductType productType, ProductSellingStatus status, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(productType)
                .sellingStatus(status)
                .name(name)
                .price(price)
                .build();
    }

}