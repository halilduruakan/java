package CommandPattern;

/**
 * Created by ugurbelge on 6/1/17.
 */
public class DeleteOrder extends Command {
    private Account account;
    private Order order;

    //execute delete command
    @Override
    public void execute(Account account) {
        this.account = account;
        account.undo(order);
        System.out.println(this.toString());
    }

    //set which order will delete
    public void setOrder(Order order){
        this.order = order;
    }


    @Override
    public void undo() {
        if (order != null && account != null) {
            Order temp = account.getLastRedoOrder();    //hold deleted order
            order = temp;
            account.redo(order);                //redo new order(add undo stack)
        }
    }

    @Override
    public void redo() {
        if (order != null && account != null) {
            Order temp = account.getLastUndoOrder();    //hold added order
            order = temp;
            account.undo(order);                     //undo old order(delete undo stack)
        }
    }

    @Override
    public String toString() {
        if (this.order == null){
            return "Delete Selected Order Cmd " ;
        }else {
            return "Delete Selected Order Cmd ----> " +this.order.toString() ;
        }

    }
}
