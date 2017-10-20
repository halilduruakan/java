package VisitorPattern.Visitable;

import VisitorPattern.Visitor.CalculateFishPrice;

/**
 * Created by ugurbelge on 5/22/17.
 */
public class FishSoldbyWeight implements IFish {
    private String name;
    private int price;
    private int weight;

    public FishSoldbyWeight(String name, int price, int weight) {
        setName(name);
        setPrice(price);
        setWeight(weight);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int totalCost() {
        return getPrice()*getWeight();
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fish name = " + '\'' + name + '\'' +
                ", Price = " + price +
                ", Weight = " + weight ;
    }

    @Override
    public double accept(CalculateFishPrice calculateFishPrice) {
        return calculateFishPrice.visit(this);
    }
}
