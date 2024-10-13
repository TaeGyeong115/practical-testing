package sample.cafekiosk.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();

        assertThat(americano.geName()).isEqualTo("아메리카노");
    }

    @Test
    void getPrice() {
        Americano americano = new Americano();

        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}