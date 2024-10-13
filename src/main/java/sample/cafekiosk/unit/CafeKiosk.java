package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private List<sample.cafekiosk.unit.Beverage> beverages = new ArrayList<>();

    public void add(sample.cafekiosk.unit.Beverage beverage) {
        beverages.add(beverage);
    }

    public void remove(sample.cafekiosk.unit.Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (sample.cafekiosk.unit.Beverage beverage : beverages) {
            totalPrice += beverage.getPrice();
        }
        return totalPrice;
    }

    public Order createOrder() {
        return new Order(LocalDateTime.now(), beverages);
    }

}
