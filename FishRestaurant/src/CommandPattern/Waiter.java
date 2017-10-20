package CommandPattern;

import java.util.Deque;
import java.util.LinkedList;

public class Waiter {

    private Deque<Command> undoStack = new LinkedList<>();
    private Deque<Command> redoStack = new LinkedList<>();

    //execute command to the target
    public void performCommand(Command command, Account account) {
        command.execute(account);
        undoStack.offerLast(command);
    }

    //Undo last command
    public void undoLastOrder() {
        if (!undoStack.isEmpty()) {
            Command previousCMD = undoStack.pollLast();     //get last command of undo stack
            redoStack.offerLast(previousCMD);               //add end of redo stack
            System.out.println("{" + this.toString() + "} undoes {" + previousCMD.toString() + "}");
            previousCMD.undo();                             //call last command undo method
        }
    }
    //redo last command
    public void redoLastOrder() {
        if (!redoStack.isEmpty()) {
            Command previousCMD = redoStack.pollLast(); //get last command of redo stack
            undoStack.offerLast(previousCMD);           //add end of undo stack
            System.out.println("{" + this.toString() + "} redoes {" + previousCMD.toString() + "}");
            previousCMD.redo();                         //call last command redo method
        }
    }

    @Override
    public String toString() {
        return "Order";
    }
}
