package CommandPattern;

import VisitorPattern.Visitable.IFish;

/**
 * Created by ugurbelge on 5/31/17.
 */
public class ChangeFishCMD extends Command {
    private Account account;
    private Order order;
    private IFish fish;

    //execute change fish command
    @Override
    public void execute(Account account) {
        this.account = account;
        account.changeFishinOrder(this.order,this.fish);
        System.out.println(this.toString());
    }

    //change fish in order
    public void changeFishinOrder(Order order,IFish fish){
        this.order = order;
        this.fish = fish;
    }

    @Override
    public void undo() {
        if (order != null && account != null) {
            Order temp = account.getLastUndoOrder();    //get last undo order and hold it then move order to redo new order
            account.undo(temp);
            account.redo(order);
        }
    }

    @Override
    public void redo() {
        if (order != null && account != null) {
            Order temp = account.getLastRedoOrder();     //get last redo order and hold it then move order to undo order
            account.redo(temp);
            account.undo(order);
        }
    }

    @Override
    public String toString() {
        if (this.order == null) {
            return "Change Fish Cmd ";
        } else {
            return "Change Fish Cmd ----> " + this.order.toString() +"-------->"+fish.toString();
        }
    }
}