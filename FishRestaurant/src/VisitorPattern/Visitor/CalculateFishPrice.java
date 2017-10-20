package VisitorPattern.Visitor;

import VisitorPattern.Visitable.IFish;

/**
 * Created by ugurbelge on 5/22/17.
 */
public class CalculateFishPrice implements IVisitor {
    @Override
    public int visit(IFish fish) {
        int cost = fish.totalCost();
        return cost;
    }

}
