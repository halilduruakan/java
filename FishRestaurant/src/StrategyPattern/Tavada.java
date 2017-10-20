package StrategyPattern;

/**
 * Created by ugurbelge on 5/22/17.
 */
public class Tavada implements CookingStrategy {
    private int price ;

    public Tavada() {
        setPrice(10);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int cook() {
        return getPrice();
    }

    @Override
    public String toString() {
        return "Cooking Type : Tavada , " +
                "price = " + price ;
    }
}
