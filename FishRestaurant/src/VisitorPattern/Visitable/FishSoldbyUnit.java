package VisitorPattern.Visitable;

import VisitorPattern.Visitor.CalculateFishPrice;

/**
 * Created by ugurbelge on 5/22/17.
 */
public class FishSoldbyUnit implements IFish {
    private String name;
    private int price;
    private int unit;

    public FishSoldbyUnit(String name, int price, int unit) {
        setName(name);
        setPrice(price);
        setUnit(unit);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getUnit() {
        return unit;
    }


    @Override
    public double accept(CalculateFishPrice calculateFishPrice) {
        return calculateFishPrice.visit(this);
    }

    @Override
    public int totalCost() {
        return getPrice()*getUnit();
    }

    @Override
    public String toString() {
        return "Fish name = " + '\'' + name + '\'' +
                ", Price = " + price +
                ", Unit = " + unit ;
    }
}
