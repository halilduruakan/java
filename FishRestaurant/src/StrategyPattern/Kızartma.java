package StrategyPattern;

/**
 * Created by ugurbelge on 5/22/17.
 */
public class Kızartma implements CookingStrategy {
    private int price ;

    public Kızartma() {
        setPrice(15);
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int cook() {
        return getPrice();
    }

    @Override
    public String toString() {
        return "Cooking Type : Kızartma , " +
                "price = " + price ;
    }
}
