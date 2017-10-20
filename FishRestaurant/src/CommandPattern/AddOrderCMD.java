package CommandPattern;

public class AddOrderCMD extends Command {
    private Account account;    //target of command
    private Order order;

    //command execute
    @Override
    public void execute(Account account) {
        this.account = account;
        account.addOrder(this.order);
        System.out.println(this.toString());
    }
    public void setOrder(Order order){
        this.order = order;
    }

    @Override
    public void undo() {
        if (order != null && account != null) {
            Order temp = account.getLastUndoOrder();    //get last order and hold  when undo command occurs
            order = temp;
            account.undo(order);        //undo command
        }
    }

    @Override
    public void redo() {
        if (order != null && account != null) {
            Order temp = account.getLastRedoOrder();    //get last redo order and hold  when redo command occurs
            order = temp;
            account.redo(order);        //redo command
        }
    }

    @Override
    public String toString() {
        if (this.order == null){
            return "Added Selected Fish Cmd " ;
        }else {
            return "Added Selected Fish Cmd ----> " +this.order.toString() ;
        }

    }
}
