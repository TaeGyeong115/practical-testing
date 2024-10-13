package sample.cafekiosk.beverage;

public class Americano implements sample.cafekiosk.unit.Beverage {
    @Override
    public String geName() {
        return "아메리카노";
    }

    @Override
    public int getPrice() {
        return 4000;
    }
}
