package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.beverage.Americano;
import sample.cafekiosk.beverage.Latte;
import sample.cafekiosk.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @Test
    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    void add() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        // when & then
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).geName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("음료 여러 개를 추가하면 주문 목록에 담긴다.")
    void addSeveralBeverages() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        // when & then
        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @Test
    void addZeroBeverages() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // when & then
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문할 수 있습니다.");
    }

    @Test
    void remove() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // when & then
        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        // when & then
        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
    void calculateTotalPrice() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);

        // when
        int totalPrice = cafeKiosk.calculateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(8500);

    }

    @Test
    void createOrder() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        // when
        Order order = cafeKiosk.createOrder();

        // then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).geName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderWithCurrentTime() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        // when
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024, 10, 13, 14, 0));

        // then
        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).geName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("영업 시작 시간 이전에는 주문을 생성할 수 없다.")
    void createOrderOutsideOpenTime() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        // when & then
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024, 10, 13, 9, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
}