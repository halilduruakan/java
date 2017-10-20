package VisitorPattern.Visitor;

import VisitorPattern.Visitable.FishSoldbyUnit;
import VisitorPattern.Visitable.FishSoldbyWeight;
import VisitorPattern.Visitable.IFish;

/**
 * Created by ugurbelge on 5/22/17.
 */
public interface IVisitor {
    int visit(IFish fish);
}
