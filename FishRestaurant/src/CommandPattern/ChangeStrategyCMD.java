package CommandPattern;

import StrategyPattern.CookingStrategy;

/**
 * Created by ugurbelge on 5/31/17.
 */
public class ChangeStrategyCMD extends Command {
    private Account account;
    private Order order;
    private CookingStrategy strategy;

    //execute change strategy command
    @Override
    public void execute(Account account) {
        this.account = account;
        account.changeStrategyinOrder(this.order,this.strategy);
        System.out.println(this.toString());
    }

    //change strategy in order
    public void changeStrategyinOrder(Order order,CookingStrategy strategy){
        this.order = order;
        this.strategy = strategy;
    }

    @Override
    public void undo() {
        if (order != null && account != null) {
            Order temp = account.getLastUndoOrder(); //get last undo order and hold it then move order to redo new order
            account.undo(temp);
            account.redo(order);
        }
    }

    @Override
    public void redo() {
        if (order != null && account != null) {
            Order temp = account.getLastRedoOrder(); //get last redo order and hold it then move order to undo order
            account.redo(temp);
            account.undo(order);
        }
    }

    @Override
    public String toString() {
        if (this.order == null) {
            return "Change Strategy Cmd ";
        } else {
            return "Change Strategy Cmd ----> " + this.order.toString() +"-------->"+strategy.toString();
        }
    }
}