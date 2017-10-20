package CommandPattern;

import StrategyPattern.CookingStrategy;
import VisitorPattern.Visitable.IFish;

/**
 * Created by ugurbelge on 5/31/17.
 */

public class Order {
    private IFish fish ;
    private CookingStrategy strategy;

    public Order(){
        this.fish = null;
        this.strategy = null;
    }

    public void addFish(IFish fish){
        this.fish = fish;
    }

    public void addStrategy(CookingStrategy strategy){
        this.strategy = strategy;
    }

    public IFish getFish() {
        return fish;
    }

    public CookingStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "fish=" + fish +
                ", strategy=" + strategy +
                '}';
    }
}
