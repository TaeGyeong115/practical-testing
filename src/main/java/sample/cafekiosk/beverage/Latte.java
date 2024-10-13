package sample.cafekiosk.beverage;

public class Latte implements sample.cafekiosk.unit.Beverage {
    @Override
    public String geName() {
        return "라떼";
    }

    @Override
    public int getPrice() {
        return 4500;
    }
}
