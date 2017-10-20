package VisitorPattern.Visitable;

import VisitorPattern.Visitor.CalculateFishPrice;

/**
 * Created by ugurbelge on 5/22/17.
 */
public interface IFish {
    double accept(CalculateFishPrice calculateFishPrice);
    int totalCost();
}
